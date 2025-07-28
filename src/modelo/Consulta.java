package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Consulta extends EventoClinico implements Serializable {
    private String diagnostico;
    private String tratamiento;
    private List<String> medicamentos;
    private Veterinario veterinario;

    public Consulta(LocalDate fecha, Mascota mascota, String descripcion,
                    String diagnostico, String tratamiento,
                    List<String> medicamentos, Veterinario veterinario) {
        super(fecha, mascota, descripcion);
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.medicamentos = medicamentos;
        this.veterinario = veterinario;
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

    @Override
    public String mostrarDetalle() {
        return "Consulta veterinaria de " + mascota.getNombre() + " el " + fecha +
                "\nVeterinario: " + veterinario.getNombre() +
                "\nDiagnóstico: " + diagnostico +
                "\nTratamiento: " + tratamiento +
                "\nMedicamentos: " + String.join(", ", medicamentos) +
                "\nDescripción: " + descripcion;
    }
}