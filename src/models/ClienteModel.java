package models;

import java.time.LocalDate;
import java.util.Date;

public class ClienteModel extends PersonaModel {
    String id_cliente;
    String id_persona;
    String id_nivel_cliente;
    LocalDate fecha_registro;

    public ClienteModel(String idPersona, String dni, String primerNombre, String segundoNombre, String apellidoPaterno, String apellidoMaterno, String direccion, String distrito, String codigoPostal, String telefono, String correo, String id_cliente, String id_persona, String id_nivel_cliente, LocalDate fecha_registro) {
        super(idPersona, dni, primerNombre, segundoNombre, apellidoPaterno, apellidoMaterno, direccion, distrito, codigoPostal, telefono, correo);
        this.id_cliente = id_cliente;
        this.id_persona = id_persona;
        this.id_nivel_cliente = id_nivel_cliente;
        this.fecha_registro = fecha_registro;
    }

    @Override
    public String getRol() {
        return "CLIENTE";
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getId_persona() {
        return id_persona;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }

    public String getId_nivel_cliente() {
        return id_nivel_cliente;
    }

    public void setId_nivel_cliente(String id_nivel_cliente) {
        this.id_nivel_cliente = id_nivel_cliente;
    }

    public LocalDate getFechaRegistro() {
        return fecha_registro;
    }

    public void setFechaRegistro(LocalDate fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
}
