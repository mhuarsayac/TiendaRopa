public class Polo extends Producto {
    private String tipoCuello;
    private String tipoCorte;
    private boolean tieneEstampado;

    public Polo(String codigo, String nombre, double precio, int stock, String tipoCuello, String tipoCorte, boolean tieneEstampado) {
        super(codigo, nombre, precio, stock);
        this.tipoCuello = tipoCuello;
        this.tipoCorte = tipoCorte;
        this.tieneEstampado = tieneEstampado;
    }

    private boolean validarCantidad(int cantidad) {
        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor a 0");
            return false;
        }
        return true;
    }

    @Override
    public double calcularPrecioVenta(int cantidad) {
        if (!validarCantidad(cantidad)) {
            return 0.0;
        }
        double total = getPrecio() * cantidad;
        if (cantidad >= 3) total *= 0.95; // 5 % de descuento por 3 o más polos
        return total;
    }


    @Override public String getTipo() { return "Polo"; }
    public String getTipoCuello() { return tipoCuello; }
    public String getTipoCorte() { return tipoCorte; }
    public boolean isTieneEstampado() { return tieneEstampado; }

    public void setTipoCuello(String tipoCuello) {
        if (tipoCuello == null || tipoCuello.equals("")) {
            System.out.println("Indique el tipo de cuello.");
            return;
        }
        this.tipoCuello = tipoCuello;
    }

    public void setTipoCorte(String tipoCorte) {
        if (tipoCorte == null || tipoCorte.equals("")) {
            System.out.println("Indique el tipo de corte.");
            return;
        }
        this.tipoCorte = tipoCorte;
    }

    public void setTieneEstampado(boolean tieneEstampado) { this.tieneEstampado = tieneEstampado; }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("  Cuello: " + tipoCuello + " | Corte: " + tipoCorte + " | Estampado: " + (tieneEstampado ? "Sí" : "No"));
    }
}