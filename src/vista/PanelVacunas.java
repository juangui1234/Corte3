package vista;

import controlador.VacunaControlador;
import dto.VacunaDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class PanelVacunas extends JInternalFrame {

    private JTextField txtNombreMascota, txtTipoVacuna, txtLote, txtDescripcion, txtFecha, txtProximaDosis;
    private JTable tablaVacunas;
    private DefaultTableModel modeloTabla;
    private final VacunaControlador vacunaControlador;

    public PanelVacunas() {
        setTitle("Gestión de Vacunas");
        setSize(800, 400);
        setClosable(true);
        setLayout(new BorderLayout());

        vacunaControlador = new VacunaControlador();
        initComponentes();
        cargarVacunasEnTabla();
    }

    private void initComponentes() {
        JPanel panelFormulario = new JPanel(new GridLayout(7, 2, 5, 5));

        panelFormulario.add(new JLabel("Nombre Mascota:"));
        txtNombreMascota = new JTextField();
        panelFormulario.add(txtNombreMascota);

        panelFormulario.add(new JLabel("Tipo de Vacuna:"));
        txtTipoVacuna = new JTextField();
        panelFormulario.add(txtTipoVacuna);

        panelFormulario.add(new JLabel("Lote:"));
        txtLote = new JTextField();
        panelFormulario.add(txtLote);

        panelFormulario.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        panelFormulario.add(txtDescripcion);

        panelFormulario.add(new JLabel("Fecha (yyyy-MM-dd):"));
        txtFecha = new JTextField();
        panelFormulario.add(txtFecha);

        panelFormulario.add(new JLabel("Próxima Dosis (yyyy-MM-dd):"));
        txtProximaDosis = new JTextField();
        panelFormulario.add(txtProximaDosis);

        JButton btnRegistrar = new JButton("Registrar Vacuna");
        btnRegistrar.addActionListener(e -> registrarVacuna());
        panelFormulario.add(btnRegistrar);

        JButton btnEliminar = new JButton("Eliminar por Tipo");
        btnEliminar.addActionListener(e -> eliminarVacuna());
        panelFormulario.add(btnEliminar);

        add(panelFormulario, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new String[]{
                "Mascota", "Tipo", "Lote", "Descripción", "Fecha", "Próxima"
        }, 0);
        tablaVacunas = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaVacunas);
        add(scroll, BorderLayout.CENTER);
    }

    private void registrarVacuna() {
        try {
            String nombre = txtNombreMascota.getText().trim();
            String tipo = txtTipoVacuna.getText().trim();
            String lote = txtLote.getText().trim();
            String descripcion = txtDescripcion.getText().trim();
            LocalDate fecha = LocalDate.parse(txtFecha.getText().trim());
            LocalDate proxima = LocalDate.parse(txtProximaDosis.getText().trim());

            // Registro correcto pasando los 6 argumentos
            vacunaControlador.registrarVacuna(nombre, descripcion, fecha, tipo, lote, proxima);

            JOptionPane.showMessageDialog(this, "Vacuna registrada correctamente");
            limpiarCampos();
            cargarVacunasEnTabla();

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Usa yyyy-MM-dd");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void eliminarVacuna() {
        String nombre = txtNombreMascota.getText().trim();
        String tipo = txtTipoVacuna.getText().trim();
        if (nombre.isBlank() || tipo.isBlank()) {
            JOptionPane.showMessageDialog(this, "Nombre y tipo de vacuna requeridos.");
            return;
        }
        boolean eliminada = vacunaControlador.eliminarVacunaPorTipo(nombre, tipo);
        if (eliminada) {
            JOptionPane.showMessageDialog(this, "Vacuna eliminada.");
            cargarVacunasEnTabla();
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró la vacuna.");
        }
    }

    private void cargarVacunasEnTabla() {
        modeloTabla.setRowCount(0);
        List<VacunaDTO> vacunas = vacunaControlador.listarVacunas();
        for (VacunaDTO v : vacunas) {
            modeloTabla.addRow(new Object[]{
                    v.getNombreMascota(),
                    v.getTipoVacuna(),
                    v.getLote(),
                    v.getDescripcion(),
                    v.getFecha(),
                    v.getProximaDosis()
            });
        }
    }

    private void limpiarCampos() {
        txtNombreMascota.setText("");
        txtTipoVacuna.setText("");
        txtLote.setText("");
        txtDescripcion.setText("");
        txtFecha.setText("");
        txtProximaDosis.setText("");
    }
}
