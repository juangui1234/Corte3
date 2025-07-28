package util;

import controlador.ConsultaControlador;
import dto.ConsultaDTO;
import modelo.Mascota;
import modelo.Veterinario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelConsultassss extends JPanel {

    private JTable tablaConsultas;
    private DefaultTableModel modeloTabla;
    private JButton btnVerDetalle;
    private ConsultaControlador consultaControlador;
    private List<ConsultaDTO> listaConsultas;
    private List<Mascota> listaMascotas;              // 🆕
    private List<Veterinario> listaVeterinarios;      // 🆕

    public PanelConsultassss(List<Mascota> listaMascotas, List<Veterinario> listaVeterinarios, ConsultaControlador consultaControlador) {
        this.consultaControlador = consultaControlador;
        this.listaMascotas = listaMascotas;                 // 🆕
        this.listaVeterinarios = listaVeterinarios;         // 🆕
        this.listaConsultas = consultaControlador.obtenerConsultas();

        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Mascota");
        modeloTabla.addColumn("Veterinario");

        tablaConsultas = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaConsultas);
        add(scroll, BorderLayout.CENTER);

        btnVerDetalle = new JButton("Ver Detalle");
        btnVerDetalle.addActionListener(e -> mostrarDetalleConsulta());
        add(btnVerDetalle, BorderLayout.SOUTH);

        cargarConsultas();
    }

    private void cargarConsultas() {
        modeloTabla.setRowCount(0);
        listaConsultas = consultaControlador.obtenerConsultas();

        for (ConsultaDTO dto : listaConsultas) {
            modeloTabla.addRow(new Object[]{
                    dto.getIdConsulta(),
                    dto.getFecha(),
                    dto.getNombreMascota(),
                    dto.getNombreVeterinario()
            });
        }
    }

    private void mostrarDetalleConsulta() {
        int fila = tablaConsultas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione una consulta.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ConsultaDTO consulta = listaConsultas.get(fila);

        StringBuilder detalle = new StringBuilder();
        detalle.append("ID: ").append(consulta.getIdConsulta()).append("\n");
        detalle.append("Fecha: ").append(consulta.getFecha()).append("\n");
        detalle.append("Mascota: ").append(consulta.getNombreMascota()).append("\n");
        detalle.append("Descripción: ").append(consulta.getDescripcion()).append("\n");
        detalle.append("Veterinario: ").append(consulta.getNombreVeterinario()).append("\n");
        detalle.append("Especialidad: ").append(consulta.getEspecialidadVeterinario());

        JOptionPane.showMessageDialog(this, detalle.toString(), "Detalle de Consulta", JOptionPane.INFORMATION_MESSAGE);
    }
}
