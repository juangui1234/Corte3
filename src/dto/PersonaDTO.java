package src.dto;

public class PersonaDTO {

        protected String nombre;
        protected String documento;
        protected String telefono;

        public PersonaDTO(String nombre, String documento, String telefono) {
            this.nombre = nombre;
            this.documento = documento;
            this.telefono = telefono;
        }

        // Devuelve los datos
        public String obtenerDatos() {
            return "👤 Nombre: " + nombre + "\n" +
                    "🆔 Documento: " + documento + "\n" +
                    "📞 Teléfono: " + telefono;
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

    public String mostrarDatos() {
        return "👤 Nombre: " + nombre + "\n" +
                "🆔 Documento: " + documento + "\n" +
                "📞 Teléfono: " + telefono;
    }
}

