package modelo;

import java.time.LocalDate;

public abstract class EventoClinico {
    protected LocalDate fecha;
    protected Mascota mascota;
    protected String descripcion;

    public EventoClinico(LocalDate fecha, Mascota mascota, String descripcion) {
        this.fecha = fecha;
        this.mascota = mascota;
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public String getDescripcion() {
        return descripcion;
    }

    // Metodo abstracto
    public abstract String mostrarDetalle();
}