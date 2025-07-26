package src.modelo;

import java.time.LocalDate;

public class Vacunacion extends EventoClinico {
    private String tipoVacuna;
    private String lote;
    private LocalDate proximaDosis;

    public Vacunacion(LocalDate fecha, Mascota mascota, String descripcion,
                      String tipoVacuna, String lote, LocalDate proximaDosis) {
        super(fecha, mascota, descripcion);
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
        return "Vacunación aplicada a " + mascota.getNombre() +
                " el " + fecha +
                "\nTipo de vacuna: " + tipoVacuna +
                "\nLote: " + lote +
                "\nPróxima dosis: " + proximaDosis +
                "\nDescripción: " + descripcion;
    }
}