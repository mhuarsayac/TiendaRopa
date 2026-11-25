package views;

import controllers.*;

import java.time.LocalDate;
import java.util.Scanner;

public class MainView {
    private static final Scanner scanner = new Scanner(System.in);
    private final VentaController ventaController = new VentaController();
    private final DetalleVentaController detalleVentaController = new DetalleVentaController();
    private final EmpleadoController empleadoController = new EmpleadoController();
    private final ClienteController clienteController = new ClienteController();
    private final NivelClienteController nivelClienteController = new NivelClienteController();
    private final ProductoController productoController = new ProductoController();

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
                    productoController.mostrarTodos();
                    break;
                case 2:
                    buscarProducto();
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
        System.out.println("2. Registrar empleado");
        System.out.println("3. Listar productos");
        System.out.println("4. Registrar producto");
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
        System.out.println("15. Buscar empleado");
        System.out.println("16. Modificar empleado");
        System.out.println("17. Eliminar empleado");
        System.out.println("18. Registrar cliente");
        System.out.println("19. Buscar cliente");
        System.out.println("20. Modificar cliente");
        System.out.println("21. Eliminar cliente");
        System.out.println("22. Cerrar sesión");
    }

    private void ejecutarMenuAdministrador() {
        boolean sesionActiva = true;
        while (sesionActiva) {
            mostrarMenuAdministrador();
            System.out.print("Seleccione una opcion: ");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    System.out.println("\n--- EMPLEADOS ---");
                    empleadoController.mostrarTodos();
                    System.out.println("\n--- CLIENTES ---");
                    clienteController.mostrarTodos();
                    break;
                case 2:
                    registrarEmpleado();
                    break;
                case 3:
                    productoController.mostrarTodos();
                    break;
                case 4:
                    registrarProducto();
                    break;
                case 5:
                    buscarProducto();
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
                    buscarEmpleado();
                    break;
                case 16:
                    modificarEmpleado();
                    break;
                case 17:
                    eliminarEmpleado();
                    break;
                case 18:
                    registrarCliente();
                    break;
                case 19:
                    buscarCliente();
                    break;
                case 20:
                    modificarCliente();
                    break;
                case 21:
                    eliminarCliente();
                    break;
                case 22:
                    sesionActiva = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        System.out.println("Sesión cerrada.");
    }

    private void registrarEmpleado() {
        System.out.println("--- REGISTRAR EMPLEADO ---");
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

        System.out.print("idEmpleado: ");
        String idEmpleado = scanner.nextLine();
        System.out.print("idUsuario: ");
        int idUsuario = Integer.parseInt(scanner.nextLine());
        System.out.print("Tipo (ADMINISTRADOR / VENDEDOR): ");
        String tipo = scanner.nextLine();
        System.out.print("Salario: ");
        double salario = Double.parseDouble(scanner.nextLine());
        System.out.print("Bono: ");
        double bono = Double.parseDouble(scanner.nextLine());
        System.out.print("Fecha de contratación (AAAA-MM-DD): ");
        LocalDate fechaContratacion = LocalDate.parse(scanner.nextLine());
        System.out.print("Fecha de despedida (AAAA-MM-DD, vacío si no aplica): ");
        String textoDespedida = scanner.nextLine();
        LocalDate fechaDespedida = textoDespedida.isBlank() ? null : LocalDate.parse(textoDespedida);
        System.out.print("Estado (ACTIVO / SUSPENDIDO / DESPEDIDO): ");
        String estado = scanner.nextLine();
        System.out.print("Motivo (vacío si no aplica): ");
        String textoMotivo = scanner.nextLine();
        String motivo = textoMotivo.isBlank() ? null : textoMotivo;

        empleadoController.registrar(
                idPersona, dni, primerNombre, segundoNombre,
                apellidoPaterno, apellidoMaterno, direccion, distrito,
                codigoPostal, telefono, correo,
                idEmpleado, idUsuario, tipo, salario, bono,
                fechaContratacion, fechaDespedida, estado, motivo
        );
    }

    private void buscarEmpleado() {
        System.out.print("\nIngrese el ID del empleado a buscar: ");
        String idEmpleado = scanner.nextLine();
        empleadoController.mostrarPorId(idEmpleado);
    }

    private void modificarEmpleado() {
        System.out.println("--- MODIFICAR EMPLEADO ---");
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

        System.out.print("idEmpleado: ");
        String idEmpleado = scanner.nextLine();
        System.out.print("idUsuario: ");
        int idUsuario = Integer.parseInt(scanner.nextLine());
        System.out.print("Tipo (ADMINISTRADOR / VENDEDOR): ");
        String tipo = scanner.nextLine();
        System.out.print("Salario: ");
        double salario = Double.parseDouble(scanner.nextLine());
        System.out.print("Bono: ");
        double bono = Double.parseDouble(scanner.nextLine());
        System.out.print("Fecha de contratación (AAAA-MM-DD): ");
        LocalDate fechaContratacion = LocalDate.parse(scanner.nextLine());
        System.out.print("Fecha de despedida (AAAA-MM-DD, vacío si no aplica): ");
        String textoDespedida = scanner.nextLine();
        LocalDate fechaDespedida = textoDespedida.isBlank() ? null : LocalDate.parse(textoDespedida);
        System.out.print("Estado (ACTIVO / SUSPENDIDO / DESPEDIDO): ");
        String estado = scanner.nextLine();
        System.out.print("Motivo (vacío si no aplica): ");
        String textoMotivo = scanner.nextLine();
        String motivo = textoMotivo.isBlank() ? null : textoMotivo;

        empleadoController.actualizar(
                idPersona, dni, primerNombre, segundoNombre,
                apellidoPaterno, apellidoMaterno, direccion, distrito,
                codigoPostal, telefono, correo,
                idEmpleado, idUsuario, tipo, salario, bono,
                fechaContratacion, fechaDespedida, estado, motivo
        );
    }

    private void eliminarEmpleado() {
        System.out.print("\nIngrese el ID del empleado a eliminar: ");
        String idEmpleado = scanner.nextLine();
        empleadoController.eliminar(idEmpleado);
    }

    private void registrarCliente() {
        System.out.println("--- REGISTRAR CLIENTE ---");
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

        System.out.print("idCliente: ");
        String idCliente = scanner.nextLine();
        System.out.print("idNivelCliente: ");
        String idNivelCliente = scanner.nextLine();
        System.out.print("Fecha de registro (AAAA-MM-DD): ");
        LocalDate fechaRegistro = LocalDate.parse(scanner.nextLine());

        clienteController.registrar(
                idPersona, dni, primerNombre, segundoNombre,
                apellidoPaterno, apellidoMaterno, direccion, distrito,
                codigoPostal, telefono, correo,
                idCliente, idNivelCliente, fechaRegistro
        );
    }

    private void buscarCliente() {
        System.out.print("\nIngrese el ID del cliente a buscar: ");
        String idCliente = scanner.nextLine();
        clienteController.mostrarPorId(idCliente);
    }

    private void modificarCliente() {
        System.out.println("--- MODIFICAR CLIENTE ---");
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

        System.out.print("idCliente: ");
        String idCliente = scanner.nextLine();
        System.out.print("idNivelCliente: ");
        String idNivelCliente = scanner.nextLine();
        System.out.print("Fecha de registro (AAAA-MM-DD): ");
        LocalDate fechaRegistro = LocalDate.parse(scanner.nextLine());

        clienteController.actualizar(
                idPersona, dni, primerNombre, segundoNombre,
                apellidoPaterno, apellidoMaterno, direccion, distrito,
                codigoPostal, telefono, correo,
                idCliente, idNivelCliente, fechaRegistro
        );
    }

    private void eliminarCliente() {
        System.out.print("\nIngrese el ID del cliente a eliminar: ");
        String idCliente = scanner.nextLine();
        clienteController.eliminar(idCliente);
    }

    private void registrarProducto() {
        System.out.print("\nID Producto: ");
        int idProducto = Integer.parseInt(scanner.nextLine());
        System.out.print("Codigo: ");
        String codigo = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = Double.parseDouble(scanner.nextLine());
        System.out.print("ID Genero: ");
        int idGenero = Integer.parseInt(scanner.nextLine());
        System.out.print("\nID Categoria: ");
        int idCategoria = Integer.parseInt(scanner.nextLine());
        System.out.print("Activo: ");
        String activo = scanner.nextLine();
        productoController.registrar(idProducto, codigo, nombre, descripcion, precio, idGenero, idCategoria, activo);
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
        System.out.print("Activo: ");
        String activo = scanner.nextLine();
        ventaController.registrar(idVenta, idEmpleado, idCliente, fechaHora, total, activo);
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

    private void buscarProducto(){
        System.out.print("\nIngrese el ID del producto a buscar: ");
        int idProductoBuscado = Integer.parseInt(scanner.nextLine());
        productoController.mostrarProductoPorId(idProductoBuscado);
    }
}
