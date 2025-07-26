package persistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoUtil {

    // ✅ Escribe una línea en un archivo (modo append)
    public static void escribirLinea(String ruta, String linea, boolean append) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, append))) {
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir archivo: " + e.getMessage());
        }
    }

    // ✅ Lee todas las líneas de un archivo
    public static List<String> leerArchivo(String ruta) {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer archivo: " + e.getMessage());
        }
        return lineas;
    }

    // ✅ Sobrescribe archivo completo
    public static void sobrescribirArchivo(String ruta, List<?> objetos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (Object o : objetos) {
                bw.write(o.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al sobrescribir archivo: " + e.getMessage());
        }
    }

    // ✅ Crea archivo si no existe
    public static void crearArchivoSiNoExiste(String ruta) {
        File archivo = new File(ruta);
        try {
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error al crear archivo: " + e.getMessage());
        }
    }

    // ✅ Elimina un archivo
    public static boolean eliminarArchivo(String ruta) {
        return new File(ruta).delete();
    }

    // ✅ Renombrar archivo
    public static boolean renombrarArchivo(String rutaVieja, String nuevoNombre) {
        File archivoViejo = new File(rutaVieja);
        File archivoNuevo = new File(archivoViejo.getParent(), nuevoNombre);
        return archivoViejo.renameTo(archivoNuevo);
    }
}