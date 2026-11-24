package models;

import java.util.Date;

public class VentaModel {
    private int idVenta;
    private int idEmpleado;
    private int idCliente;
    private String fechaHora;
    private String metodoPago;
    private double total;
    private String estado;

    public VentaModel(int idVenta, int idEmpleado, int idCliente, String fechaHora, String metodoPago, double total, String estado) {
        this.idVenta = idVenta;
        this.idEmpleado = idEmpleado;
        this.idCliente = idCliente;
        this.fechaHora = fechaHora;
        this.metodoPago = metodoPago;
        this.total = total;
        this.estado = estado;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void mostrarInformacion() {
        System.out.println("CODIGO_VENTA: " + idVenta + " - CODIGO_EMPLEADO: " + idEmpleado + " - CODIGO_CLIENTE: " + idCliente + " - DISPONIBLE: " + disponible + " - ID_TIPO: " + idTipo);
    }
}
