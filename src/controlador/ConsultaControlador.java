package controlador;

import dao.ConsultaDAO;
import dto.ConsultaDTO;
import modelo.Veterinario;
import util.IDGenerator;

import java.time.LocalDate;
import java.util.List;

public class ConsultaControlador {

    private final ConsultaDAO consultaDAO;
    private final List<ConsultaDTO> listaConsultas;

    public ConsultaControlador() {
        this.consultaDAO = new ConsultaDAO();
        this.listaConsultas = consultaDAO.cargarConsultas();

        // Inicializar generador de ID si se desea
        IDGenerator.inicializarDesdeDatos(
                listaConsultas.stream().map(ConsultaDTO::getIdConsulta).toList(),
                "C-"
        );
    }

    public boolean registrarConsulta(LocalDate fecha, String nombreMascota,  String medicamentos, String descripcion,
                                     Veterinario veterinarioSeleccionado) {
        validar(veterinarioSeleccionado, nombreMascota, descripcion);

        String idConsulta = IDGenerator.generarCodigoConsulta();
        ConsultaDTO nueva = new ConsultaDTO(
                idConsulta, fecha, nombreMascota, descripcion,
                veterinarioSeleccionado.getDocumento(),
                veterinarioSeleccionado.getNombre(),
                veterinarioSeleccionado.getEspecialidad()

        );
        nueva.setMedicamentos(medicamentos);
        listaConsultas.add(nueva);
        consultaDAO.guardarConsultas(listaConsultas);
        return true;
    }

    public List<ConsultaDTO> obtenerConsultas() {
        return List.copyOf(listaConsultas); // Inmutable
    }
    public void eliminarConsultaPorID(String idConsulta) {
        listaConsultas.removeIf(c -> c.getIdConsulta().equalsIgnoreCase(idConsulta));
        consultaDAO.guardarConsultas(listaConsultas);
    }

    public ConsultaDTO buscarPorID(String idConsulta) {
        return listaConsultas.stream()
                .filter(c -> c.getIdConsulta().equalsIgnoreCase(idConsulta))
                .findFirst()
                .orElse(null);
    }

    private void validar(Veterinario veterinario, String nombreMascota, String descripcion) {
        if (veterinario == null || !veterinario.isDisponible()) {
            throw new IllegalArgumentException("Veterinario no disponible.");
        }
        if (nombreMascota == null || nombreMascota.isBlank()) {
            throw new IllegalArgumentException("Nombre de mascota requerido.");
        }
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("Descripción requerida.");
        }
    }
}
