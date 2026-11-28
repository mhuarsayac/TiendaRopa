package utils;

import java.util.Arrays;

public class Validador {

    public Validador() {

    }

    //Valores permitidos
    private static final String ACTIVO = "ACTIVO";

    //String ... permitidos: permite recibir varios Strings directamente o pasar un arreglo de String
    public static boolean estaEn(String valor, String... permitidos) {
        if (valor == null) {
            return false;
        } else {
            // Arrays.stream(permitidos) recorre cada elemento del array
            // anyMatch busca si hay alguno que coincida
            // trim elimina los espacios en blanco del inicio y del final del texto
            // p representa el elemento actual
            return Arrays.stream(permitidos).anyMatch(p -> p.equalsIgnoreCase(valor.trim()));
        }
    }

    //Toma como "ACTIVO" si ingresan ACTIVO, S, SI, 1 o True
    public static boolean esActivo(String valor) {
        return estaEn(valor, ACTIVO, "S", "SI", "1", "True");
    }
}
