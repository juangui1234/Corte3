package src.modelo;

import java.util.ArrayList;

public class Propietario extends Persona {
    private String direccion;
    private ArrayList<Mascota> mascotas;

    public Propietario(String nombre, String documento, String telefono, String direccion) {
        super(nombre, documento, telefono); // llamada al constructor de Modelo.PersonaDTO
        setDireccion(direccion);
        this.mascotas = new ArrayList<>();
    }

    public void agregarMascota(Mascota mascota) {
        mascotas.add(mascota);
    }

    public void mostrarInformacion() {
        System.out.println("👤 Modelo.Propietario: " + getNombre());
        System.out.println("🆔 Documento: " + getDocumento());
        System.out.println("📞 Teléfono: " + getTelefono());
        System.out.println("🏠 Dirección: " + direccion);
    }

    public void mostrarMascotas() {
        for (Mascota m : mascotas) {
            System.out.println();
            m.mostrarHistorial();
        }
    }

    public void mostrarInformacionCompleta() {
        System.out.println("===== FICHA CLÍNICA =====");
        mostrarInformacion();
        mostrarMascotas();
    }

    // Getters y Setters
    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

   /* public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || telefono.length() < 7) {
            throw new IllegalArgumentException("Teléfono inválido.");
        }
        this.telefono = telefono;
    }*/

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion == null || direccion.isBlank()) {
            throw new IllegalArgumentException("Dirección inválida.");
        }
        this.direccion = direccion;
    }

    public String toLineaArchivo() {
        return getNombre() + ";" + getDocumento() + ";" + getTelefono() + ";" + getDireccion();
    }

    public static Propietario desdeLineaArchivo(String linea) {
        String[] partes = linea.split(";");
        if (partes.length != 4) return null;
        try {
            return new Propietario(partes[0], partes[1], partes[2], partes[3]);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return getNombre() + " (" + getDocumento() + ")";
    }
}