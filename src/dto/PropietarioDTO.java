package dto;
import java.io.Serializable;

public class PropietarioDTO extends PersonaDTO implements Serializable {
    private String direccion;

    public PropietarioDTO(String nombre, String documento, String telefono, String direccion) {
        super(nombre, documento, telefono);
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String getTipo() {
        return "Propietario";
    }
}
