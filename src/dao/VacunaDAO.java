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
        this.vacunas = cargarVacunas(); // Inicializar lista desde archivo
    }

    /**
     * Agregar una vacuna y guardar en archivo
     */
    public void agregarVacuna(VacunaDTO vacuna) {
        vacunas.add(vacuna);
        guardarVacunas(); // 🔹 Persistencia inmediata
    }

    /**
     * Eliminar una vacuna por tipo para una mascota específica
     */
    public boolean eliminarPorTipo(String nombreMascota, String tipo) {
        boolean eliminado = vacunas.removeIf(v ->
                v.getNombreMascota().equalsIgnoreCase(nombreMascota) &&
                        v.getTipoVacuna().equalsIgnoreCase(tipo)
        );
        if (eliminado) {
            guardarVacunas(); // 🔹 Actualiza el archivo después de eliminar
        }
        return eliminado;
    }

    /**
     * Guardar todas las vacunas en el archivo
     */
    public void guardarVacunas() {
        gestor.guardarLista(archivo, vacunas);
    }

    /**
     * Cargar todas las vacunas desde el archivo
     */
    public List<VacunaDTO> cargarVacunas() {
        List<VacunaDTO> lista = gestor.cargarLista(archivo);
        return (lista != null) ? lista : new ArrayList<>();
    }

    /**
     * Obtener la lista actual de vacunas en memoria
     */
    public List<VacunaDTO> getVacunas() {
        return new ArrayList<>(vacunas); // 🔹 Devuelve copia para evitar modificaciones externas
    }
}
