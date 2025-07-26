package src.modelo;

public class Veterinario extends Persona {
    private String especialidad;
    private boolean disponible;

    public Veterinario(String nombre, String documento, String telefono, String especialidad, boolean disponible) {
        super(nombre, documento, telefono);
        this.especialidad = especialidad;
        this.disponible = disponible;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String mostrarInformacion() {
        return "Modelo.Veterinario: " + getNombre() + "\nDocumento: " + getDocumento() + "\nTeléfono: " + getTelefono() +
                "\nEspecialidad: " + especialidad + "\nDisponible: " + (disponible ? "Sí" : "No");
    }

    @Override
    public String toString() {
        return getNombre() + " - " + especialidad;
    }
}