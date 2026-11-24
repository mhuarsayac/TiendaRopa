package models;

public class PersonaModel {
    private String idPersona;
    private String dni;
    private String primerNombre;
    private String segundoNombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String direccion;
    private String distrito;
    private String codigoPostal;
    private String telefono;
    private String correo;

    public PersonaModel(String idPersona, String dni, String primerNombre, String segundoNombre, String apellidoPaterno, String apellidoMaterno, String direccion, String distrito, String codigoPostal, String telefono, String correo) {
        this.idPersona = idPersona;
        this.dni = dni;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.direccion = direccion;
        this.distrito = distrito;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCoidgoPostal(String coidgoPostal) {
        this.codigoPostal = coidgoPostal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void mostrarInformacion(){
        System.out.println("=== Datos de Persona ===");
        System.out.println("ID Persona: " + idPersona);
        System.out.println("DNI: " + dni);
        System.out.println("Primer Nombre: " + primerNombre);
        System.out.println("Segundo Nombre: " + segundoNombre);
        System.out.println("Apellido Paterno: " + apellidoPaterno);
        System.out.println("Apellido Materno: " + apellidoMaterno);
        System.out.println("Dirección: " + direccion);
        System.out.println("Distrito: " + distrito);
        System.out.println("Código Postal: " + codigoPostal);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Correo: " + correo);
    }
}