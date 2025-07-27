package dao;

import modelo.Veterinario;
import persistencia.GestorPersistencia;

import java.util.List;
//dao con persistencia
public class VeterinarioDAO {
    private final String archivo = "data/veterinarios.dat";
    private final GestorPersistencia gestor;

    public VeterinarioDAO() {
        this.gestor = GestorPersistencia.getInstance(); // Usar singleton
    }

    public void guardarVeterinarios(List<Veterinario> lista) {
        gestor.guardarLista(archivo, lista); // delega al Gestor
    }

    public List<Veterinario> cargarVeterinarios() {
        return gestor.cargarLista(archivo); // delega al Gestor
    }
}


/*package dao;

import modelo.Veterinario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

Clase DAO para manejar la persistencia de objetos Veterinario en archivo .dat

public class VeterinarioDAO {
    private final String archivo = "data/veterinarios.dat";

    public VeterinarioDAO() {
        File carpeta = new File("data");
        if (!carpeta.exists()) {
            carpeta.mkdirs(); // Crear carpeta si no existe
        }
    }

    // Guarda la lista completa de veterinarios en archivo
    public void guardarVeterinarios(List<Veterinario> veterinarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(veterinarios);
        } catch (IOException e) {
            System.err.println("❌ Error guardando veterinarios: " + e.getMessage());
        }
    }

    // Carga la lista de veterinarios desde el archivo
    public List<Veterinario> cargarVeterinarios() {
        List<Veterinario> veterinarios = new ArrayList<>();
        File f = new File(archivo);
        if (!f.exists()) {
            return veterinarios; // si el archivo no existe, retornar lista vacía
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            veterinarios = (List<Veterinario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ No se pudo leer el archivo: " + e.getMessage());
        }

        return veterinarios;
    }
}*/
