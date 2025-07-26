package src.dao;

import modelo.Veterinario;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CrudVeterinarios {
    private List<Veterinario> veterinarios;

    public CrudVeterinarios() {
        this.veterinarios = new ArrayList<>();
        // Puedes agregar algunos datos de ejemplo si quieres
        veterinarios.add(new Veterinario("Carlos Pérez", "123", "3214567890", "General", true));
        veterinarios.add(new Veterinario("Laura Torres", "456", "3123456789", "Vacunación", true));
    }

    public void agregarVeterinario(Veterinario v) {
        veterinarios.add(v);
    }

    public Veterinario buscarPorDocumento(String documento) {
        for (Veterinario v : veterinarios) {
            if (v.getDocumento().equals(documento)) {
                return v;
            }
        }
        return null;
    }

    public boolean eliminarVeterinario(String documento) {
        Veterinario v = buscarPorDocumento(documento);
        if (v != null) {
            veterinarios.remove(v);
            return true;
        }
        return false;
    }

    public boolean editarVeterinario(String documento, String nuevoNombre, String nuevoTelefono, String nuevaEspecialidad, boolean disponible) {
        Veterinario v = buscarPorDocumento(documento);
        if (v != null) {
            v.setNombre(nuevoNombre);
            v.setTelefono(nuevoTelefono);
            v.setEspecialidad(nuevaEspecialidad);
            v.setDisponible(disponible);
            return true;
        }
        return false;
    }

    public List<Veterinario> getVeterinarios() {
        return veterinarios;
    }

    public void mostrarVeterinarios() {
        if (veterinarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay veterinarios registrados.");
        } else {
            StringBuilder sb = new StringBuilder("Lista de Veterinarios:\n");
            for (Veterinario v : veterinarios) {
                sb.append(v.mostrarInformacion()).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }
}
