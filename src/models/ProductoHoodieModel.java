package models;

public class ProductoHoodieModel extends ProductoModel {
    private static final int PORCENTAJE_DESCUENTO = 15;
    private static final int CANTIDAD_MINIMA = 2;

    public ProductoHoodieModel(int idProducto, String codigo, String nombre, String descripcion, double precio, int idGenero, int idCategoria, String activo) {
        super(idProducto, codigo, nombre, descripcion, precio, idGenero, idCategoria, activo);
    }

    @Override
    public double calcularPrecioVenta(int cantidad) {
        if (cantidad <= 0) {
            System.err.println("El cantidad debe ser mayor a 0");
            return -1;
        }
        double subtotal = precio * cantidad;
        double descuentoCalculado = 0;
        if (cantidad >= CANTIDAD_MINIMA) {
            descuentoCalculado = subtotal * PORCENTAJE_DESCUENTO / 100;
        }
        return redondear(subtotal - descuentoCalculado);
    }
}
