package dao;

import modelo.*;

import java.util.List;

public class CrudVacunas {

    // Registrar vacunación a una mascota existente
    public boolean registrarVacuna(Mascota mascota, Vacunacion vacunacion) {
        if (mascota != null && vacunacion != null) {
            mascota.agregarEvento(vacunacion); // ✅ Método correcto
            System.out.println("✅ Vacunación registrada para " + mascota.getNombre());
            return true;
        }
        return false;
    }

    // Listar vacunas de una mascota
    public void listarVacunas(Mascota mascota) {
        if (mascota != null) {
            boolean hayVacunas = false;
            for (EventoClinico e : mascota.getHistorial()) {
                if (e instanceof Vacunacion) {
                    System.out.println(((Vacunacion) e).mostrarDetalle());
                    hayVacunas = true;
                }
            }
            if (!hayVacunas) {
                System.out.println("📭 No hay vacunas registradas para " + mascota.getNombre());
            }
        } else {
            System.out.println("❌ Modelo.MascotaDTO no encontrada.");
        }
    }

    // Buscar vacuna por tipo en una mascota
    public Vacunacion buscarVacunaPorTipo(Mascota mascota, String tipo) {
        if (mascota != null && tipo != null) {
            for (EventoClinico e : mascota.getHistorial()) {
                if (e instanceof Vacunacion) {
                    Vacunacion v = (Vacunacion) e;
                    if (v.getTipoVacuna().equalsIgnoreCase(tipo)) {
                        return v;
                    }
                }
            }
        }
        return null;
    }

    // Eliminar vacuna por tipo
    public boolean eliminarVacunaPorTipo(Mascota mascota, String tipo) {
        Vacunacion vacunacion = buscarVacunaPorTipo(mascota, tipo);
        if (vacunacion != null) {
            mascota.getHistorial().remove(vacunacion);
            System.out.println("🗑️ Vacunación '" + tipo + "' eliminada de " + mascota.getNombre());
            return true;
        }
        return false;
    }

    // Editar vacuna por tipo
    public boolean editarVacunaPorTipo(Mascota mascota, String tipoOriginal, Vacunacion nuevaVacunacion) {
        List<EventoClinico> historial = mascota.getHistorial();
        for (int i = 0; i < historial.size(); i++) {
            EventoClinico e = historial.get(i);
            if (e instanceof Vacunacion) {
                Vacunacion v = (Vacunacion) e;
                if (v.getTipoVacuna().equalsIgnoreCase(tipoOriginal)) {
                    historial.set(i, nuevaVacunacion);
                    System.out.println("✏️ Vacunación actualizada.");
                    return true;
                }
            }
        }
        return false;
    }
}
