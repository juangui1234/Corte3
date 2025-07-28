package dao;

import modelo.*;

import java.util.List;

public class CrudVacunas {

    // Registrar vacunación a una mascota existente
    public boolean registrarVacuna(Mascota mascota, Vacuna vacuna) {
        if (mascota != null && vacuna != null) {
            mascota.agregarEvento(vacuna); // ✅ Método correcto
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
                if (e instanceof Vacuna) {
                    System.out.println(((Vacuna) e).mostrarDetalle());
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
    public Vacuna buscarVacunaPorTipo(Mascota mascota, String tipo) {
        if (mascota != null && tipo != null) {
            for (EventoClinico e : mascota.getHistorial()) {
                if (e instanceof Vacuna) {
                    Vacuna v = (Vacuna) e;
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
        Vacuna vacuna = buscarVacunaPorTipo(mascota, tipo);
        if (vacuna != null) {
            mascota.getHistorial().remove(vacuna);
            System.out.println("🗑️ Vacunación '" + tipo + "' eliminada de " + mascota.getNombre());
            return true;
        }
        return false;
    }

    // Editar vacuna por tipo
    public boolean editarVacunaPorTipo(Mascota mascota, String tipoOriginal, Vacuna nuevaVacuna) {
        List<EventoClinico> historial = mascota.getHistorial();
        for (int i = 0; i < historial.size(); i++) {
            EventoClinico e = historial.get(i);
            if (e instanceof Vacuna) {
                Vacuna v = (Vacuna) e;
                if (v.getTipoVacuna().equalsIgnoreCase(tipoOriginal)) {
                    historial.set(i, nuevaVacuna);
                    System.out.println("✏️ Vacunación actualizada.");
                    return true;
                }
            }
        }
        return false;
    }
}
