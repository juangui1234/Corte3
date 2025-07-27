package modelo;

import java.io.Serializable;

public abstract class Persona implements Serializable {
    protected String nombre;
    protected String documento;
    protected String telefono;

    public Persona(String nombre, String documento, String telefono) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
    }

    public String getNombre() { return nombre; }
    public String getDocumento() { return documento; }
    public String getTelefono() { return telefono; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDocumento(String documento) { this.documento = documento; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    //Metodo abstracto para implementar en subclases
    public abstract String getTipo();

    public String mostrarDatos() {
        return "👤 Nombre: " + nombre +
                "\n🆔 Documento: " + documento +
                "\n📞 Teléfono: " + telefono;
    }

    @Override
    public String toString() {
        return getTipo() + " - " + nombre + " (" + documento + ")";
    }
}


/*package modelo;
import java.io.Serializable;
//clase abstracta
public abstract class Persona implements Serializable {
//public class Persona {

        protected String nombre;
        protected String documento;
        protected String telefono;

        public Persona(String nombre, String documento, String telefono) {
            this.nombre = nombre;
            this.documento = documento;
            this.telefono = telefono;
        }
        // Getters y Setters
        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDocumento() {
            return documento;
        }

        public void setDocumento(String documento) {
            this.documento = documento;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

    //metodo abstracto para personalizar en subclases
    public abstract String getTipo();

    public String mostrarDatos() {
        return "👤 Nombre: " + nombre + "\n" +
                "🆔 Documento: " + documento + "\n" +
                "📞 Teléfono: " + telefono;
    }
}

*/