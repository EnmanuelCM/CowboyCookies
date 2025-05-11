package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.CabeceraVenta;
import modelo.DetalleVenta;

public class ctrl_RegistrarVenta {

    //ultimo id de la cabecera
    public static int idCabeceraRegistrada;
    java.math.BigDecimal iDColVar;

    /**
     * **************************************************
     * metodo para guardar la cabecera de venta
     * **************************************************
     */
    public boolean guardar(CabeceraVenta objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.getConnection();
        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "INSERT INTO ventas (id_usuario, fecha_hora, total) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            consulta.setInt(1, objeto.getId_usuario());
            consulta.setString(2, objeto.getFecha_hora());
            consulta.setDouble(3, objeto.getTotal());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            ResultSet rs = consulta.getGeneratedKeys();
            while (rs.next()) {
                iDColVar = rs.getBigDecimal(1);
                idCabeceraRegistrada = iDColVar.intValue();
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar cabecera de venta: " + e);
        }
        return respuesta;
    }

    /**
     * **************************************************
     * metodo para guardar el detalle de venta
     * **************************************************
     */
    public boolean guardarDetalle(DetalleVenta objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.getConnection();
        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "INSERT INTO detalleventa (id_detalle, id_venta, id_producto, nombre, cantidad, precio_unitario, itbis, total) VALUES (?,?,?,?,?,?,?,?)"
            );

            consulta.setInt(1, 0);
            consulta.setInt(2, idCabeceraRegistrada);
            consulta.setInt(3, objeto.getId_producto());
            consulta.setString(4, objeto.getNombre());
            consulta.setInt(5, objeto.getCantidad());
            consulta.setDouble(6, objeto.getPrecio_unitario());
            consulta.setDouble(7, objeto.getItbis());
            consulta.setDouble(8, objeto.getTotal());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar detalle de venta: " + e);
        }
        return respuesta;
    }

}
