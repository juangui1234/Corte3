package src.vista;

import modelo.CitaMedica;
import modelo.EventoClinico;
import modelo.Mascota;
import modelo.Vacunacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelConsultas extends JPanel {

    private JTable tablaEventos;
    private DefaultTableModel modeloTabla;
    private JButton btnVerDetalle;
    private List<EventoClinico> eventosTotales; // Lista combinada de todos los eventos de todas las mascotas

    public PanelConsultas(List<Mascota> mascotas) {
        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Mascota");
        modeloTabla.addColumn("Tipo");

        tablaEventos = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaEventos);
        add(scroll, BorderLayout.CENTER);

        btnVerDetalle = new JButton("Ver detalle");
        btnVerDetalle.addActionListener(e -> mostrarDetalleEvento());
        add(btnVerDetalle, BorderLayout.SOUTH);

        cargarEventos(mascotas);
    }

    private void cargarEventos(List<Mascota> mascotas) {
        modeloTabla.setRowCount(0); // Limpiar tabla
        eventosTotales = new java.util.ArrayList<>(); // Inicializar

        for (Mascota mascota : mascotas) {
            for (EventoClinico evento : mascota.getConsultas()) {
                eventosTotales.add(evento); // Guardamos para luego ver detalle

                String fecha = evento.getFecha().toString();
                String nombreMascota = mascota.getNombre();
                String tipo = (evento instanceof CitaMedica) ? "Cita Médica" :
                        (evento instanceof Vacunacion) ? "Vacunación" : "Otro";

                modeloTabla.addRow(new Object[]{fecha, nombreMascota, tipo});
            }
        }
    }

    private void mostrarDetalleEvento() {
        int filaSeleccionada = tablaEventos.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un evento clínico.");
            return;
        }

        EventoClinico evento = eventosTotales.get(filaSeleccionada);
        String mensaje = evento.mostrarDetalle();
        JOptionPane.showMessageDialog(this, mensaje, "Detalle del Evento", JOptionPane.INFORMATION_MESSAGE);
    }
}
