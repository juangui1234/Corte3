package modelo;
import excepciones.DatoInvalidoException;
import java.io.Serializable;


public class Veterinario extends Persona implements Serializable {
    private String especialidad;
    private boolean disponible;

    public Veterinario(String nombre, String documento, String telefono, String especialidad, boolean disponible) throws DatoInvalidoException {
        super(nombre, documento, telefono);
        setEspecialidad(especialidad);
        this.disponible = disponible;
    }

    public String getEspecialidad() { return especialidad; }

    public void setEspecialidad(String especialidad) throws DatoInvalidoException {
        if (especialidad == null || especialidad.trim().isEmpty()) {
            throw new DatoInvalidoException("La especialidad no puede estar vacía");
        }
        this.especialidad = especialidad;
    }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    public String mostrarInformacion() {
        return "👨‍⚕ Veterinario: " + getNombre() +
                "\n🆔 Documento: " + getDocumento() +
                "\n📞 Teléfono: " + getTelefono() +
                "\n📚 Especialidad: " + especialidad +
                "\n✔ Disponible: " + (disponible ? "Sí" : "No");
    }

    @Override
    public String getTipo() {
        return "Veterinario";
    }
    public String getCodigo() {
        return getDocumento();  //cambia a otro campo único
    }
    @Override
    public String mostrarDatos() {
        return super.mostrarDatos() +
                "\n📚 Especialidad: " + especialidad +
                "\n✔ Disponible: " + (disponible ? "Sí" : "No");
    }

    @Override
    public String toString() {
        return getTipo() + " - " + getNombre() + " (" + getDocumento() + ") - " + especialidad;
    }
}
