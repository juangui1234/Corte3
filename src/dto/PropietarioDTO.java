package src.dto;

public class PropietarioDTO {
    private String nombre;
    private String documento;
    private String telefono;
    private String direccion;

    public PropietarioDTO(String nombre, String documento, String telefono, String direccion) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    @Override
    public String toString() {
        return nombre + " - " + documento + " - " + telefono + " - " + direccion;
    }
}