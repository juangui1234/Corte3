package dao;
import excepciones.*;
import dto.PropietarioDTO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
 *clase DAO para manejar la persistencia de objetos Propietario en archivo .dat
 */
public class PropietarioDAO {
    private final String archivo = "data/propietarios.dat";

    public PropietarioDAO() {
        File carpeta = new File("data");
        if (!carpeta.exists()) {
            carpeta.mkdirs(); // Crear carpeta si no existe
        }
    }

    // Guarda la lista completa de propietarios en archivo
    public void guardarPropietarios(List<PropietarioDTO> propietarios) throws ErrorPersistenciaException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(propietarios);
        } catch (IOException e) {
            throw new ErrorPersistenciaException("Error guardando propietarios", e);
        }
    }

    // Carga la lista de propietarios desde el archivo
    public List<PropietarioDTO> cargarPropietarios() {
        List<PropietarioDTO> propietarios = new ArrayList<>();
        File f = new File(archivo);
        if (!f.exists()) {
            return propietarios;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            propietarios = (List<PropietarioDTO>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ No se pudo leer el archivo de propietarios: " + e.getMessage());
        }

        return propietarios;
    }
}
