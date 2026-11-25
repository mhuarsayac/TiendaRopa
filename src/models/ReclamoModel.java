package models;

public class ReclamoModel {
    private int idReclamo;
    private String idCliente;
    private int idVenta;
    private String idEmpleadoRegistro;
    private String idEmpleadoResolucion;
    private String tipo;
    private String descripcion;
    private String fechaRegistro;
    private String estado;
    private String respuesta;
    private String fechaResolucion;

    public ReclamoModel(int idReclamo, String idCliente, int idVenta, String idEmpleadoRegistro,
                        String idEmpleadoResolucion, String tipo, String descripcion,
                        String fechaRegistro, String estado, String respuesta,
                        String fechaResolucion) {
        this.idReclamo = idReclamo;
        this.idCliente = idCliente;
        this.idVenta = idVenta;
        this.idEmpleadoRegistro = idEmpleadoRegistro;
        this.idEmpleadoResolucion = idEmpleadoResolucion;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.respuesta = respuesta;
        this.fechaResolucion = fechaResolucion;
    }

    public int getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(int idReclamo) {
        this.idReclamo = idReclamo;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getIdEmpleadoRegistro() {
        return idEmpleadoRegistro;
    }

    public void setIdEmpleadoRegistro(String idEmpleadoRegistro) {
        this.idEmpleadoRegistro = idEmpleadoRegistro;
    }

    public String getIdEmpleadoResolucion() {
        return idEmpleadoResolucion;
    }

    public void setIdEmpleadoResolucion(String idEmpleadoResolucion) {
        this.idEmpleadoResolucion = idEmpleadoResolucion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(String fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public void mostrarInformacion() {
        System.out.println("ID_RECLAMO: " + idReclamo + " - ID_CLIENTE: " + idCliente +
                " - ID_VENTA: " + idVenta + " - ID_EMPLEADO_REGISTRO: " + idEmpleadoRegistro +
                " - ID_EMPLEADO_RESOLVIO: " + idEmpleadoResolucion + " - TIPO: " + tipo +
                " - DESCRIPCION: " + descripcion + " - FECHA_REGISTRO: " + fechaRegistro + " - ESTADO: " + estado +
                " - RESPUESTA: " + respuesta + " - FECHA_RESOLUCION: " + fechaResolucion);
    }
}
