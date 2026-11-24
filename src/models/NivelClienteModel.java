package models;

public class NivelClienteModel {
    private int idNivelCliente;
    private String nombre;
    private double montoMinimo;

    public NivelClienteModel(int idNivelCliente, String nombre, double montoMinimo) {
        this.idNivelCliente = idNivelCliente;
        this.nombre = nombre;
        this.montoMinimo = montoMinimo;
    }

    public int getIdNivelCliente() {
        return idNivelCliente;
    }

    public void setIdNivelCliente(int idNivelCliente) {
        this.idNivelCliente = idNivelCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getMontoMinimo() {
        return montoMinimo;
    }

    public void setMontoMinimo(double montoMinimo) {
        this.montoMinimo = montoMinimo;
    }

    public void mostrarInformacion() {
        System.out.println("CODIGO_NIVEL: " + idNivelCliente + " - NOMBRE: " + nombre +
                " - MONTO_MINIMO: " + montoMinimo);
    }
}
