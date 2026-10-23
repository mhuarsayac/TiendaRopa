public class Jean extends Producto {
    private String tipoCorte;
    private boolean esRasgado;

    public Jean(String codigo, String nombre, double precio, int stock, String tipoCorte, boolean esRasgado) {
        super(codigo, nombre, precio, stock);
        this.tipoCorte = tipoCorte;
        this.esRasgado = esRasgado;
    }

    @Override
    public double calcularPrecioVenta(int cantidad) {
        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor que 0.");
            return 0;
        }

        double total = getPrecio() * cantidad;
        if (cantidad >= 2) total *= 0.90; // 10 % de descuento por 2 o más jeans
        return total;
    }

    @Override public String getTipo() { return "Jean"; }
    public String getTipoCorte() { return tipoCorte; }
    public boolean isEsRasgado() { return esRasgado; }

    public void setTipoCorte(String tipoCorte) {
        if (tipoCorte == null || tipoCorte.equals("")){
            System.out.println("El tipo de corte no puede ser vacio.");
        }
        this.tipoCorte = tipoCorte;
    }
    public void setEsRasgado(boolean esRasgado) { this.esRasgado = esRasgado; }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("  Corte: " + tipoCorte + " | Rasgado: " + (esRasgado ? "Sí" : "No"));
    }
}