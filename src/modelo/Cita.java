package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Cita extends EventoClinico implements Serializable  {
    private LocalTime hora;
    private Veterinario veterinario;
    private String estado; // Valores posibles: agendada, completada, cancelada

    public Cita(LocalDate fecha, Mascota mascota, String descripcion,
                LocalTime hora, Veterinario veterinario, String estado) {
        super(fecha, mascota, descripcion);
        this.hora = hora;
        this.veterinario = veterinario;
        this.estado = estado;
    }

    public LocalTime getHora() {
        return hora;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public String getEstado() {
        return estado;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String mostrarDetalle() {
        return "Cita médica para " + mascota.getNombre() +
                " el " + fecha + " a las " + hora +
                "\nVeterinario: " + veterinario.getNombre() +
                "\nEstado: " + estado +
                "\nDescripción: " + descripcion;
    }
}