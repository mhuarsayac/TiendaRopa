public interface GestionProductos {
    boolean registrarProducto(Producto producto);
    Producto buscarProducto(String codigo);
    boolean modificarProducto(String codigo, Producto producto);
    boolean eliminarProducto(String codigo);
    void listarProductos();
}
