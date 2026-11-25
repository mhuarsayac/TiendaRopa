package models;

public class GeneroModel {
    private int idGenero;
    private String nombre;

    public GeneroModel(int idGenero, String nombre) {
        this.idGenero = idGenero;
        this.nombre = nombre;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void mostrarInformacion() {
        System.out.println("CODIGO_GENERO: " + idGenero + " - NOMBRE: " + nombre);
    }
}
