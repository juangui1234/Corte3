package excepciones;

/**
 * Excepción personalizada para datos inválidos o campos vacíos.
 * Se lanza cuando un campo obligatorio está vacío o mal formado.
 */
public class DatoInvalidoException extends Exception {

    public DatoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
