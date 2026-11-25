package models;

import java.time.LocalDate;

public class EmpleadoModel extends PersonaModel {
    private String idEmpleado;
    private int idUsuario;
    private String tipo;
    private double salario;
    private double bono;
    private LocalDate fechaContratacion;
    private LocalDate fechaDespedida;
    private String estado;
    private String motivo;

    public EmpleadoModel(String idPersona, String dni, String primerNombre, String segundoNombre,
                         String apellidoPaterno, String apellidoMaterno, String direccion,
                         String distrito, String codigoPostal, String telefono, String correo,
                         String idEmpleado, int idUsuario, String tipo, double salario, double bono,
                         LocalDate fechaContratacion, LocalDate fechaDespedida,
                         String estado, String motivo) {
        super(idPersona, dni, primerNombre, segundoNombre, apellidoPaterno,
                apellidoMaterno, direccion, distrito, codigoPostal, telefono, correo);
        this.idEmpleado = idEmpleado;
        this.idUsuario = idUsuario;
        this.tipo = tipo;
        this.salario = salario;
        this.bono = bono;
        this.fechaContratacion = fechaContratacion;
        this.fechaDespedida = fechaDespedida;
        this.estado = estado;
        this.motivo = motivo;
    }

    @Override
    public String getRol() {
        return "EMPLEADO";
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getBono() {
        return bono;
    }

    public void setBono(double bono) {
        this.bono = bono;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public LocalDate getFechaDespedida() {
        return fechaDespedida;
    }

    public void setFechaDespedida(LocalDate fechaDespedida) {
        this.fechaDespedida = fechaDespedida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("=== Datos de Empleado ===");
        System.out.println("ID Empleado: " + idEmpleado);
        System.out.println("ID Usuario: " + idUsuario);
        System.out.println("Tipo: " + tipo);
        System.out.println("Salario: " + salario);
        System.out.println("Bono: " + bono);
        System.out.println("Fecha de contratación: " + fechaContratacion);
        System.out.println("Fecha de despedida: " + fechaDespedida);
        System.out.println("Estado: " + estado);
        System.out.println("Motivo: " + motivo);
    }
}
