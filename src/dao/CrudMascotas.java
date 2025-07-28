/*package dao;

import modelo.*;

import java.util.ArrayList;
import java.util.List;

public class CrudMascotas {
    private List<Mascota> mascotas;

    public CrudMascotas() {
        mascotas = new ArrayList<>();
        // Datos simulados para pruebas
     //  mascotas.add(new Mascota("Luna", "Perro", 5));
      //  mascotas.add(new Mascota("Milo", "Gato", 3));
      //  mascotas.add(new Mascota("Rocky", "Conejo", 2));
    }

    // Obtener la lista completa
    public List<Mascota> getMascotas() {
        return mascotas;
    }

    // Agregar nueva mascota
    public void agregarMascota(Mascota m) {
        mascotas.add(m);
    }

    // Buscar por nombre
    public Mascota buscarPorNombre(String nombre) {
        for (Mascota m : mascotas) {
            if (m.getNombre().equalsIgnoreCase(nombre)) {
                return m;
            }
        }
        return null;
    }

    // Eliminar mascota por nombre
    public boolean eliminarMascotaPorNombre(String nombre) {
        Mascota m = buscarPorNombre(nombre);
        if (m != null) {
            mascotas.remove(m);
            return true;
        }
        return false;
    }
}*/