package views;

import controllers.DetalleVentaController;
import controllers.NivelClienteController;
import controllers.PersonaController;
import controllers.VentaController;

import java.util.Scanner;

public class MainView {
    private static final Scanner scanner = new Scanner(System.in);
    private final VentaController ventaController = new VentaController();
    private final DetalleVentaController detalleVentaController = new DetalleVentaController();
    private final PersonaController personaController = new PersonaController();
    private final NivelClienteController nivelClienteController = new NivelClienteController();

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
                System.out.println("1- VENDEDOR / 2 ADMINISTRADOR");
                int opcionPrueba = Integer.parseInt(scanner.nextLine());
                if (opcionPrueba == 1) {
                    ejecutarMenuVendedor();
                } else {
                    ejecutarMenuAdministrador();
                }

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

    private void mostrarMenuAdministrador() {
        System.out.println("\n===== MENÚ ADMINISTRADOR =====");
        System.out.println("1. Listar personas");
        System.out.println("2. Registrar persona");
        System.out.println("3. Registrar producto");
        System.out.println("4. Listar productos");
        System.out.println("5. Buscar producto");
        System.out.println("6. Modificar producto");
        System.out.println("7. Eliminar producto");
        System.out.println("8. Listar ventas");
        System.out.println("9. Registrar venta");
        System.out.println("10. Listar ventas de un vendedor");
        System.out.println("11. Listar detalle de las ventas");
        System.out.println("12. Registrar un detalle de una venta");
        System.out.println("13. Listar niveles");
        System.out.println("14. Registra nivel");
        System.out.println("15. Cerrar sesión");
    }

    private void ejecutarMenuAdministrador() {
        boolean sesionActiva = true;
        while (sesionActiva) {
            mostrarMenuAdministrador();
            System.out.print("Seleccione una opcion: ");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    break;
                case 2:
                    registrarPersona();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    ventaController.mostrarTodos();
                    break;
                case 9:
                    registrarVenta();
                    break;
                case 10:
                    break;
                case 11:
                    detalleVentaController.mostrarTodos();
                    break;
                case 12:
                    registrarDetalleVenta();
                    break;
                case 13:
                    nivelClienteController.mostrarTodos();
                    break;
                case 14:
                    registrarNivel();
                    break;
                case 15:
                    sesionActiva = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        System.out.println("Sesión cerrada.");
    }

    private void registrarPersona() {
        System.out.println("\n--- REGISTRAR PERSONA ---");
        System.out.print("idPersona: ");
        String idPersona = scanner.nextLine();
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Primer nombre: ");
        String primerNombre = scanner.nextLine();
        System.out.print("Segundo nombre: ");
        String segundoNombre = scanner.nextLine();
        System.out.print("Apellido paterno: ");
        String apellidoPaterno = scanner.nextLine();
        System.out.print("Apellido materno: ");
        String apellidoMaterno = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Distrito: ");
        String distrito = scanner.nextLine();
        System.out.print("Código postal: ");
        String codigoPostal = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();

        personaController.registrar(
                idPersona, dni, primerNombre, segundoNombre,
                apellidoPaterno, apellidoMaterno, direccion, distrito,
                codigoPostal, telefono, correo
        );
    }

    private void registrarVenta() {
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

    private void registrarDetalleVenta() {
        System.out.print("\nidDetalle: ");
        int idDetalle = Integer.parseInt(scanner.nextLine());
        System.out.print("idVenta: ");
        int idVenta = Integer.parseInt(scanner.nextLine());
        System.out.print("idVariante: ");
        int idVariante = Integer.parseInt(scanner.nextLine());
        System.out.print("Cantidad: ");
        int cantidad = Integer.parseInt(scanner.nextLine());
        System.out.print("Precio Unitario: ");
        double precioUnitario = Double.parseDouble(scanner.nextLine());
        System.out.print("Subotal: ");
        double subtotal = Double.parseDouble(scanner.nextLine());
        detalleVentaController.registrar(idDetalle, idVenta, idVariante, cantidad, precioUnitario, subtotal);
    }

    private void registrarNivel() {
        System.out.print("\nidNivel: ");
        int idNivel = Integer.parseInt(scanner.nextLine());
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Monto minimo: ");
        double montoMinimo = Double.parseDouble(scanner.nextLine());
        nivelClienteController.registrar(idNivel, nombre, montoMinimo);
    }
}
