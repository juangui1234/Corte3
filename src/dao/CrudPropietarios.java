/*package dao;
import modelo.*;

import java.util.ArrayList;

public class CrudPropietarios {
    private ArrayList<Propietario> propietarios = new ArrayList<>();

    public void agregar(Propietario p) {
        propietarios.add(p);
    }

    public ArrayList<Propietario> getTodos() {
        return propietarios;
    }

    public void agregarPropietario(Propietario propietario) {
        if (propietario != null) {
            propietarios.add(propietario);
        }
    }

    public Propietario buscarPorDocumento(String documento) {
        for (Propietario p : propietarios) {
            if (p.getDocumento().equals(documento)) {
                return p;
            }
        }
        return null;
    }

    public boolean eliminarPorDocumento(String documento) {
        Propietario p = buscarPorDocumento(documento);
        if (p != null) {
            propietarios.remove(p);
            return true;
        }
        return false;
    }
}*/