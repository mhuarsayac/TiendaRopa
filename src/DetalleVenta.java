public class DetalleVenta {
    private Producto producto;
    private int cantidad;
    private double subtotal;

    public DetalleVenta(Producto producto, int cantidad) {
        boolean verificado = true;
        if (producto == null) {
            System.out.println("\nDebe seleccionar un producto");
            verificado = false;
        }
        if (cantidad <= 0) {
            System.out.println("\nLa cantidad debe ser mayor que cero");
            verificado = false;
        }
        if (cantidad > producto.getStock()) {
            System.out.println("\nNo ahy stock suficiente");
            verificado = false;
        }

        if (verificado) {
            this.producto = producto;
            this.cantidad = cantidad;
            this.subtotal = producto.calcularPrecioVenta(cantidad);
        }
    }

    public double calcularSubtotal() {
        return subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }
}
