
package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import modelo.Cupón;


public class ctrlCupon {
    
    public Cupón buscarCupon(String codigo) {
        Cupón cupon = null;
        String sql = "SELECT * FROM cupones WHERE codigo = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codigo);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cupon = new Cupón(
                    rs.getString("codigo"),
                    rs.getString("descripcion"),
                    rs.getString("tipo_descuento"),
                    rs.getDouble("valor_descuento"),
                    rs.getBoolean("usado")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cupon;
    }

    public void marcarComoUsado(String codigo) {
        String sql = "UPDATE cupones SET usado = true WHERE codigo = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
   
