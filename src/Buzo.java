public class Buzo extends Producto {
    private boolean tieneBolsillos;
    private boolean bolsillosConCierre;

    public Buzo(String codigo, String nombre, double precio, int stock, boolean tieneBolsillos, boolean bolsillosConCierre) {
        super(codigo, nombre, precio, stock);
        this.tieneBolsillos = tieneBolsillos;
        this.bolsillosConCierre = bolsillosConCierre;
    }

    @Override
    public double calcularPrecioVenta(int cantidad) {
        if (cantidad <= 0) {
            System.err.println("El cantidad debe ser mayor a 0");
            return 0;
        }

        double total = getPrecio() * cantidad;
        if (cantidad >= 3) total *= 0.90; // 10 % de descuento por 3 o más buzos
        return total;
    }

    @Override public String getTipo() { return "Buzo"; }
    public boolean isTieneBolsillos() { return tieneBolsillos; }
    public boolean isBolsillosConCierre() { return bolsillosConCierre; }
    public void setTieneBolsillos(boolean tieneBolsillos) { this.tieneBolsillos = tieneBolsillos; }
    public void setBolsillosConCierre(boolean bolsillosConCierre) { this.bolsillosConCierre = bolsillosConCierre; }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("  Bolsillos: " + (tieneBolsillos ? "Sí" : "No")
                + " | Bolsillos con cierre: " + (bolsillosConCierre ? "Sí" : "No"));
    }
}