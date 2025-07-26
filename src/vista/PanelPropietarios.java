package vista;

import controlador.PropietarioControlador;
import modelo.Propietario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PanelPropietarios extends JFrame {
    private PropietarioControlador controlador;

    private JTextField txtNombre, txtDocumento, txtTelefono, txtDireccion;
    private JTextArea areaListado;

    public PanelPropietarios() {
        controlador = new PropietarioControlador();
        setTitle("Gestión de Propietarios");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel superior - Formulario
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2));
        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Documento:"));
        txtDocumento = new JTextField();
        panelFormulario.add(txtDocumento);

        panelFormulario.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelFormulario.add(txtTelefono);

        panelFormulario.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panelFormulario.add(txtDireccion);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(this::agregarPropietario);
        panelFormulario.add(btnAgregar);

        JButton btnListar = new JButton("Listar");
        btnListar.addActionListener(e -> mostrarPropietarios());
        panelFormulario.add(btnListar);

        add(panelFormulario, BorderLayout.NORTH);

        // Área de listado
        areaListado = new JTextArea();
        areaListado.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaListado);
        add(scroll, BorderLayout.CENTER);
    }

    private void agregarPropietario(ActionEvent e) {
        String nombre = txtNombre.getText().trim();
        String documento = txtDocumento.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String direccion = txtDireccion.getText().trim();

        boolean exito = controlador.agregarPropietario(nombre, documento, telefono, direccion);
        if (exito) {
            JOptionPane.showMessageDialog(this, "✅ Propietario agregado correctamente");
            limpiarCampos();
            mostrarPropietarios();
        } else {
            JOptionPane.showMessageDialog(this, "❌ Error al agregar propietario. Verifica los datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarPropietarios() {
        StringBuilder sb = new StringBuilder();
        for (Propietario p : controlador.getPropietarios()) {
            sb.append("[").append(p.getCodigo()).append("] ") // ✅ Mostramos el código
                    .append(p.getNombre()).append(" - ")
                    .append(p.getDocumento()).append(" - ")
                    .append(p.getTelefono()).append(" - ")
                    .append(p.getDireccion()).append("\n");
        }
        areaListado.setText(sb.toString());
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtDocumento.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PanelPropietarios().setVisible(true);
        });
    }
}
