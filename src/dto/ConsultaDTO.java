package dto;

import java.time.LocalDate;

public class ConsultaDTO extends EventoClinicoDTO {

    private String idConsulta;
    private String idVeterinario;
    private String nombreVeterinario;
    private String especialidadVeterinario;

    public ConsultaDTO(String idConsulta, LocalDate fecha, String nombreMascota, String descripcion,
                       String idVeterinario, String nombreVeterinario, String especialidadVeterinario) {
        super(fecha, nombreMascota, descripcion);
        if (idConsulta == null || idConsulta.isEmpty()) throw new IllegalArgumentException("ID de consulta no válido");
        if (idVeterinario == null || idVeterinario.isEmpty()) throw new IllegalArgumentException("ID de veterinario no válido");
        if (nombreVeterinario == null || nombreVeterinario.isEmpty()) throw new IllegalArgumentException("Nombre del veterinario no válido");
        if (especialidadVeterinario == null || especialidadVeterinario.isEmpty()) throw new IllegalArgumentException("Especialidad del veterinario no válida");
        this.idConsulta = idConsulta;
        this.idVeterinario = idVeterinario;
        this.nombreVeterinario = nombreVeterinario;
        this.especialidadVeterinario = especialidadVeterinario;
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

    public String getEspecialidadVeterinario() {
        return especialidadVeterinario;
    }

    @Override
    public String mostrarDetalle() {
        return "📋 Consulta ID: " + idConsulta +
                "\n📅 Fecha: " + fecha +
                "\n🐶 Mascota: " + nombreMascota +
                "\n🩺 Veterinario: " + nombreVeterinario + " (" + especialidadVeterinario + ")" +
                "\n📝 Descripción: " + descripcion;
    }
}
