package controlador;

import dto.VacunaDTO;

import java.util.ArrayList;
import java.util.List;

public class VacunaControlador {
    private List<VacunaDTO> vacunas = new ArrayList<>();

    public void registrarVacuna(String nombreMascota, String descripcion, java.time.LocalDate fecha,
                                String tipoVacuna, String lote, java.time.LocalDate proximaDosis) {
        VacunaDTO nueva = new VacunaDTO(fecha, nombreMascota, descripcion, tipoVacuna, lote, proximaDosis);
        vacunas.add(nueva);
    }

    public boolean eliminarVacunaPorTipo(String nombreMascota, String tipoVacuna) {
        return vacunas.removeIf(v -> v.getNombreMascota().equalsIgnoreCase(nombreMascota)
                && v.getTipoVacuna().equalsIgnoreCase(tipoVacuna));
    }

    public List<VacunaDTO> listarVacunas() {
        return new ArrayList<>(vacunas);
    }
}
