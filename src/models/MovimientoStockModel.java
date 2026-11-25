package models;

public class MovimientoStockModel {
    private int idMovimiento;
    private int idVariante;
    private int idVenta;
    private String tipo;
    private int cantidad;
    private int stockResultante;
    private String fecha;
    private String observacion;

    public MovimientoStockModel(int idMovimiento, int idVariante, int idVenta, String tipo,
                                int cantidad, int stockResultante, String fecha, String observacion) {
        this.idMovimiento = idMovimiento;
        this.idVariante = idVariante;
        this.idVenta = idVenta;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.stockResultante = stockResultante;
        this.fecha = fecha;
        this.observacion = observacion;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public int getIdVariante() {
        return idVariante;
    }

    public void setIdVariante(int idVariante) {
        this.idVariante = idVariante;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getStockResultante() {
        return stockResultante;
    }

    public void setStockResultante(int stockResultante) {
        this.stockResultante = stockResultante;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public void mostrarInformacion() {
        System.out.println("ID_MOVIMIENTO: " + idMovimiento + " - ID_VARIANTE: " + idVariante +
                " - ID_VENTA: " + idVenta + " - TIPO: " + tipo + " - CANTIDAD: " + cantidad +
                " - STOCK_RESULTANTE: " + stockResultante + " - FECHA: " + fecha + " - OBSERVACION: " + observacion);
    }
}
