/*package dto;

import java.time.LocalDate;

public class VacunaDTO extends EventoClinicoDTO {
    private String nombreVacuna;
    private String lote;

    public VacunaDTO(LocalDate fecha, String descripcion, String nombreVacuna, String lote) {
        super(fecha, descripcion);
        this.nombreVacuna = nombreVacuna;
        this.lote = lote;
    }

    public String getNombreVacuna() {
        return nombreVacuna;
    }

    public void setNombreVacuna(String nombreVacuna) {
        this.nombreVacuna = nombreVacuna;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    @Override
    public String mostrarDetalle() {
        return "Vacuna [" + fecha + "]\nDescripción: " + descripcion +
                "\nVacuna aplicada: " + nombreVacuna +
                "\nLote: " + lote;
    }
}


/*package dto;

public class VacunaDTO extends EventoClinicoDTO {
    private String tipoVacuna;

    public VacunaDTO(LocalDate fecha, String nombreMascota, String descripcion, String tipoVacuna) {
        super(fecha, nombreMascota, descripcion);
        this.tipoVacuna = tipoVacuna;
    }

    public String getTipoVacuna() {
        return tipoVacuna;
    }

    @Override
    public String mostrarDetalle() {
        return "Vacuna aplicada: " + tipoVacuna + " - Fecha: " + fecha + " - Mascota: " + nombreMascota;
    }
}
*/