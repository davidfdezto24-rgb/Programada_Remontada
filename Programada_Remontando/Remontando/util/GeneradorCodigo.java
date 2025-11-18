package Remontando.util;

/**
 * Clase utilitaria encargada de generar códigos consecutivos para los clientes.
 * Cada vez que se llama al método, se devuelve un código
 * con el formato "CLI-X", donde X aumenta secuencialmente.
 */
public class GeneradorCodigo {

    /** Contador interno utilizado para generar los códigos consecutivos. */
    private static int contador = 1;

    /**
     * Devuelve el siguiente código disponible con el formato "CLI-X".
     *
     * @return código único de cliente generado automáticamente
     */
    public static String siguiente() {
        return "CLI-" + contador++;
    }
}