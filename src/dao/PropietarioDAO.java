package src.dao;

import modelo.Propietario;
import persistencia.ArchivoManager;

import java.util.ArrayList;
import java.util.List;

public class PropietarioDAO {
    private final ArchivoManager archivo;

    public PropietarioDAO() {
        archivo = new ArchivoManager("data/propietarios.txt");
    }

    public void guardar(Propietario p) {
        archivo.escribirLinea(p.toLineaArchivo());
    }

    public List<Propietario> listar() {
        List<Propietario> lista = new ArrayList<>();
        for (String linea : archivo.leerLineas()) {
            Propietario p = Propietario.desdeLineaArchivo(linea); //metodo para listar
            if (p != null) lista.add(p);
        }
        return lista;
    }

    public void eliminarPorDocumento(String documento) {
        List<Propietario> lista = listar();
        lista.removeIf(p -> p.getDocumento().equalsIgnoreCase(documento));
        sobrescribirLista(lista);
    }

    public void actualizar(String documentoClave, Propietario nuevo) {
        List<Propietario> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getDocumento().equalsIgnoreCase(documentoClave)) {
                lista.set(i, nuevo);
                break;
            }
        }
        sobrescribirLista(lista);
    }

    private void sobrescribirLista(List<Propietario> lista) {
        List<String> lineas = new ArrayList<>();
        for (Propietario p : lista) {
            lineas.add(p.toLineaArchivo());
        }
        archivo.sobrescribirArchivo(lineas);
    }
}
