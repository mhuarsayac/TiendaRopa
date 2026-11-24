package models;

import java.util.Date;

public class VentaModel {
    private int idVenta;
    private String idEmpleado;
    private String idCliente;
    private String fechaHora;
    private double total;
    private String estado;

    public VentaModel(int idVenta, String idEmpleado, String idCliente, String fechaHora,
                      double total, String estado) {
        this.idVenta = idVenta;
        this.idEmpleado = idEmpleado;
        this.idCliente = idCliente;
        this.fechaHora = fechaHora;
        this.total = total;
        this.estado = estado;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
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
        System.out.println("CODIGO_VENTA: " + idVenta + " - CODIGO_EMPLEADO: " + idEmpleado + " - CODIGO_CLIENTE: " + idCliente + " - FECHA_HORA: " + fechaHora + " - TOTAL: " + total + " - ESTADO: " + estado);
    }
}
