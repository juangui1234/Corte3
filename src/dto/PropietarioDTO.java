package dto;
import java.io.Serializable;

public class PropietarioDTO extends PersonaDTO implements Serializable {
    private String direccion;
    private String codigo;


    public PropietarioDTO(String nombre, String documento, String telefono, String direccion, String codigo) {
        super(nombre, documento, telefono);

        this.direccion = direccion;
        this.codigo = codigo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    @Override
    public String getTipo() {
        return "Propietario";
    }
}
