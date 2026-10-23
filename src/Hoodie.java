public class Hoodie extends Producto {
    private boolean tieneCapucha;
    private boolean tieneCierre;

    public Hoodie(String codigo, String nombre, double precio, int stock,
                  boolean tieneCapucha, boolean tieneCierre) {
        super(codigo, nombre, precio, stock);
        this.tieneCapucha = tieneCapucha;
        this.tieneCierre = tieneCierre;
    }

    @Override
    public double calcularPrecioVenta(int cantidad) {
        if (cantidad <= 0) {
            System.out.println("El cantidad debe ser mayor que 0");
            return 0;
        }
        double total = getPrecio() * cantidad;
        if (cantidad >= 2) total *= 0.85; // 15 % de descuento por 2 o más hoodies
        return total;
    }

    @Override
    public String getTipo() {
        return "Hoodie";
    }

    public boolean isTieneCapucha() {
        return tieneCapucha;
    }

    public boolean isTieneCierre() {
        return tieneCierre;
    }

    public void setTieneCapucha(boolean tieneCapucha) {
        this.tieneCapucha = tieneCapucha;
    }

    public void setTieneCierre(boolean tieneCierre) {
        this.tieneCierre = tieneCierre;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("  Capucha: " + (tieneCapucha ? "Sí" : "No") + " | Cierre: " + (tieneCierre ? "Sí" : "No"));
    }
}