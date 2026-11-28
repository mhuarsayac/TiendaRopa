package views;

import controllers.*;
import models.EmpleadoModel;
import services.DatabaseConnector;
import utils.Validador;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MainView {
    private static final Scanner scanner = new Scanner(System.in);
    private final VentaController ventaController = new VentaController();
    private final DetalleVentaController detalleVentaController = new DetalleVentaController();
    private final EmpleadoController empleadoController = new EmpleadoController();
    private final ClienteController clienteController = new ClienteController();
    private final NivelClienteController nivelClienteController = new NivelClienteController();
    private final ProductoController productoController = new ProductoController();
    private final LoginController loginController = new LoginController();

    public void ejecutar() {
        System.out.println("==================================");
        System.out.println("     NONCONFORMIST - TIENDA");
        System.out.println("==================================");

        if (!DatabaseConnector.probarConexion()) {
            System.out.println("No hay conexion con la base de datos. Revise MySQL y DatabaseConnector");
            scanner.close();
            return;
        }

        System.out.println("Conexion a la base de datos: OK");

        EmpleadoModel empleado = null;
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- INICIO DE SESIÓN (escriba SALIR como usuario para terminar) ---");
            System.out.print("Usuario: ");
            String usuario = leerTextoNoVacio();

            if (usuario.equalsIgnoreCase("SALIR")) {
                salir = true;
                continue;
            }

            System.out.print("Contraseña: ");
            String contrasena = leerTextoNoVacio();

            empleado = loginController.iniciarSesion(usuario, contrasena);

            if (empleado != null) {
                System.out.println("\nBienvenido, " + empleado.getPrimerNombre() + " (" + empleado.getTipo() + ")");

                if (empleado.getTipo().equalsIgnoreCase("ADMINISTRADOR")) {
                    ejecutarMenuAdministrador();
                } else {
                    ejecutarMenuVendedor();
                }

                salir = true;
            }
        }
        scanner.close();
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
            int opcion = leerEntero();

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
            int opcion = leerEntero();

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
        String idPersona = leerId();
        System.out.print("DNI: ");
        String dni = leerDni();
        System.out.print("Primer nombre: ");
        String primerNombre = leerSoloLetras();
        System.out.print("Segundo nombre: ");
        String segundoNombre = leerSoloLetrasOpcional();
        System.out.print("Apellido paterno: ");
        String apellidoPaterno = leerSoloLetras();
        System.out.print("Apellido materno: ");
        String apellidoMaterno = leerSoloLetras();
        System.out.print("Dirección: ");
        String direccion = leerTextoNoVacio();
        System.out.print("Distrito: ");
        String distrito = leerSoloLetras();
        System.out.print("Código postal: ");
        String codigoPostal = leerCodigoPostal();
        System.out.print("Teléfono: ");
        String telefono = leerTelefono();
        System.out.print("Correo: ");
        String correo = leerCorreo();

        System.out.print("idEmpleado: ");
        String idEmpleado = leerId();
        System.out.print("idUsuario: ");
        int idUsuario = leerEnteroPositivo();
        System.out.print("Tipo (ADMINISTRADOR / VENDEDOR): ");
        String tipo = leerDeLista("ADMINISTRADOR", "VENDEDOR");
        System.out.print("Salario: ");
        double salario = leerDecimalPositivo();
        System.out.print("Bono: ");
        double bono = leerDecimal();
        System.out.print("Fecha de contratación (AAAA-MM-DD): ");
        LocalDate fechaContratacion = leerFecha();
        System.out.print("Fecha de despedida (AAAA-MM-DD, vacío si no aplica): ");
        String textoDespedida = leerFechaDespedida(fechaContratacion);
        LocalDate fechaDespedida = textoDespedida.isBlank() ? null : LocalDate.parse(textoDespedida);
        System.out.print("Estado (ACTIVO / SUSPENDIDO / DESPEDIDO): ");
        String estado = leerDeLista("ACTIVO", "SUSPENDIDO", "DESPEDIDO");
        System.out.print("Motivo (vacío si no aplica): ");
        String textoMotivo = leerTextoOpcional();
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
        String idEmpleado = leerId();
        empleadoController.mostrarPorId(idEmpleado);
    }

    private void modificarEmpleado() {
        System.out.println("--- MODIFICAR EMPLEADO ---");
        System.out.print("idPersona: ");
        String idPersona = leerId();
        System.out.print("DNI: ");
        String dni = leerDni();
        System.out.print("Primer nombre: ");
        String primerNombre = leerSoloLetras();
        System.out.print("Segundo nombre: ");
        String segundoNombre = leerSoloLetrasOpcional();
        System.out.print("Apellido paterno: ");
        String apellidoPaterno = leerSoloLetras();
        System.out.print("Apellido materno: ");
        String apellidoMaterno = leerSoloLetras();
        System.out.print("Dirección: ");
        String direccion = leerTextoNoVacio();
        System.out.print("Distrito: ");
        String distrito = leerSoloLetras();
        System.out.print("Código postal: ");
        String codigoPostal = leerCodigoPostal();
        System.out.print("Teléfono: ");
        String telefono = leerTelefono();
        System.out.print("Correo: ");
        String correo = leerCorreo();

        System.out.print("idEmpleado: ");
        String idEmpleado = leerId();
        System.out.print("idUsuario: ");
        int idUsuario = leerEnteroPositivo();
        System.out.print("Tipo (ADMINISTRADOR / VENDEDOR): ");
        String tipo = leerDeLista("ADMINISTRADOR", "VENDEDOR");
        System.out.print("Salario: ");
        double salario = leerDecimalPositivo();
        System.out.print("Bono: ");
        double bono = leerDecimal();
        System.out.print("Fecha de contratación (AAAA-MM-DD): ");
        LocalDate fechaContratacion = leerFecha();
        System.out.print("Fecha de despedida (AAAA-MM-DD, vacío si no aplica): ");
        String textoDespedida = leerFechaDespedida(fechaContratacion);
        LocalDate fechaDespedida = textoDespedida.isBlank() ? null : LocalDate.parse(textoDespedida);
        System.out.print("Estado (ACTIVO / SUSPENDIDO / DESPEDIDO): ");
        String estado = leerDeLista("ACTIVO", "SUSPENDIDO", "DESPEDIDO");
        System.out.print("Motivo (vacío si no aplica): ");
        String textoMotivo = leerTextoOpcional();
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
        String idEmpleado = leerId();
        empleadoController.eliminar(idEmpleado);
    }

    private void registrarCliente() {
        System.out.println("--- REGISTRAR CLIENTE ---");
        System.out.print("idPersona: ");
        String idPersona = leerId();
        System.out.print("DNI: ");
        String dni = leerDni();
        System.out.print("Primer nombre: ");
        String primerNombre = leerSoloLetras();
        System.out.print("Segundo nombre: ");
        String segundoNombre = leerSoloLetrasOpcional();
        System.out.print("Apellido paterno: ");
        String apellidoPaterno = leerSoloLetras();
        System.out.print("Apellido materno: ");
        String apellidoMaterno = leerSoloLetras();
        System.out.print("Dirección: ");
        String direccion = leerTextoNoVacio();
        System.out.print("Distrito: ");
        String distrito = leerSoloLetras();
        System.out.print("Código postal: ");
        String codigoPostal = leerCodigoPostal();
        System.out.print("Teléfono: ");
        String telefono = leerTelefono();
        System.out.print("Correo: ");
        String correo = leerCorreo();

        System.out.print("idCliente: ");
        String idCliente = leerId();
        System.out.print("idNivelCliente: ");
        String idNivelCliente = leerId();
        System.out.print("Fecha de registro (AAAA-MM-DD): ");
        LocalDate fechaRegistro = leerFechaNoFutura();

        clienteController.registrar(
                idPersona, dni, primerNombre, segundoNombre,
                apellidoPaterno, apellidoMaterno, direccion, distrito,
                codigoPostal, telefono, correo,
                idCliente, idNivelCliente, fechaRegistro
        );
    }

    private void buscarCliente() {
        System.out.print("\nIngrese el ID del cliente a buscar: ");
        String idCliente = leerId();
        clienteController.mostrarPorId(idCliente);
    }

    private void modificarCliente() {
        System.out.println("--- MODIFICAR CLIENTE ---");
        System.out.print("idPersona: ");
        String idPersona = leerId();
        System.out.print("DNI: ");
        String dni = leerDni();
        System.out.print("Primer nombre: ");
        String primerNombre = leerSoloLetras();
        System.out.print("Segundo nombre: ");
        String segundoNombre = leerSoloLetrasOpcional();
        System.out.print("Apellido paterno: ");
        String apellidoPaterno = leerSoloLetras();
        System.out.print("Apellido materno: ");
        String apellidoMaterno = leerSoloLetras();
        System.out.print("Dirección: ");
        String direccion = leerTextoNoVacio();
        System.out.print("Distrito: ");
        String distrito = leerSoloLetras();
        System.out.print("Código postal: ");
        String codigoPostal = leerCodigoPostal();
        System.out.print("Teléfono: ");
        String telefono = leerTelefono();
        System.out.print("Correo: ");
        String correo = leerCorreo();

        System.out.print("idCliente: ");
        String idCliente = leerId();
        System.out.print("idNivelCliente: ");
        String idNivelCliente = leerId();
        System.out.print("Fecha de registro (AAAA-MM-DD): ");
        LocalDate fechaRegistro = leerFechaNoFutura();

        clienteController.actualizar(
                idPersona, dni, primerNombre, segundoNombre,
                apellidoPaterno, apellidoMaterno, direccion, distrito,
                codigoPostal, telefono, correo,
                idCliente, idNivelCliente, fechaRegistro
        );
    }

    private void eliminarCliente() {
        System.out.print("\nIngrese el ID del cliente a eliminar: ");
        String idCliente = leerId();
        clienteController.eliminar(idCliente);
    }

    private void registrarProducto() {
        System.out.print("\nID Producto: ");
        int idProducto = leerEnteroPositivo();
        System.out.print("Codigo: ");
        String codigo = leerId();
        System.out.print("Nombre: ");
        String nombre = leerTextoNoVacio();
        System.out.print("Descripcion: ");
        String descripcion = leerTextoNoVacio();
        System.out.print("Precio: ");
        double precio = leerDecimalPositivo();
        System.out.print("ID Genero: ");
        int idGenero = leerEnteroPositivo();
        System.out.print("\nID Categoria: ");
        int idCategoria = leerEnteroPositivo();
        System.out.print("Activo (ACTIVO / INACTIVO): ");
        String activo = leerActivo();
        productoController.registrar(idProducto, codigo, nombre, descripcion, precio, idGenero, idCategoria, activo);
    }

    private void registrarVenta() {
        System.out.print("\nidVenta: ");
        int idVenta = leerEnteroPositivo();
        System.out.print("idEmpleado: ");
        String idEmpleado = leerId();
        System.out.print("idCliente: ");
        String idCliente = leerId();
        System.out.print("Fecha (AAAA-MM-DD, no futura): ");
        String fechaHora = leerFechaNoFutura().toString();
        System.out.print("Total (mayor que 0): ");
        double total = leerDecimalPositivo();
        System.out.print("Activo (ACTIVO / INACTIVO): ");
        String activo = leerActivo();
        ventaController.registrar(idVenta, idEmpleado, idCliente, fechaHora, total, activo);
    }

    private void registrarDetalleVenta() {
        System.out.print("\nidDetalle: ");
        int idDetalle = leerEnteroPositivo();
        System.out.print("idVenta: ");
        int idVenta = leerEnteroPositivo();
        System.out.print("idVariante: ");
        int idVariante = leerEnteroPositivo();
        System.out.print("Cantidad: ");
        int cantidad = leerEnteroPositivo();
        System.out.print("Precio Unitario: ");
        double precioUnitario = leerDecimalPositivo();
        double subtotal = Math.round(cantidad * precioUnitario * 100.0) / 100.0;
        System.out.println("Subtotal calculado: " + subtotal);
        detalleVentaController.registrar(idDetalle, idVenta, idVariante, cantidad, precioUnitario, subtotal);
    }

    private void registrarNivel() {
        System.out.print("\nidNivel: ");
        int idNivel = leerEnteroPositivo();
        System.out.print("Nombre: ");
        String nombre = leerTextoNoVacio();
        System.out.print("Monto minimo: ");
        double montoMinimo = leerDecimal();
        nivelClienteController.registrar(idNivel, nombre, montoMinimo);
    }

    private void buscarProducto() {
        System.out.print("\nIngrese el ID del producto a buscar: ");
        int idProductoBuscado = leerEnteroPositivo();
        productoController.mostrarProductoPorId(idProductoBuscado);
    }

    // =====================================================================
    //                      MÉTODOS DE VALIDACIÓN
    // =====================================================================
    private static final String REGEX_LETRAS = "[A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+( [A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+)*";
    private static final String REGEX_CORREO = "^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$";

    //Lee una línea y exige que NO esté vacía.
    private static String leerTextoNoVacio() {
        while (true) {
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) return texto;
            System.out.print("  [!] No puede estar vacío. Intente de nuevo: ");
        }
    }

    //Lee texto que puede quedar vacío (devuelve "" si está vacío).
    private static String leerTextoOpcional() {
        return scanner.nextLine().trim();
    }

    //Solo letras (con tildes, ñ) y espacios: nombres, apellidos, distrito.
    private static String leerSoloLetras() {
        while (true) {
            String texto = scanner.nextLine().trim();
            if (texto.matches(REGEX_LETRAS)) return texto;
            System.out.print("  [!] Solo se permiten letras (sin números ni símbolos). Intente de nuevo: ");
        }
    }

    //Solo letras, pero permite dejarlo vacío (segundo nombre).
    private static String leerSoloLetrasOpcional() {
        while (true) {
            String texto = scanner.nextLine().trim();
            if (texto.isEmpty() || texto.matches(REGEX_LETRAS)) return texto;
            System.out.print("  [!] Solo se permiten letras o deje vacío. Intente de nuevo: ");
        }
    }


    //Identificadores: letras y números, sin espacios (ej. E1, C2, P10).
    private static String leerId() {
        while (true) {
            String texto = scanner.nextLine().trim();
            if (texto.matches("[A-Za-z0-9]+")) return texto;
            System.out.print("  [!] Solo letras y números, sin espacios. Intente de nuevo: ");
        }
    }

    //DNI peruano: exactamente 8 dígitos.
    private static String leerDni() {
        while (true) {
            String texto = scanner.nextLine().trim();
            if (texto.matches("\\d{8}")) return texto;
            System.out.print("  [!] El DNI debe tener exactamente 8 números. Intente de nuevo: ");
        }
    }

    //Teléfono móvil peruano: 9 dígitos y empieza con 9.
    private static String leerTelefono() {
        while (true) {
            String texto = scanner.nextLine().trim();
            if (texto.matches("9\\d{8}")) return texto;
            System.out.print("  [!] El teléfono debe tener 9 números y empezar con 9. Intente de nuevo: ");
        }
    }

    //Código postal: solo 5 dígitos (formato de Perú).
    private static String leerCodigoPostal() {
        while (true) {
            String texto = scanner.nextLine().trim();
            if (texto.matches("\\d{5}")) return texto;
            System.out.print("  [!] El código postal debe tener 5 números. Intente de nuevo: ");
        }
    }

    //Correo con formato usuario@dominio.com
    private static String leerCorreo() {
        while (true) {
            String texto = scanner.nextLine().trim();
            if (texto.matches(REGEX_CORREO)) return texto;
            System.out.print("  [!] Correo inválido (ej: nombre@correo.com). Intente de nuevo: ");
        }
    }

    //Número entero positivo (0 o más).
    private static int leerEntero() {
        while (true) {
            String texto = scanner.nextLine().trim();
            if (texto.matches("\\d{1,9}")) return Integer.parseInt(texto);
            System.out.print("  [!] Ingrese solo números enteros (sin letras ni decimales). Intente de nuevo: ");
        }
    }

    // Número decimal positivo (acepta 10.50 o 10,50).
    private static double leerDecimal() {
        while (true) {
            String texto = scanner.nextLine().trim().replace(',', '.');
            if (texto.matches("\\d+(\\.\\d{1,2})?")) return Double.parseDouble(texto);
            System.out.print("  [!] Ingrese un número válido (ej: 25 o 25.50). Intente de nuevo: ");
        }
    }

    //Entero de 1 en adelante (ids y cantidades).
    private static int leerEnteroPositivo() {
        while (true) {
            int numero = leerEntero();
            if (Validador.esEnteroPositivo(numero)) return numero;
            System.out.print("  [!] Debe ser un número mayor que 0. Intente de nuevo: ");
        }
    }

    //Decimal MAYOR que 0 (total, precio, salario). Rechaza 0 y negativos.
    private static double leerDecimalPositivo() {
        while (true) {
            double numero = leerDecimal();
            if (Validador.esPositivo(numero)) return numero;
            System.out.print("  [!] Debe ser mayor que 0. Intente de nuevo: ");
        }
    }

    //ACTIVO o INACTIVO (usuario, producto, venta).
    private static String leerActivo() {
        return leerDeLista(Validador.ESTADOS_ACTIVO);
    }

    //Fecha real que no sea futura.
    private static LocalDate leerFechaNoFutura() {
        while (true) {
            LocalDate fecha = leerFecha();
            if (Validador.esFechaNoFutura(fecha)) return fecha;
            System.out.print("  [!] La fecha no puede ser futura. Intente de nuevo: ");
        }
    }

    //Fecha de despedida: vacía, o válida y no anterior a la contratación.
    private static String leerFechaDespedida(LocalDate contratacion) {
        while (true) {
            String texto = leerFechaOpcional();
            if (texto.isEmpty() || !LocalDate.parse(texto).isBefore(contratacion)) return texto;
            System.out.print("  [!] No puede ser anterior a la contratación (" + contratacion + "). Intente de nuevo: ");
        }
    }

    //Fecha en formato AAAA-MM-DD.
    private static LocalDate leerFecha() {
        while (true) {
            String texto = scanner.nextLine().trim();
            try {
                return LocalDate.parse(texto);
            } catch (DateTimeParseException e) {
                System.out.print("  [!] Fecha inválida. Use AAAA-MM-DD (ej: 2026-11-25). Intente de nuevo: ");
            }
        }
    }

    //Igual que leerFecha pero devuelve el texto validado.
    private static String leerFechaTexto() {
        return leerFecha().toString();
    }


    //Fecha opcional: devuelve "" si se deja vacío, o una fecha válida.
    private static String leerFechaOpcional() {
        while (true) {
            String texto = scanner.nextLine().trim();
            if (texto.isEmpty()) return "";
            try {
                return LocalDate.parse(texto).toString();
            } catch (DateTimeParseException e) {
                System.out.print("  [!] Fecha inválida. Use AAAA-MM-DD o deje vacío. Intente de nuevo: ");
            }
        }
    }

    //Acepta solo una de las opciones indicadas (sin importar mayúsculas).
    private static String leerDeLista(String... opciones) {
        while (true) {
            String texto = scanner.nextLine().trim().toUpperCase();
            for (String opcion : opciones) {
                if (opcion.equals(texto)) return opcion;
            }
            System.out.print("  [!] Opción inválida. Elija: " + String.join(" / ", opciones) + ": ");
        }
    }
}
