package modelo;

import dto.ConsultaDTO;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import util.IDGenerator;
public class Consulta extends EventoClinico implements Serializable {

    private String idConsulta; // ID generado
    private String diagnostico;
    private String tratamiento;
    private List<String> medicamentos;
    private Veterinario veterinario;

    public Consulta(LocalDate fecha, Mascota mascota, String descripcion,
                    String diagnostico, String tratamiento,
                    List<String> medicamentos, Veterinario veterinario) {
        super(fecha, mascota, descripcion);
        this.idConsulta = IDGenerator.generarCodigoConsulta(); // Generar ID tipo: "C-"
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.medicamentos = medicamentos;
        this.veterinario = veterinario;
    }

    // ✅ Getters y Setters
    public String getIdConsulta() {
        return idConsulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public List<String> getMedicamentos() {
        return medicamentos;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public void setMedicamentos(List<String> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    // ✅ Método requerido por el DTO
    public String getCodigo() {
        return this.idConsulta;
    }

    @Override
    public String mostrarDetalle() {
        return "Consulta veterinaria de " + mascota.getNombre() + " el " + fecha +
                "\nVeterinario: " + veterinario.getNombre() +
                "\nDiagnóstico: " + diagnostico +
                "\nTratamiento: " + tratamiento +
                "\nMedicamentos: " + String.join(", ", medicamentos) +
                "\nDescripción: " + descripcion;
    }

    // ✅ Conversión a DTO
    public ConsultaDTO toDTO() {
        return new ConsultaDTO(
                this.getCodigo(),
                this.getFecha(),
                this.getMascota().getNombre(),
                this.getDescripcion(),
                this.getVeterinario().getDocumento(),
                this.getVeterinario().getNombre(),
                this.getVeterinario().getEspecialidad()
        );
    }
}
