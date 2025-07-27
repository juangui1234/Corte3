package controlador;

import excepciones.*;
import dao.VeterinarioDAO;
import modelo.Veterinario;
import util.IDGenerator;

import java.util.ArrayList;
import java.util.List;

public class VeterinarioControlador {
    private List<Veterinario> listaVeterinarios;
    private VeterinarioDAO dao;

    public VeterinarioControlador() {
        dao = new VeterinarioDAO();
        listaVeterinarios = dao.cargarVeterinarios(); // Cargar desde archivo

        List<String> codigos = new ArrayList<>();
        for (Veterinario v : listaVeterinarios) {
            codigos.add(v.getDocumento());
        }
        IDGenerator.inicializarDesdeDatos(codigos, "V-");

        }

    // Agregar
    public void agregar(String nombre, String telefono, String especialidad, boolean disponible) {
        String id = IDGenerator.generarCodigoVeterinario();
        try {
            Veterinario nuevo = new Veterinario(nombre, id, telefono, especialidad, disponible);
            listaVeterinarios.add(nuevo);
            dao.guardarVeterinarios(listaVeterinarios);
        } catch (DatoInvalidoException e) {
            System.err.println("⚠️ Error al agregar veterinario: " + e.getMessage());
        }
    }

    // Buscar por ID
    public Veterinario buscarPorID(String id) {
        for (Veterinario v : listaVeterinarios) {
            if (v.getDocumento().equalsIgnoreCase(id)) return v;
        }
        return null;
    }

    // Actualizar por ID
    public boolean actualizarPorID(String id, String nombre, String telefono, String especialidad, boolean disponible) {
        Veterinario v = buscarPorID(id);
        if (v != null) {
            v.setNombre(nombre);
            v.setTelefono(telefono);
            try {
                v.setEspecialidad(especialidad);
            } catch (DatoInvalidoException e) {
                System.err.println("⚠️ Error al actualizar especialidad: " + e.getMessage());
                return false;
            }
            v.setDisponible(disponible);
            dao.guardarVeterinarios(listaVeterinarios);
            return true;
        }
        return false;
    }

    // Eliminar por ID
    public boolean eliminar(String id) {
        Veterinario v = buscarPorID(id);
        if (v != null) {
            listaVeterinarios.remove(v);
            dao.guardarVeterinarios(listaVeterinarios);
            return true;
        }
        return false;
    }

    public List<Veterinario> getListaVeterinarios() {
        return new ArrayList<>(listaVeterinarios);
    }

    public List<Veterinario> listar() {
        return new ArrayList<>(listaVeterinarios);
    }
}
