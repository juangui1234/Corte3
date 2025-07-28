package dto;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class EventoClinicoDTO implements Serializable {
    protected LocalDate fecha;
    protected String nombreMascota;  // Puedes usar ID o nombre como referencia
    protected String descripcion;

    public EventoClinicoDTO(LocalDate fecha, String nombreMascota, String descripcion) {
        this.fecha = fecha;
        this.nombreMascota = nombreMascota;
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public abstract String mostrarDetalle();
}
