package src.dao;

import modelo.Mascota;
import persistencia.ArchivoManager;

import java.util.ArrayList;
import java.util.List;

public class MascotaDAO {
    private final ArchivoManager archivo;

    public MascotaDAO() {
        archivo = new ArchivoManager("data/mascotas.txt");
    }

    public void guardar(Mascota mascota) {
        archivo.escribirLinea(mascota.toLineaArchivo());
    }

    public List<Mascota> listar() {
        List<Mascota> mascotas = new ArrayList<>();
        for (String linea : archivo.leerLineas()) {
            Mascota m = Mascota.desdeLineaArchivo(linea);
            if (m != null) mascotas.add(m);
        }
        return mascotas;
    }

    public void eliminarPorNombre(String nombre) {
        List<Mascota> mascotas = listar();
        mascotas.removeIf(m -> m.getNombre().equalsIgnoreCase(nombre));
        sobrescribirLista(mascotas);
    }

    public void actualizar(String nombreClave, Mascota nueva) {
        List<Mascota> mascotas = listar();
        for (int i = 0; i < mascotas.size(); i++) {
            if (mascotas.get(i).getNombre().equalsIgnoreCase(nombreClave)) {
                mascotas.set(i, nueva);
                break;
            }
        }
        sobrescribirLista(mascotas);
    }

    private void sobrescribirLista(List<Mascota> mascotas) {
        List<String> lineas = new ArrayList<>();
        for (Mascota m : mascotas) {
            lineas.add(m.toLineaArchivo());
        }
        archivo.sobrescribirArchivo(lineas);
    }
}