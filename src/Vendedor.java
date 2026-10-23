public class Vendedor extends Empleado {
    private double porcentajeComision;

    public Vendedor(String codigo, String nombre, String usuario, String contrasena, double porcentajeComision) {
        super(codigo, nombre, usuario, contrasena);
        this.porcentajeComision = porcentajeComision;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("\n===== MENÚ VENDEDOR =====");
        System.out.println("1. Listar productos");
        System.out.println("2. Buscar producto");
        System.out.println("3. Registrar venta");
        System.out.println("4. Ver mis ventas");
        System.out.println("5. Ver mi comisión total");
        System.out.println("6. Cerrar sesión");
    }

    @Override
    public boolean puedeGestionarInventario() {
        return false;
    }

    public double calcularComision(double montoVenta) {

        if (montoVenta < 0) {
            System.out.println("\nEl monto no puede ser negativo");
        } else {
            return montoVenta * porcentajeComision / 100.0;
        }
        return 0;
    }

    public double getPorcentajeComision() {
        return porcentajeComision;
    }

    public void setPorcentajeComision(double porcentajeComision) {
        if (porcentajeComision < 0 || porcentajeComision > 100) {
            System.out.println("\nLa comision debe estar entre 0 y 100");
        } else {
            this.porcentajeComision = porcentajeComision;
        }
    }
}
