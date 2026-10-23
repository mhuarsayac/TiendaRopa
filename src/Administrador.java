public class Administrador extends Empleado {
    public Administrador(String codigo, String nombre, String usuario, String contrasena) {
        super(codigo, nombre, usuario, contrasena);
    }

    @Override
    public void mostrarMenu() {
        System.out.println("\n===== MENÚ ADMINISTRADOR =====");
        System.out.println("1. Registrar producto");
        System.out.println("2. Listar productos");
        System.out.println("3. Buscar producto");
        System.out.println("4. Modificar producto");
        System.out.println("5. Eliminar producto");
        System.out.println("6. Registrar venta");
        System.out.println("7. Listar ventas");
        System.out.println("8. Listar ventas de un vendedor");
        System.out.println("9. Cerrar sesión");
    }

    @Override
    public boolean puedeGestionarInventario() {
        return true;
    }
}
