package util;

public class IDGenerator {
    private static int contadorConsulta = 0;
    private static int contadorMascota = 0;
    private static int contadorVeterinario = 0; // ✅ Nuevo contador
    private static int contadorPropietario = 0;

    public static String generarCodigoConsulta() {
        return "C" + (++contadorConsulta);
    }

    public static String generarCodigoMascota() {
        return "M" + (++contadorMascota);
    }

    public static String generarCodigoVeterinario() {
        return "V" + (++contadorVeterinario); // ✅ Genera V1, V2, ...
    }
    public static String generarCodigoPropietario() {
        return "P" + (++contadorPropietario);
    }
}

