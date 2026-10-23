import java.util.ArrayList;

public class GestorInventario implements GestionProductos {
    private ArrayList<Producto> productos;

    public GestorInventario() {
        productos = new ArrayList<Producto>();
    }

    @Override
    public boolean registrarProducto(Producto producto) {
        if (producto == null || buscarProducto(producto.getCodigo()) != null) {
            return false;
        }
        productos.add(producto);
        return true;
    }

    @Override
    public Producto buscarProducto(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                return producto;
            }
        }
        return null;
    }

    @Override
    public boolean modificarProducto(String codigo, Producto nuevoProducto) {
        if (codigo == null || nuevoProducto == null) {
            return false;
        }
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo().equals(codigo)) {
                productos.set(i, nuevoProducto);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminarProducto(String codigo) {
        Producto producto = buscarProducto(codigo);
        if (producto == null) {
            return false;
        }
        productos.remove(producto);
        return true;
    }

    @Override
    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        for (Producto producto : productos) {
            producto.mostrarInformacion();
        }
    }

    public boolean estaVacio() {
        return productos.isEmpty();
    }
}
