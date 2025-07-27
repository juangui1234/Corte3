package util;

import java.util.List;

public class IDGenerator {
    private static int contadorConsulta = 0;
    private static int contadorMascota = 0;
    private static int contadorVeterinario = 0; // ✅ Nuevo contador
    private static int contadorPropietario = 0;
//metodo para validar los .dat
    public static void inicializarDesdeDatos(List<String> codigos, String prefijo) {
        int max = 0;
        for (String cod : codigos) {
            if (cod.startsWith(prefijo)) {
                try {
                    int num = Integer.parseInt(cod.replace(prefijo, ""));
                    if (num > max) max = num;
                } catch (NumberFormatException ignored) {}
            }
        }
        if (prefijo.equals("P-")) {
            contadorPropietario = max + 1;
        } else if (prefijo.equals("V-")) {
            contadorVeterinario = max + 1;
        } else if (prefijo.equals("M-")) {
            contadorMascota = max + 1;
        } else if (prefijo.equals("C-")) {
            contadorConsulta = max + 1;
        }
    }

    public static String generarCodigoConsulta() {
        return "C-" + (++contadorConsulta);
    }

    public static String generarCodigoMascota() {
        return "M-" + (++contadorMascota);
    }

    public static String generarCodigoVeterinario() {
        return "V-" + String.format("%03d", contadorVeterinario++);
    }
    public static String generarCodigoPropietario() {
        return "P-" + String.format("%03d", contadorPropietario++);
    }

    public static void setContadorPropietario(int nuevoValor) {
        contadorPropietario = nuevoValor;
    }
    public static void setContadorVeterinario(int nuevoValor) {
        contadorVeterinario = nuevoValor;
    }

}

