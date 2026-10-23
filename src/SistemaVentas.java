import java.util.ArrayList;

public class SistemaVentas {
    private ArrayList<Venta> ventas;

    public SistemaVentas() {
        ventas = new ArrayList<Venta>();
    }

    public boolean registrarVenta(Venta venta) {
        if (venta == null || buscarVenta(venta.getCodigo()) != null){
            return false;
        }
        ventas.add(venta);
        return true;
    }

    public Venta buscarVenta(String codigo) {
        if (codigo == null){
            return null;
        }
        for (Venta venta : ventas) {
            if (venta.getCodigo().equals(codigo)){
                return venta;
            }
        }
        return null;
    }

    public void listarVentas() {
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }
        for (Venta venta : ventas){
            venta.mostrarInformacion();
        }
    }

    public void listarVentasVendedor(Vendedor vendedor) {
        boolean encontrado = false;
        for (Venta venta : ventas) {
            if (venta.getVendedor().getCodigo().equals(vendedor.getCodigo())) {
                venta.mostrarInformacion();
                encontrado = true;
            }
        }
        if (!encontrado){
            System.out.println("Este vendedor aún no tiene ventas.");
        }
    }

    public double calcularComisionTotal(Vendedor vendedor) {
        double total = 0;
        for (Venta venta : ventas) {
            if (venta.getVendedor().getCodigo().equals(vendedor.getCodigo())) {
                total += venta.calcularComision();
            }
        }
        return total;
    }
}
