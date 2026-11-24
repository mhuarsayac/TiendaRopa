package models;

public class DetalleVentaModel {
    private int idDetalle;
    private int idVenta;
    private int idVariante;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public DetalleVentaModel(int idDetalle, int idVenta, int idVariante, int cantidad, double precioUnitario, double subtotal) {
        this.idDetalle = idDetalle;
        this.idVenta = idVenta;
        this.idVariante = idVariante;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdVariante() {
        return idVariante;
    }

    public void setIdVariante(int idVariante) {
        this.idVariante = idVariante;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void mostrarInformacion() {
        System.out.println("CODIGO_DETALLE: " + idDetalle + " - CODIGO_VENTA: " + idVenta + " - CODIGO_VARIANTE: " + idVariante +
                " - CANTIDAD: " + cantidad + " - PRECIO_UNITARIO: " + precioUnitario + " - SUBTOTAL: " + subtotal);
    }
}
