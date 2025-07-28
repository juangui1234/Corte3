package persistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoBinarioManager<T> {

    private File archivo;

    // Constructor: recibe la ruta del archivo binario
    public ArchivoBinarioManager(String rutaArchivo) {
        this.archivo = new File(rutaArchivo);
        crearArchivoSiNoExiste();
    }

    // Crea el archivo si no existe
    private void crearArchivoSiNoExiste() {
        try {
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
                // Guardar lista vacía al crearlo
                guardarObjetos(new ArrayList<>());
            }
        } catch (IOException e) {
            System.err.println("Error al crear archivo binario: " + e.getMessage());
        }
    }

    // ✅ Guarda lista de objetos (sobrescribe el archivo)
    public void guardarObjetos(List<T> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.err.println("Error al guardar objetos: " + e.getMessage());
        }
    }

    // ✅ Carga lista de objetos desde el archivo
    @SuppressWarnings("unchecked")
    public List<T> cargarObjetos() {
        List<T> lista = new ArrayList<>();
        if (archivo.length() == 0) {
            return lista; // si está vacío, devuelve lista vacía
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            lista = (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar objetos: " + e.getMessage());
        }
        return lista;
    }

    // ✅ Elimina archivo binario
    public boolean eliminarArchivo() {
        return archivo.delete();
    }

    // ✅ Ruta absoluta
    public String getRuta() {
        return archivo.getAbsolutePath();
    }
}
