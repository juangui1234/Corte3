package vista;

import controlador.ConsultaControlador;
import dao.MascotaDAO;
import dao.VeterinarioDAO;
import dto.ConsultaDTO;
import dto.MascotaDTO;
import modelo.Mascota;
import modelo.Veterinario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PanelConsulta extends JInternalFrame {

    private JComboBox<Mascota> comboMascotas;
    private JComboBox<Veterinario> comboVeterinarios;
    private JTextField txtDiagnostico, txtTratamiento, txtMedicamentos;
    private JTextArea areaDescripcion;
    private JButton btnRegistrar, btnEditar, btnEliminar;
    private JTable tablaConsultas;
    private DefaultTableModel modeloTabla;

    private final ConsultaControlador consultaControlador;

    public PanelConsulta() {
        super("Gestión de Consultas", true, true, true, true);
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        consultaControlador = new ConsultaControlador();

        comboMascotas = new JComboBox<>();
        comboVeterinarios = new JComboBox<>();
        txtDiagnostico = new JTextField(20);
        txtTratamiento = new JTextField(20);
        txtMedicamentos = new JTextField(20);
        areaDescripcion = new JTextArea(4, 20);
        areaDescripcion.setLineWrap(true);
        areaDescripcion.setWrapStyleWord(true);

        btnRegistrar = new JButton("Registrar Consulta");
        btnEditar = new JButton("Editar Consulta");
        btnEliminar = new JButton("Eliminar Consulta");

        cargarMascotas();
        cargarVeterinarios();

        // ---------- PANEL FORMULARIO ----------
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Formulario de Consulta"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Mascota:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(comboMascotas, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panelFormulario.add(new JLabel("Veterinario:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(comboVeterinarios, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panelFormulario.add(new JLabel("Diagnóstico:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtDiagnostico, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panelFormulario.add(new JLabel("Tratamiento:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtTratamiento, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panelFormulario.add(new JLabel("Medicamentos:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtMedicamentos, gbc);

        gbc.gridx = 0; gbc.gridy++;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panelFormulario.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1;
        JScrollPane scrollDescripcion = new JScrollPane(areaDescripcion);
        scrollDescripcion.setPreferredSize(new Dimension(250, 80));
        panelFormulario.add(scrollDescripcion, gbc);

        // ---------- PANEL BOTONES ----------
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);

        // ---------- PANEL SUPERIOR ----------
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        // ---------- TABLA ----------
        modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "Fecha", "Mascota", "Veterinario", "Diagnóstico", "Tratamiento", "Medicamentos", "Descripción"}, 0);

        tablaConsultas = new JTable(modeloTabla); // <-- ESTA LÍNEA FALTABA

        JScrollPane scrollTabla = new JScrollPane(tablaConsultas);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Consultas Registradas"));

        // ---------- AGREGAR A FRAME ----------
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);

        // Eventos
        btnRegistrar.addActionListener(e -> registrarConsulta());
        btnEditar.addActionListener(e -> editarConsulta());
        btnEliminar.addActionListener(e -> eliminarConsulta());
        tablaConsultas.getSelectionModel().addListSelectionListener(e -> cargarConsultaSeleccionada());

        // Cargar datos desde archivo
        cargarConsultasEnTabla();
    }

    private void cargarMascotas() {
        MascotaDAO mascotaDAO = new MascotaDAO();
        List<MascotaDTO> mascotasDTO = mascotaDAO.cargarMascotas();
        for (MascotaDTO dto : mascotasDTO) {
            comboMascotas.addItem(new Mascota(dto.getNombre(), dto.getCodigo(), dto.getEdad()));
        }
    }

    private void cargarVeterinarios() {
        VeterinarioDAO veterinarioDAO = new VeterinarioDAO();
        List<Veterinario> veterinarios = veterinarioDAO.cargarVeterinarios();
        for (Veterinario vet : veterinarios) {
            if (vet.isDisponible()) {
                comboVeterinarios.addItem(vet);
            }
        }
    }

    private void registrarConsulta() {
        try {
            Mascota mascota = (Mascota) comboMascotas.getSelectedItem();
            Veterinario veterinario = (Veterinario) comboVeterinarios.getSelectedItem();
            String diagnostico = txtDiagnostico.getText().trim();
            String tratamiento = txtTratamiento.getText().trim();
            String medicamentosTexto = txtMedicamentos.getText().trim();
            String descripcion = areaDescripcion.getText().trim();

            if (mascota == null || veterinario == null || diagnostico.isEmpty()
                    || tratamiento.isEmpty() || medicamentosTexto.isEmpty() || descripcion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<String> medicamentos = new ArrayList<>();
            for (String med : medicamentosTexto.split(",")) {
                medicamentos.add(med.trim());
            }

            LocalDate fecha = LocalDate.now();

            boolean registrado = consultaControlador.registrarConsulta(
                    fecha,
                    mascota.getNombre(),
                    descripcion,
                    txtMedicamentos.getText().trim(),
                    txtDiagnostico.getText().trim(),
                    txtTratamiento.getText().trim(),
                    veterinario
            );

            if (registrado) {
                String id = consultaControlador.obtenerConsultas().getLast().getIdConsulta();
                modeloTabla.addRow(new Object[]{
                        id,
                        fecha.toString(),
                        mascota.getNombre(),
                        veterinario.getNombre(),
                        diagnostico,
                        tratamiento,
                        medicamentosTexto,
                        descripcion
                });

                JOptionPane.showMessageDialog(this, "✅ Consulta registrada correctamente.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "❌ No se pudo registrar la consulta.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "❌ Error al registrar consulta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarConsulta() {
        int fila = tablaConsultas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una consulta para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        modeloTabla.setValueAt(txtDiagnostico.getText().trim(), fila, 4);
        modeloTabla.setValueAt(txtTratamiento.getText().trim(), fila, 5);
        modeloTabla.setValueAt(txtMedicamentos.getText().trim(), fila, 6);
        JOptionPane.showMessageDialog(this, "✏️ Consulta actualizada visualmente (no se persiste en archivo).");
    }

    private void eliminarConsulta() {
        int fila = tablaConsultas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una consulta para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String idConsulta = (String) modeloTabla.getValueAt(fila, 0);
        int confirmar = JOptionPane.showConfirmDialog(this, "¿Eliminar la consulta con ID: " + idConsulta + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {
            consultaControlador.eliminarConsultaPorID(idConsulta);
            modeloTabla.removeRow(fila);
            JOptionPane.showMessageDialog(this, "🗑️ Consulta eliminada.");
        }
    }

    private void cargarConsultaSeleccionada() {
        int fila = tablaConsultas.getSelectedRow();
        if (fila != -1) {
            txtDiagnostico.setText((String) modeloTabla.getValueAt(fila, 4));
            txtTratamiento.setText((String) modeloTabla.getValueAt(fila, 5));
            txtMedicamentos.setText((String) modeloTabla.getValueAt(fila, 6));
        }
    }

    private void limpiarCampos() {
        txtDiagnostico.setText("");
        txtTratamiento.setText("");
        txtMedicamentos.setText("");
        areaDescripcion.setText("");
        comboMascotas.setSelectedIndex(0);
        comboVeterinarios.setSelectedIndex(0);
    }

    private void cargarConsultasEnTabla() {
        List<ConsultaDTO> lista = consultaControlador.obtenerConsultas();
        DefaultTableModel modelo = (DefaultTableModel) tablaConsultas.getModel();
        modelo.setRowCount(0); // Limpiar tabla

        for (ConsultaDTO c : lista) {
            modelo.addRow(new Object[]{
                    c.getIdConsulta(),
                    c.getFecha(),
                    c.getNombreMascota(),
                    c.getNombreVeterinario(),
                    c.getDiagnostico(),
                    c.getTratamiento(),
                    c.getMedicamentos(),
                    c.getDescripcion()
            });
        }
    }
}
