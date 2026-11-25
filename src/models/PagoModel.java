package models;

public class PagoModel {
    private int idPago;
    private int idVenta;
    private String metodoPago;
    private double monto;
    private String fechaPago;

    public PagoModel(int idPago, int idVenta, String metodoPago, double monto, String fechaPago) {
        this.idPago = idPago;
        this.idVenta = idVenta;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.fechaPago = fechaPago;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public void mostrarInformacion() {
        System.out.println("ID_PAGO: " + idPago + " - ID_VENTA: " + idVenta + " - METODO_PAGO: " + metodoPago +
                " - MONTO: " + monto + " - FECHA_PAGO: " + fechaPago);
    }
}
