package vista;

import controlador.VeterinarioControlador;
import modelo.Veterinario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PanelVeterinarios extends JInternalFrame {
    private VeterinarioControlador controlador;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField txtNombre, txtTelefono, txtEspecialidad, txtBuscarID;
    private JCheckBox chkDisponible;

    public PanelVeterinarios() {
        super("Gestión de Veterinarios", true, true, true, true);
        controlador = new VeterinarioControlador();
        setSize(800, 400);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Veterinario"));

        txtNombre = new JTextField();
        txtTelefono = new JTextField();
        txtEspecialidad = new JTextField();
        txtBuscarID = new JTextField();
        chkDisponible = new JCheckBox("¿Está disponible?");

        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Teléfono:"));
        panelFormulario.add(txtTelefono);
        panelFormulario.add(new JLabel("Especialidad:"));
        panelFormulario.add(txtEspecialidad);
        panelFormulario.add(new JLabel("Disponible:"));
        panelFormulario.add(chkDisponible);
        panelFormulario.add(new JLabel("Buscar/Editar por ID:"));
        panelFormulario.add(txtBuscarID);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        panelBotones.add(btnAgregar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);

        // Tabla
        modelo = new DefaultTableModel(new String[]{"Nombre", "ID", "Teléfono", "Especialidad", "Disponible"}, 0);
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        // Agregar componentes
        add(panelFormulario, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        actualizarTabla();

        // Listeners
        btnAgregar.addActionListener(e -> {
            controlador.agregar(
                    txtNombre.getText(),
                    txtTelefono.getText(),
                    txtEspecialidad.getText(),
                    chkDisponible.isSelected()
            );
            limpiarCampos();
            actualizarTabla();
        });

        btnBuscar.addActionListener((ActionEvent e) -> {
            String id = txtBuscarID.getText();
            Veterinario v = controlador.buscarPorID(id);
            if (v != null) {
                txtNombre.setText(v.getNombre());
                txtTelefono.setText(v.getTelefono());
                txtEspecialidad.setText(v.getEspecialidad());
                chkDisponible.setSelected(v.isDisponible());
            } else {
                JOptionPane.showMessageDialog(this, "Veterinario no encontrado.");
            }
        });

        btnEditar.addActionListener((ActionEvent e) -> {
            String id = txtBuscarID.getText();
            boolean actualizado = controlador.actualizarPorID(
                    id,
                    txtNombre.getText(),
                    txtTelefono.getText(),
                    txtEspecialidad.getText(),
                    chkDisponible.isSelected()
            );
            if (actualizado) {
                JOptionPane.showMessageDialog(this, "Veterinario actualizado.");
                limpiarCampos();
                actualizarTabla();
            } else {
                JOptionPane.showMessageDialog(this, "Veterinario no encontrado para actualizar.");
            }
        });

        btnEliminar.addActionListener((ActionEvent e) -> {
            String id = txtBuscarID.getText();
            boolean eliminado = controlador.eliminar(id);
            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Veterinario eliminado.");
                limpiarCampos();
                actualizarTabla();
            } else {
                JOptionPane.showMessageDialog(this, "Veterinario no encontrado.");
            }
        });
    }

    private void actualizarTabla() {
        modelo.setRowCount(0); // Limpiar
        for (Veterinario v : controlador.getListaVeterinarios()) {
            modelo.addRow(new Object[]{
                    v.getNombre(),
                    v.getDocumento(),
                    v.getTelefono(),
                    v.getEspecialidad(),
                    v.isDisponible() ? "Sí" : "No"
            });
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtTelefono.setText("");
        txtEspecialidad.setText("");
        txtBuscarID.setText("");
        chkDisponible.setSelected(false);
    }
}
