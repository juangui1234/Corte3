package dto;

import java.io.Serializable;
import java.time.LocalDate;

public class VacunaDTO extends EventoClinicoDTO implements Serializable {

    private String tipoVacuna;
    private String lote;
    private LocalDate proximaDosis;

    public VacunaDTO(LocalDate fecha, String nombreMascota, String descripcion,
                     String tipoVacuna, String lote, LocalDate proximaDosis) {
        super(fecha, nombreMascota, descripcion);
        this.tipoVacuna = tipoVacuna;
        this.lote = lote;
        this.proximaDosis = proximaDosis;
    }

    public String getTipoVacuna() {
        return tipoVacuna;
    }

    public String getLote() {
        return lote;
    }

    public LocalDate getProximaDosis() {
        return proximaDosis;
    }

    public void setTipoVacuna(String tipoVacuna) {
        this.tipoVacuna = tipoVacuna;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public void setProximaDosis(LocalDate proximaDosis) {
        this.proximaDosis = proximaDosis;
    }

    @Override
    public String mostrarDetalle() {
        return "Vacunación aplicada a " + nombreMascota +
                " el " + fecha +
                "\nTipo de vacuna: " + tipoVacuna +
                "\nLote: " + lote +
                "\nPróxima dosis: " + proximaDosis +
                "\nDescripción: " + descripcion;
    }
}
