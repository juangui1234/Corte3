package excepciones;

public class NombreVacioException extends RuntimeException {
    public NombreVacioException(String mensaje) {
        super(mensaje);
    }
}