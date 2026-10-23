import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static GestorInventario inventario = new GestorInventario();
    private static SistemaVentas sistemaVentas = new SistemaVentas();
    private static ArrayList<Empleado> empleados = new ArrayList<Empleado>();

    public static void main(String[] args) {
        cargarDatosIniciales();
        System.out.println("==================================");
        System.out.println("     NONCONFORMIST - TIENDA");
        System.out.println("==================================");
        System.out.println("Usuarios de prueba:");
        System.out.println("Administrador: admin / admin123");
        System.out.println("Vendedor: venta / venta123");

        boolean continuar = true;
        while (continuar) {
            Empleado empleado = iniciarSesion();

            if (empleado == null) {
                System.out.println("Saliendo del sistema...");
                continuar = false;
            } else {
                ejecutarMenu(empleado);
            }
        }
        sc.close();
    }

    private static void cargarDatosIniciales() {
        empleados.add(new Administrador("E001", "Administrador", "admin", "admin123"));
        empleados.add(new Vendedor("E002", "Lucía Torres", "venta", "venta123", 5.0));

        inventario.registrarProducto(new Polo("P001", "Polo urbano negro", 39.90, 20, "Redondo", "Oversize", true));
        inventario.registrarProducto(new Jean("J001", "Jean azul recto", 89.90, 12, "Recto", false));
        inventario.registrarProducto(new Hoodie("H001", "Hoodie gris", 99.90, 10, true, true));
        inventario.registrarProducto(new Buzo("B001", "Buzo deportivo", 74.90, 15, true, false));
    }

    private static Empleado iniciarSesion() {
        System.out.println("\n--- INICIO DE SESIÓN (escriba SALIR como usuario para terminar) ---");
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        if (usuario.equals("SALIR")) {
            return null;
        }
        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();

        for (Empleado empleado : empleados) {
            if (empleado.getUsuario().equals(usuario) && empleado.getContrasena().equals(contrasena)) {
                System.out.println("Bienvenido/a, " + empleado.getNombre() + ".");
                return empleado;
            }
        }
        System.out.println("Usuario o contraseña incorrectos.");
        return iniciarSesion();
    }

    private static void ejecutarMenu(Empleado empleado) {
        boolean sesionActiva = true;
        while (sesionActiva) {
            empleado.mostrarMenu();
            System.out.print("Seleccione una opcion: ");
            int opcion = Integer.parseInt(sc.nextLine());

            if (empleado.puedeGestionarInventario()) {
                switch (opcion) {
                    case 1:
                        registrarProducto();
                        break;
                    case 2:
                        inventario.listarProductos();
                        break;
                    case 3:
                        buscarProducto();
                        break;
                    case 4:
                        modificarProducto();
                        break;
                    case 5:
                        eliminarProducto();
                        break;
                    case 6:
                        registrarVenta(empleado);
                        break;
                    case 7:
                        sistemaVentas.listarVentas();
                        break;
                    case 8:
                        listarVentasVendedor();
                        break;
                    case 9:
                        sesionActiva = false;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } else {
                Vendedor vendedor = (Vendedor) empleado;
                switch (opcion) {
                    case 1:
                        inventario.listarProductos();
                        break;
                    case 2:
                        buscarProducto();
                        break;
                    case 3:
                        registrarVenta(vendedor);
                        break;
                    case 4:
                        sistemaVentas.listarVentasVendedor(vendedor);
                        break;
                    case 5:
                        System.out.println("Comisión acumulada: S/ " + sistemaVentas.calcularComisionTotal(vendedor));
                        break;
                    case 6:
                        sesionActiva = false;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        }
        System.out.println("Sesión cerrada.");
    }

    private static void registrarProducto() {
        System.out.println("\nTipo de producto: 1.Polo  2.Jean  3.Hoodie  4.Buzo");
        System.out.print("Seleccione: ");
        int tipo = Integer.parseInt(sc.nextLine());
        System.out.print("Codigo: ");
        String codigo = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Precio (S/): ");
        double precio = Double.parseDouble(sc.nextLine());
        System.out.print("Stock inicial: ");
        int stock = Integer.parseInt(sc.nextLine());
        Producto producto;

        switch (tipo) {
            case 1:
                System.out.print("Tipo de cuello: ");
                String cuello = sc.nextLine();
                System.out.print("Tipo de corte: ");
                String cortePolo = sc.nextLine();
                String textoEstampado;
                do {
                    System.out.print("¿Tiene estampado? (s/n): ");
                    textoEstampado = sc.nextLine();
                } while (!textoEstampado.equals("s") && !textoEstampado.equals("n"));
                boolean estampado;
                if (textoEstampado.equals("s")) {
                    estampado = true;
                } else {
                    estampado = false;
                }
                producto = new Polo(codigo, nombre, precio, stock, cuello, cortePolo, estampado);
                break;
            case 2:
                System.out.print("Tipo de corte: ");
                String corteJean = sc.nextLine();
                String textoRasgado;
                do {
                    System.out.print("¿Es rasgado? (s/n): ");
                    textoRasgado = sc.nextLine();
                } while (!textoRasgado.equals("s") && !textoRasgado.equals("n"));
                boolean rasgado;
                if (textoRasgado.equals("s")) {
                    rasgado = true;
                } else {
                    rasgado = false;
                }
                producto = new Jean(codigo, nombre, precio, stock, corteJean, rasgado);
                break;
            case 3:
                String textoCapucha;
                do {
                    System.out.print("¿Tiene capucha? (s/n): ");
                    textoCapucha = sc.nextLine();
                } while (!textoCapucha.equals("s") && !textoCapucha.equals("n"));
                boolean capucha;
                if (textoCapucha.equals("s")) {
                    capucha = true;
                } else {
                    capucha = false;
                }

                String textoCierre;
                do {
                    System.out.print("¿Tiene cierre? (s/n): ");
                    textoCierre = sc.nextLine();
                } while (!textoCierre.equals("s") && !textoCierre.equals("n"));
                boolean cierre;
                if (textoCierre.equals("s")) {
                    cierre = true;
                } else {
                    cierre = false;
                }

                producto = new Hoodie(codigo, nombre, precio, stock, capucha, cierre);
                break;
            case 4:
                String textoBolsillos;
                do {
                    System.out.print("¿Tiene bolsillos? (s/n): ");
                    textoBolsillos = sc.nextLine();
                } while (!textoBolsillos.equals("s") && !textoBolsillos.equals("n"));
                boolean bolsillos;
                if (textoBolsillos.equals("s")) {
                    bolsillos = true;
                } else {
                    bolsillos = false;
                }

                String textoBolsillosCierre;
                do {
                    System.out.print("¿Los bolsillos tienen cierre? (s/n): ");
                    textoBolsillosCierre = sc.nextLine();
                } while (!textoBolsillosCierre.equals("s") && !textoBolsillosCierre.equals("n"));
                boolean bolsillosCierre;
                if (textoBolsillosCierre.equals("s")) {
                    bolsillosCierre = true;
                } else {
                    bolsillosCierre = false;
                }

                producto = new Buzo(codigo, nombre, precio, stock, bolsillos, bolsillosCierre);
                break;
            default:
                System.out.println("Tipo no válido.");
                return;
        }
        if (inventario.registrarProducto(producto)) {
            System.out.println("Producto registrado correctamente.");
        } else {
            System.out.println("Ya existe un producto con ese código.");
        }

    }

    private static void buscarProducto() {
        System.out.print("Codigo del producto: ");
        String codigo = sc.nextLine();
        Producto producto = inventario.buscarProducto(codigo);
        if (producto == null) {
            System.out.println("No se encontró el producto.");
        } else {
            producto.mostrarInformacion();
        }
    }

    private static void modificarProducto() {
        System.out.print("Codigo del producto que desea modificar: ");
        String codigo = sc.nextLine();
        Producto producto = inventario.buscarProducto(codigo);
        if (producto == null) {
            System.out.println("No se encontró el producto.");
            return;
        }

        System.out.println("Producto actual:");
        producto.mostrarInformacion();

        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Nuevo precio: ");
        double precio = Double.parseDouble(sc.nextLine());
        System.out.print("Nuevo stock: ");
        int stock = Integer.parseInt(sc.nextLine());
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setStock(stock);
        System.out.println("Producto modificado correctamente. Se conservaron su código y sus características específicas.");

    }

    private static void eliminarProducto() {
        System.out.print("Codigo del producto que desea eliminar: ");
        String codigo = sc.nextLine();
        if (inventario.eliminarProducto(codigo)) {
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("No se encontró el producto.");
        }
    }

    private static void registrarVenta(Empleado empleado) {
        if (inventario.estaVacio()) {
            System.out.println("No hay productos disponibles.");
            return;
        }
        Vendedor vendedor;
        if (!empleado.puedeGestionarInventario()) {
            vendedor = (Vendedor) empleado;
        } else {
            System.out.println("Vendedores disponibles:");
            for (Empleado e : empleados) {
                if (e instanceof Vendedor) {
                    System.out.println(e.getCodigo() + " - " + e.getNombre());
                }
            }
            System.out.print("Codigo del vendedor que realiza la venta: ");
            String codigoVendedor = sc.nextLine();
            vendedor = buscarVendedorPorCodigo(codigoVendedor);
            if (vendedor == null) {
                System.out.println("No se encontró ese vendedor.");
                return;
            }
        }

        System.out.print("Codigo de la venta: ");
        String codigoVenta = sc.nextLine();
        if (sistemaVentas.buscarVenta(codigoVenta) != null) {
            System.out.println("Ya existe una venta con ese código.");
            return;
        }
        Venta venta;

        venta = new Venta(codigoVenta, vendedor);

        boolean agregarMas = true;
        while (agregarMas) {
            inventario.listarProductos();
            System.out.print("Codigo del producto a vender: ");
            String codigoProducto = sc.nextLine();
            Producto producto = inventario.buscarProducto(codigoProducto);
            if (producto == null) {
                System.out.println("Producto no encontrado.");
            } else {
                System.out.print("Cantidad: ");
                int cantidad = Integer.parseInt(sc.nextLine());

                DetalleVenta detalle = new DetalleVenta(producto, cantidad);
                if (venta.agregarDetalle(detalle)) {
                    System.out.println("Agregado. Subtotal: S/ " + String.format("%.2f", detalle.calcularSubtotal()));
                } else {
                    System.out.println("No hay stock suficiente.");
                }
                
            }

            String textoAgregarMas;
            do {
                System.out.print("¿Agregar otro producto a la venta? (s/n): ");
                textoAgregarMas = sc.nextLine();
            } while (!textoAgregarMas.equals("s") && !textoAgregarMas.equals("n"));

            if (textoAgregarMas.equals("s")) {
                agregarMas = true;
            } else {
                agregarMas = false;
            }

        }

        if (venta.calcularTotal() <= 0) {
            System.out.println("La venta está vacía y no se registrará.");
            return;
        }
        if (sistemaVentas.registrarVenta(venta)) {
            System.out.println("Venta registrada:");
            venta.mostrarInformacion();
        } else {
            System.out.println("No se pudo registrar la venta.");
        }
    }

    private static Vendedor buscarVendedorPorCodigo(String codigo) {
        for (Empleado empleado : empleados) {
            if (!empleado.puedeGestionarInventario() && empleado.getCodigo().equals(codigo)) {
                return (Vendedor) empleado;
            }
        }
        return null;
    }

    private static void listarVentasVendedor() {
        System.out.print("Codigo del vendedor: ");
        String codigo = sc.nextLine();
        Vendedor vendedor = buscarVendedorPorCodigo(codigo);
        if (vendedor == null) {
            System.out.println("No se encontró ese vendedor.");
        } else {
            sistemaVentas.listarVentasVendedor(vendedor);
        }
    }

}
