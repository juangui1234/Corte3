package dao;

import dto.VacunaDTO;
import persistencia.GestorPersistencia;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VacunaDAO implements Serializable {

    private final String archivo = "data/vacunas.dat";
    private final GestorPersistencia gestor;
    private List<VacunaDTO> vacunas;

    public VacunaDAO() {
        this.gestor = GestorPersistencia.getInstance();
        File carpeta = new File("data");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
        this.vacunas = cargarVacunas(); // Inicializar lista
    }

    public boolean eliminarPorTipo(String nombreMascota, String tipo) {
        boolean eliminado = vacunas.removeIf(v ->
                v.getNombreMascota().equalsIgnoreCase(nombreMascota) &&
                        v.getTipoVacuna().equalsIgnoreCase(tipo));
        if (eliminado) {
            guardarVacunas();
        }
        return eliminado;
    }

    public void agregarVacuna(VacunaDTO vacuna) {
        vacunas.add(vacuna);
    }

    public void guardarVacunas() {
        gestor.guardarLista(archivo, vacunas);
    }

    public List<VacunaDTO> cargarVacunas() {
        List<VacunaDTO> lista = gestor.cargarLista(archivo);
        return (lista != null) ? lista : new ArrayList<>();
    }
}
