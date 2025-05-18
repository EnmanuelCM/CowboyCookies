
package modelo;


public class Cupón {
    private String codigo;
    private String descripcion;
    private String tipoDescuento;
    private double valorDescuento;
    private boolean usado;

    // Constructor
    public Cupón(String codigo, String descripcion, String tipoDescuento, double valorDescuento, boolean usado) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.tipoDescuento = tipoDescuento;
        this.valorDescuento = valorDescuento;
        this.usado = usado;
    }

    // Getters
    public String getCodigo() { return codigo; }
    public String getDescripcion() { return descripcion; }
    public String getTipoDescuento() { return tipoDescuento; }
    public double getValorDescuento() { return valorDescuento; }
    public boolean isUsado() { return usado; }
}
    

