package views;

import controllers.VentaController;

import java.util.Scanner;

public class MainView {
    private static final Scanner scanner = new Scanner(System.in);
    private final VentaController ventaController = new VentaController();

    public void ejecutar() {
        System.out.println("==================================");
        System.out.println("     NONCONFORMIST - TIENDA");
        System.out.println("==================================");
        System.out.println("Usuarios de prueba:");
        System.out.println("Administrador: admin / admin123");
        System.out.println("Vendedor: venta / venta123");

        boolean continuar = true;
        while (continuar) {
            boolean inicioExitoso = iniciarSesion();
            if (inicioExitoso == false) {
                System.out.println("Saliendo del sistema...");
                continuar = false;
            } else {
                ejecutarMenuVendedor();
            }
        }
        scanner.close();
    }

    private static boolean iniciarSesion() {
        System.out.println("\n--- INICIO DE SESIÓN (escriba SALIR como usuario para terminar) ---");
        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();
        if (usuario.equals("SALIR")) {
            return false;
        }
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();

        return true;
    }

    private void mostrarMenuVendedor() {
        System.out.println("\n===== MENÚ VENDEDOR =====");
        System.out.println("1. Listar productos");
        System.out.println("2. Buscar producto");
        System.out.println("3. Registrar venta");
        System.out.println("4. Ver mis ventas");
        System.out.println("5. Ver mi comisión total");
        System.out.println("6. Cerrar sesión");
    }

    private void ejecutarMenuVendedor() {
        boolean sesionActiva = true;
        while (sesionActiva) {
            mostrarMenuVendedor();
            System.out.print("Seleccione una opcion: ");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    registrarVenta();
                    break;
                case 4:
                    ventaController.mostrarTodos();
                    break;
                case 5:
                    break;
                case 6:
                    sesionActiva = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        System.out.println("Sesión cerrada.");
    }

    private void registrarVenta(){
        System.out.print("\nidVenta: ");
        int idVenta = Integer.parseInt(scanner.nextLine());
        System.out.print("idEmpleado: ");
        String idEmpleado = scanner.nextLine();
        System.out.print("idCliente: ");
        String idCliente = scanner.nextLine();
        System.out.print("Fecha: ");
        String fechaHora = scanner.nextLine();
        System.out.print("Total: ");
        double total = Double.parseDouble(scanner.nextLine());
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        ventaController.registrar(idVenta, idEmpleado, idCliente, fechaHora, total, estado);
    }
}
