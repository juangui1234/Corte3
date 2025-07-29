package vista;

import controlador.MascotaControlador;
import controlador.PropietarioControlador;
import dto.MascotaDTO;
import dto.PropietarioDTO;
import excepciones.EdadInvalidaException;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class PanelMascotas extends JInternalFrame {

    private final MascotaControlador mascotaControlador;
    private final PropietarioControlador propietarioControlador;
    private JTable tablaMascotas;
    private DefaultTableModel modeloTabla;
    private JTextField txtNombre, txtEspecie, txtEdad, txtCodigo;
    private JComboBox<String> comboPropietarios;

    public PanelMascotas(MascotaControlador mascotaControlador, PropietarioControlador propietarioControlador) {
        this.mascotaControlador = mascotaControlador;
        this.propietarioControlador = propietarioControlador;

        setTitle("Gestión de Mascotas");
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        setSize(800, 600);
        setLayout(new BorderLayout());

        initComponentes();
        cargarMascotas();
        cargarPropietarios();
    }

    private void initComponentes() {
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtNombre = new JTextField(15);
        txtEspecie = new JTextField(15);
        txtEdad = new JTextField(15);
        txtCodigo = new JTextField(15);
        comboPropietarios = new JComboBox<>();

        // Primera fila
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtNombre, gbc);
        gbc.gridx = 2;
        panelFormulario.add(new JLabel("Especie:"), gbc);
        gbc.gridx = 3;
        panelFormulario.add(txtEspecie, gbc);

        // Segunda fila
        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Edad:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtEdad, gbc);
        gbc.gridx = 2;
        //panelFormulario.add(new JLabel("Código:"), gbc);
        //gbc.gridx = 3;
        //panelFormulario.add(txtCodigo, gbc);

        // Tercera fila
        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Propietario:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        panelFormulario.add(comboPropietarios, gbc);

        JButton btnRecargar = new JButton("Recargar Propietarios");
        btnRecargar.addActionListener(e -> cargarPropietarios());
        gbc.gridx = 3; gbc.gridwidth = 1;
        panelFormulario.add(btnRecargar, gbc);

        // Cuarta fila (botones)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        JButton btnRegistrar = new JButton("Registrar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");

        btnRegistrar.addActionListener(this::registrarMascota);
        btnActualizar.addActionListener(this::actualizarMascota);
        btnEliminar.addActionListener(this::eliminarMascota);

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 4;
        panelFormulario.add(panelBotones, gbc);

        // Tabla
        modeloTabla = new DefaultTableModel(new Object[]{"Código", "Nombre", "Especie", "Edad", "Propietario"}, 0);
        tablaMascotas = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaMascotas);

        tablaMascotas.getSelectionModel().addListSelectionListener(e -> {
            int fila = tablaMascotas.getSelectedRow();
            if (fila >= 0) {
                txtCodigo.setText(modeloTabla.getValueAt(fila, 0).toString());
                txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
                txtEspecie.setText(modeloTabla.getValueAt(fila, 2).toString());
                txtEdad.setText(modeloTabla.getValueAt(fila, 3).toString());
                comboPropietarios.setSelectedItem(modeloTabla.getValueAt(fila, 4).toString());
            }
        });

        add(panelFormulario, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);
    }

    private void cargarPropietarios() {
        comboPropietarios.removeAllItems();
        List<PropietarioDTO> propietarios = propietarioControlador.obtenerPropietarios();
        for (PropietarioDTO p : propietarios) {
            comboPropietarios.addItem(p.getDocumento() + " - " + p.getNombre());
        }
    }

    private void cargarMascotas() {
        modeloTabla.setRowCount(0);
        for (MascotaDTO m : mascotaControlador.obtenerMascotas()) {
            modeloTabla.addRow(new Object[]{
                    m.getCodigo(),
                    m.getNombre(),
                    m.getEspecie(),
                    m.getEdad(),
                    m.getPropietarioId()
            });
        }
    }

    private void registrarMascota(ActionEvent e) {
        try {
            String nombre = txtNombre.getText();
            String especie = txtEspecie.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            if (edad < 0 || edad > 30) {
                throw new EdadInvalidaException("La edad de la mascota debe estar entre 0 y 30 años.");
            }
            String codigo = txtCodigo.getText();
            String propietarioSeleccionado = (String) comboPropietarios.getSelectedItem();

            if (propietarioSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un propietario.");
                return;
            }

            String idPropietario = propietarioSeleccionado.split(" - ")[0];

            MascotaDTO dto = new MascotaDTO(nombre, especie, edad, codigo, idPropietario);
            mascotaControlador.registrarMascota(dto);
            JOptionPane.showMessageDialog(this, "Mascota registrada con éxito.");
            limpiarCampos();
            cargarMascotas();
        } catch (EdadInvalidaException ex) {
            JOptionPane.showMessageDialog(this, "⚠️ " + ex.getMessage(), "Error de edad", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "⚠️ La edad debe ser un número válido.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


    }

    private void actualizarMascota(ActionEvent e) {
        try {
            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();
            String especie = txtEspecie.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            if (edad < 0 || edad > 30) {
                throw new EdadInvalidaException("La edad de la mascota debe estar entre 0 y 30 años.");
            }
            String propietarioSeleccionado = (String) comboPropietarios.getSelectedItem();

            if (propietarioSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un propietario.");
                return;
            }

            String idPropietario = propietarioSeleccionado.split(" - ")[0];

            MascotaDTO dto = new MascotaDTO(nombre, especie, edad, codigo, idPropietario);
            mascotaControlador.actualizarMascota(nombre, dto);
            JOptionPane.showMessageDialog(this, "Mascota actualizada con éxito.");
            limpiarCampos();
            cargarMascotas();
        } catch (EdadInvalidaException ex) {
            JOptionPane.showMessageDialog(this, "⚠️ " + ex.getMessage(), "Error de edad", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "⚠️ La edad debe ser un número válido.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarMascota(ActionEvent e) {
        try {
            String nombre = txtNombre.getText();
            mascotaControlador.eliminarMascotaPorNombre(nombre);
            JOptionPane.showMessageDialog(this, "Mascota eliminada.");
            limpiarCampos();
            cargarMascotas();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
        }
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtEspecie.setText("");
        txtEdad.setText("");
        comboPropietarios.setSelectedIndex(-1);
    }
    public void recargarComboPropietarios() {
        cargarPropietarios();
    }
}
