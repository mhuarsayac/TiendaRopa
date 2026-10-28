import java.util.ArrayList;

public class Venta {
    private String codigo;
    private Vendedor vendedor;
    private ArrayList<DetalleVenta> detalles;

    public Venta(String codigo, Vendedor vendedor) {
        if (codigo == null || codigo.equals("")) {
            System.out.println("El código de venta es obligatorio.");
        } else if (vendedor == null) {
            System.out.println("Debe indicar el vendedor.");
        } else {
            this.codigo = codigo;
            this.vendedor = vendedor;
        }
        this.detalles = new ArrayList<DetalleVenta>();
    }

    public boolean agregarDetalle(DetalleVenta detalle) {
        if (detalle == null || detalle.getProducto() == null) {
            return false;
        }
        if (!detalle.getProducto().disminuirStock(detalle.getCantidad())) {
            return false;
        }
        detalles.add(detalle);
        return true;
    }

    public double calcularTotal() {
        double total = 0;
        for (DetalleVenta detalle : detalles) total += detalle.calcularSubtotal();
        return total;
    }

    public double calcularComision() {
        return vendedor.calcularComision(calcularTotal());
    }

    public void mostrarInformacion() {
        System.out.println("Venta " + codigo + " | Vendedor: " + vendedor.getNombre());
        for (DetalleVenta detalle : detalles) {
            System.out.println("  " + detalle.getProducto().getNombre() + " x" + detalle.getCantidad()
                    + " = S/ " + detalle.calcularSubtotal());
        }
        System.out.println("  TOTAL: S/ " + calcularTotal() + " | Comisión: S/ " + calcularComision());
    }

    public String getCodigo() {
        return codigo;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }
}