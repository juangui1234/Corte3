package dao;

import dto.VacunaDTO;
import persistencia.GestorPersistencia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VacunaDAO {

    private final String archivo = "data/vacunas.dat";
    private final GestorPersistencia gestor;

    public VacunaDAO() {
        this.gestor = GestorPersistencia.getInstance();
        File carpeta = new File("data");
        if (!carpeta.exists()) {
            carpeta.mkdirs(); // Asegura que la carpeta exista
        }
    }

    // Guarda la lista completa de vacunas
    public void guardarVacunas(List<VacunaDTO> vacunas) {
        gestor.guardarLista(archivo, vacunas);
    }

    // Carga y devuelve la lista de vacunas
    public List<VacunaDTO> cargarVacunas() {
        List<VacunaDTO> lista = gestor.cargarLista(archivo);
        return (lista != null) ? lista : new ArrayList<>();
    }
}
