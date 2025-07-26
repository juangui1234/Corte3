package util;

public class IDGenerator {
    private static int contadorConsulta = 0;
    private static int contadorMascota = 0;
    private static int contadorVeterinario = 0; // ✅ Nuevo contador
    private static int contadorPropietario = 0;

    public static String generarCodigoConsulta() {
        return "CON" + (++contadorConsulta);
    }

    public static String generarCodigoMascota() {
        return "MAS" + (++contadorMascota);
    }

    public static String generarCodigoVeterinario() {
        return "VET" + String.format("%03d", contadorVeterinario++);
    }
    public static String generarCodigoPropietario() {
        return "PRO" + String.format("%03d", contadorPropietario++);
    }

    public static void setContadorPropietario(int nuevoValor) {
        contadorPropietario = nuevoValor;
    }
    public static void setContadorVeterinario(int nuevoValor) {
        contadorVeterinario = nuevoValor;
    }

}

