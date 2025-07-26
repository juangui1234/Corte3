package excepciones;



public class ErrorPersistenciaException extends Exception {
    public ErrorPersistenciaException(String mensaje, Throwable causa) {
        super(mensaje, causa);

    }
    public ErrorPersistenciaException(String mensaje) {
        super(mensaje);
    }
}