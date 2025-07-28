package dao;

import dto.MascotaDTO;
import persistencia.GestorPersistencia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAO {

    private final String archivo = "data/mascotas.dat";
    private final GestorPersistencia gestor;

    public MascotaDAO() {
        this.gestor = GestorPersistencia.getInstance();
        File carpeta = new File("data");
        if (!carpeta.exists()) {
            carpeta.mkdirs(); // Asegura que la carpeta exista
        }
    }

    // Guarda la lista completa de mascotas
    public void guardarMascotas(List<MascotaDTO> mascotas) {
        gestor.guardarLista(archivo, mascotas);
    }

    // Carga y devuelve la lista de mascotas
    public List<MascotaDTO> cargarMascotas() {
        List<MascotaDTO> lista = gestor.cargarLista(archivo);
        return (lista != null) ? lista : new ArrayList<>();
    }
    public MascotaDTO buscarPorNombre(String nombre) {
        List<MascotaDTO> lista = cargarMascotas();
        for (MascotaDTO m : lista) {
            if (m.getNombre().equalsIgnoreCase(nombre)) {
                return m;
            }
        }
        return null; // No se encontró la mascota
    }
}
