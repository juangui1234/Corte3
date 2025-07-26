package modelo;

import excepciones.DatoInvalidoException;

import java.io.Serializable;
import java.util.ArrayList;

public class Propietario extends Persona implements Serializable {
    private String direccion;
    private String codigo;
    private ArrayList<Mascota> mascotas;

    public Propietario(String nombre, String documento, String telefono, String direccion) throws DatoInvalidoException  {
        super(nombre, documento, telefono);
        setDireccion(direccion);
        this.mascotas = new ArrayList<>();
    }

    // Métodos de lógica de dominio
    public void agregarMascota(Mascota mascota) {
        mascotas.add(mascota);
    }

    public void mostrarInformacion() {
        System.out.println(super.mostrarDatos());
        System.out.println("🏠 Dirección: " + direccion);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
/*    public String getTelefono() { return telefono; }

    public void setTelefono(String telefono) {
        if (telefono == null || telefono.length() < 7) {
            throw new IllegalArgumentException("Teléfono inválido.");
        }
        this.telefono = telefono;
    }*/

    public String getDireccion() { return direccion; }

    public void setDireccion(String direccion) throws DatoInvalidoException {
        if (direccion == null || direccion.isBlank()) {
            throw new DatoInvalidoException("La dirección no puede estar vacía.");
        }
        this.direccion = direccion;
    }

   /* public void setDireccion(String direccion) {
        if (direccion == null || direccion.isBlank()) {
            throw new IllegalArgumentException("Dirección inválida.");
        }
        this.direccion = direccion;
    }*/

    public ArrayList<Mascota> getMascotas() { return mascotas; }
/*
    // Serialización manual para guardar en archivos
    public String toLineaArchivo() {
        return getNombre() + ";" + getIdentificacion() + ";" + telefono + ";" + direccion;
    }

    public static Propietario desdeLineaArchivo(String linea) {
        String[] partes = linea.split(";");
        if (partes.length != 4) return null;
        return new Propietario(partes[0], partes[1], partes[2], partes[3]);
    }*/

    @Override
    public String getTipo() {
        return "Propietario";
    }

    @Override
    public String toString() {
        return getTipo() + " - " + getNombre() + " (" + getDocumento() + ") - Teléfono: " + getTelefono();
    }
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
