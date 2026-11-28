package utils;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Reglas de validación del negocio, compartidas por las vistas y los controladores.
 * Así, aunque una vista falle, el controlador igual rechaza datos inválidos (ej. total = -1).
 */
public final class Validador {

    private Validador() { }

    // ---------------------------------------------------------------
    // VALORES PERMITIDOS
    // ---------------------------------------------------------------
    public static final String ACTIVO = "ACTIVO";
    public static final String INACTIVO = "INACTIVO";
    /** Estados válidos para: usuario.activo, producto.activo, venta.estado */
    public static final String[] ESTADOS_ACTIVO = {ACTIVO, INACTIVO};

    public static final String[] TIPOS_EMPLEADO = {"ADMINISTRADOR", "VENDEDOR"};
    public static final String[] ESTADOS_EMPLEADO = {"ACTIVO", "SUSPENDIDO", "DESPEDIDO"};

    // ---------------------------------------------------------------
    // NÚMEROS
    // ---------------------------------------------------------------
    /** Mayor que 0 (total, precio, salario, monto...). Rechaza 0, negativos, NaN e infinito. */
    public static boolean esPositivo(double valor) {
        return !Double.isNaN(valor) && !Double.isInfinite(valor) && valor > 0;
    }

    /** 0 o más (bono, monto mínimo...). */
    public static boolean esNoNegativo(double valor) {
        return !Double.isNaN(valor) && !Double.isInfinite(valor) && valor >= 0;
    }

    /** Entero de 1 en adelante (ids, cantidades). */
    public static boolean esEnteroPositivo(int valor) {
        return valor >= 1;
    }

    // ---------------------------------------------------------------
    // TEXTO / FORMATOS
    // ---------------------------------------------------------------
    public static boolean noVacio(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    public static boolean estaEn(String valor, String... permitidos) {
        if (valor == null) return false;
        return Arrays.stream(permitidos).anyMatch(p -> p.equalsIgnoreCase(valor.trim()));
    }

    public static boolean esEstadoActivoValido(String valor) {
        return estaEn(valor, ESTADOS_ACTIVO);
    }

    /** ¿El texto guardado en la BD significa "activo"? (tolera variantes comunes) */
    public static boolean esActivo(String valor) {
        return estaEn(valor, ACTIVO, "A", "S", "SI", "SÍ", "1", "TRUE");
    }

    public static boolean esDni(String dni) {
        return dni != null && dni.matches("\\d{8}");
    }

    /** Móvil peruano: 9 dígitos y empieza con 9. */
    public static boolean esTelefono(String telefono) {
        return telefono != null && telefono.matches("9\\d{8}");
    }

    // ---------------------------------------------------------------
    // FECHAS
    // ---------------------------------------------------------------
    public static boolean esFechaNoFutura(LocalDate fecha) {
        return fecha != null && !fecha.isAfter(LocalDate.now());
    }

    /** Intenta leer AAAA-MM-DD; devuelve null si no es una fecha real. */
    public static LocalDate parsearFecha(String texto) {
        try {
            return LocalDate.parse(texto.trim());
        } catch (Exception e) {
            return null;
        }
    }
}
