package src.vista;

import dao.CrudMascotas;
import modelo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class PanelVacunas extends JInternalFrame {

    private CrudMascotas crudMascotas;

    private JComboBox<String> comboMascotas;
    private JTextField txtTipo, txtLote, txtFechaAplicacion, txtProximaDosis;
    private JTable tablaVacunas;
    private DefaultTableModel modeloTabla;

    public PanelVacunas(CrudMascotas crudMascotas) {
        this.crudMascotas = crudMascotas;

        setTitle("Gestión de Vacunas");
        setClosable(true);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Formulario
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 5, 5));

        comboMascotas = new JComboBox<>();
        cargarMascotas();

        txtTipo = new JTextField();
        txtLote = new JTextField();
        txtFechaAplicacion = new JTextField("2025-07-16");
        txtProximaDosis = new JTextField("2026-07-16");

        panelFormulario.add(new JLabel("Mascota:"));
        panelFormulario.add(comboMascotas);
        panelFormulario.add(new JLabel("Tipo vacuna:"));
        panelFormulario.add(txtTipo);
        panelFormulario.add(new JLabel("Lote:"));
        panelFormulario.add(txtLote);
        panelFormulario.add(new JLabel("Fecha aplicación (AAAA-MM-DD):"));
        panelFormulario.add(txtFechaAplicacion);
        panelFormulario.add(new JLabel("Próxima dosis (AAAA-MM-DD):"));
        panelFormulario.add(txtProximaDosis);

        JButton btnRegistrar = new JButton("Registrar vacuna");
        JButton btnListar = new JButton("Listar vacunas");
        panelFormulario.add(btnRegistrar);
        panelFormulario.add(btnListar);

        add(panelFormulario, BorderLayout.NORTH);

        // Tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new String[]{"Tipo", "Lote", "Aplicación", "Próxima dosis"});
        tablaVacunas = new JTable(modeloTabla);
        add(new JScrollPane(tablaVacunas), BorderLayout.CENTER);

        // Botones
        btnRegistrar.addActionListener(e -> registrarVacuna());
        btnListar.addActionListener(e -> listarVacunas());
    }

    private void cargarMascotas() {
        comboMascotas.removeAllItems();
        for (Mascota m : crudMascotas.getMascotas()) {
            comboMascotas.addItem(m.getNombre());
        }
    }

    private void registrarVacuna() {
        String nombreMascota = (String) comboMascotas.getSelectedItem();
        Mascota mascota = crudMascotas.buscarPorNombre(nombreMascota);

        try {
            String tipo = txtTipo.getText();
            String lote = txtLote.getText();
            LocalDate fechaAplicacion = LocalDate.parse(txtFechaAplicacion.getText());
            LocalDate proximaDosis = LocalDate.parse(txtProximaDosis.getText());

            String descripcion = "Vacuna " + tipo + " - Lote " + lote;

            Vacunacion v = new Vacunacion(
                    fechaAplicacion,
                    mascota,
                    descripcion,
                    tipo,
                    lote,
                    proximaDosis
            );

            mascota.agregarEvento(v);

            JOptionPane.showMessageDialog(this, "✅ Vacunación registrada correctamente.");
            listarVacunas();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ Error al registrar: " + ex.getMessage());
        }
    }

    private void listarVacunas() {
        modeloTabla.setRowCount(0); // limpiar tabla

        String nombreMascota = (String) comboMascotas.getSelectedItem();
        Mascota mascota = crudMascotas.buscarPorNombre(nombreMascota);

        if (mascota != null) {
            List<EventoClinico> eventos = mascota.getHistorial();
            for (EventoClinico evento : eventos) {
                if (evento instanceof Vacunacion) {
                    Vacunacion v = (Vacunacion) evento;
                    modeloTabla.addRow(new Object[]{
                            v.getTipoVacuna(),
                            v.getLote(),
                            v.getFecha(),
                            v.getProximaDosis()
                    });
                }
            }
        }
    }
}
