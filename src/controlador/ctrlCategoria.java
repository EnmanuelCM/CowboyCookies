/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import conexion.Conexion;
import modelo.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;



/**
 *
 * @author juand
 */
public class ctrlCategoria {

    private String id_categorias;
    
    public boolean guardar(Categoria objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.getConnection();
        try {
            PreparedStatement consulta = cn.prepareStatement("insert into categorias values(?,?,?)");
            consulta.setInt(1, 0);
            consulta.setString(2, objeto.getNombre_categoria());
            consulta.setString(3, objeto.getDescripcion());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar categoria: " + e);
        }

        return respuesta;
    }
    
    public boolean existeCategoria(String categoria) {
        boolean respuesta = false;
        String sql = "select descripcion from categorias where descripcion = '" + categoria + "';";
        Statement st;

        try {
            Connection cn = Conexion.getConnection();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar categoria: " + e);
        }
        return respuesta;
    }
    
    public boolean actualizar(Categoria objeto, int id_categoria) {
        boolean respuesta = false;
        Connection cn = conexion.Conexion.getConnection();
        try {
            PreparedStatement consulta = cn.prepareStatement("update categorias set descripcion=? where id_categoria ='" + id_categoria + "'");
            consulta.setString(1, objeto.getDescripcion());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar categoria: " + e);
        }

        return respuesta;
    }
    
    public boolean eliminar(int id_categoria) {
        boolean respuesta = false;
        Connection cn = Conexion.getConnection();
        try {

            PreparedStatement consulta = cn.prepareStatement(
                    "delete from categorias where id_categoria ='" + id_categoria + "'");
            consulta.executeUpdate();
           
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar cartegoria: " + e);
        }

        return respuesta;
    }
    
}
