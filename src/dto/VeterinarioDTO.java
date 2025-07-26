package dto;

import dto.PersonaDTO;

import java.io.Serializable;


public class VeterinarioDTO extends PersonaDTO implements Serializable {
    private String especialidad;
    private boolean disponible;

    public VeterinarioDTO(String nombre, String identificacion, String telefono, String especialidad, boolean disponible) {
        super(nombre, identificacion, telefono);
        this.especialidad = especialidad;
        this.disponible = disponible;
    }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    @Override
    public String getTipo() {
        return "Veterinario";
    }

    @Override
    public String toString() {
        return getTipo() + " - " + getNombre() + " (" + getDocumento() + ") - " + especialidad;
    }
}
