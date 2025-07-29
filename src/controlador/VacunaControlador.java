package controlador;

import dao.VacunaDAO;
import dto.VacunaDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VacunaControlador {
    // Lista en memoria
    private List<VacunaDTO> vacunas = new ArrayList<>();

    // 🔹 Nuevo: DAO para persistencia
    private final VacunaDAO vacunaDAO;

    public VacunaControlador() {
        vacunaDAO = new VacunaDAO();
        // 🔹 Cargar vacunas desde archivo al iniciar
        vacunas = vacunaDAO.cargarVacunas();
        vacunas = vacunaDAO.getVacunas();
    }

    // 🔹 Registrar y guardar en archivo
    public void registrarVacuna(String nombreMascota, String descripcion, LocalDate fecha,
                                String tipoVacuna, String lote, LocalDate proximaDosis) {
        VacunaDTO nueva = new VacunaDTO(fecha, nombreMascota, descripcion, tipoVacuna, lote, proximaDosis);
        vacunas.add(nueva);
        vacunaDAO.agregarVacuna(nueva); // Guarda automáticamente
        vacunas = vacunaDAO.getVacunas(); //refresca la lista
    }

    // 🔹 Eliminar vacuna por tipo para una mascota
    public boolean eliminarVacunaPorTipo(String nombreMascota, String tipoVacuna) {
        boolean eliminado = vacunas.removeIf(v ->
                v.getNombreMascota().equalsIgnoreCase(nombreMascota) &&
                        v.getTipoVacuna().equalsIgnoreCase(tipoVacuna)
        );
        if (eliminado) {
            vacunas = vacunaDAO.getVacunas();
            vacunaDAO.eliminarPorTipo(nombreMascota, tipoVacuna);
        }
        return eliminado;
    }

    // 🔹 Listar todas las vacunas
    public List<VacunaDTO> listarVacunas() {
        // Retorna una copia para evitar modificaciones externas
        return new ArrayList<>(vacunas);
    }

    // 🔹 Extra: Listar vacunas filtradas por mascota (para historial)
    public List<VacunaDTO> listarVacunasPorMascota(String nombreMascota) {
        List<VacunaDTO> filtradas = new ArrayList<>();
        for (VacunaDTO v : vacunas) {
            if (v.getNombreMascota().equalsIgnoreCase(nombreMascota)) {
                filtradas.add(v);
            }
        }
        return filtradas;
    }
}
