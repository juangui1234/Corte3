package dto;

import java.io.Serializable;
import java.time.LocalDate;

public class ConsultaDTO extends EventoClinicoDTO implements Serializable {

    private String idConsulta;
    private String idVeterinario;
    private String nombreVeterinario;
    private String especialidadVeterinario;
    private String diagnostico;
    private String tratamiento;
    private String medicamentos;

    public ConsultaDTO(String idConsulta, LocalDate fecha, String nombreMascota, String descripcion,
                       String idVeterinario, String nombreVeterinario, String especialidadVeterinario,
                       String medicamentos, String diagnostico, String tratamiento) {
        super(fecha, nombreMascota, descripcion);
        if (idConsulta == null || idConsulta.isEmpty()) throw new IllegalArgumentException("ID de consulta no válido");
        if (idVeterinario == null || idVeterinario.isEmpty())
            throw new IllegalArgumentException("ID de veterinario no válido");
        if (nombreVeterinario == null || nombreVeterinario.isEmpty())
            throw new IllegalArgumentException("Nombre del veterinario no válido");
        if (especialidadVeterinario == null || especialidadVeterinario.isEmpty())
            throw new IllegalArgumentException("Especialidad del veterinario no válida");
        this.idConsulta = idConsulta;
        this.idVeterinario = idVeterinario;
        this.nombreVeterinario = nombreVeterinario;
        this.especialidadVeterinario = especialidadVeterinario;
        this.medicamentos = medicamentos;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
    }
    // Getters
    public String getIdConsulta() {
        return idConsulta;
    }

    public String getIdVeterinario() {
        return idVeterinario;
    }

    public String getNombreVeterinario() {
        return nombreVeterinario;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }
    public String getMedicamentos() {
        return medicamentos;
    }
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

        public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

       public String getEspecialidadVeterinario() {
        return especialidadVeterinario;
    }

    @Override
    public String mostrarDetalle() {
        return "📋 Consulta ID: " + idConsulta +
                "\n📅 Fecha: " + fecha +
                "\n🐶 Mascota: " + nombreMascota +
                "\n🩺 Veterinario: " + nombreVeterinario + " (" + especialidadVeterinario + ")" +
                "\n📝 Descripción: " + descripcion +
                (diagnostico != null ? "\n🩻 Diagnóstico: " + diagnostico : "") +
                (tratamiento != null ? "\n💊 Tratamiento: " + tratamiento : "") +
                (medicamentos != null ? "\n🧪 Medicamentos: " + medicamentos : "");
    }

}
