
package modelo;

public class UsuarioActual {
    private static int idUsuario;
    private static String nombreUsuario;

    public static void setIdUsuario(int id) {
        idUsuario = id;
    }

    public static void setNombreUsuario(String nombre) {
        nombreUsuario = nombre;
    }

    public static int getIdUsuario() {
        return idUsuario;
    }

    public static String getNombreUsuario() {
        return nombreUsuario;
    }
    
}
