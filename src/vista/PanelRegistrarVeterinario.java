/*package vista;

import controlador.VeterinarioControlador;
import modelo.Veterinario;

import javax.swing.*;
import java.awt.*;

public class PanelRegistrarVeterinario extends JInternalFrame {

    private JTextField txtNombre, txtTelefono, txtEspecialidad;
    private JCheckBox chkDisponible;
    private JTextArea areaListado;
    private VeterinarioControlador controlador;

    public PanelRegistrarVeterinario() {
        super("Gestión de Veterinarios", true, true, true, true);
        controlador = new VeterinarioControlador();
        inicializarComponentes();
        setSize(400, 400);
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));

        txtNombre = new JTextField();
        txtTelefono = new JTextField();
        txtEspecialidad = new JTextField();
        chkDisponible = new JCheckBox("Disponible");
        areaListado = new JTextArea();
        areaListado.setEditable(false);

        JButton btnAgregar = new JButton("Agregar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnListar = new JButton("Listar");

        btnAgregar.addActionListener(e -> {
            controlador.agregarVeterinario(
                    txtNombre.getText(),
                    txtTelefono.getText(),
                    txtEspecialidad.getText(),
                    chkDisponible.isSelected()
            );
            JOptionPane.showMessageDialog(this, "Veterinario agregado.");
        });

        btnBuscar.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Ingrese ID del veterinario a buscar:");
            Veterinario v = controlador.buscarPorId(id);
            if (v != null) {
                areaListado.setText("Nombre: " + v.getNombre() + "\nTel: " + v.getTelefono()
                        + "\nEsp: " + v.getEspecialidad() + "\nDisponible: " + v.isDisponible());
            } else {
                areaListado.setText("Veterinario no encontrado.");
            }
        });

        btnEditar.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Ingrese ID del veterinario a editar:");
            boolean ok = controlador.editarVeterinario(
                    id,
                    txtNombre.getText(),
                    txtTelefono.getText(),
                    txtEspecialidad.getText(),
                    chkDisponible.isSelected()
            );
            if (ok) {
                JOptionPane.showMessageDialog(this, "Veterinario actualizado.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el veterinario.");
            }
        });

        btnEliminar.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Ingrese ID del veterinario a eliminar:");
            if (controlador.eliminarVeterinario(id)) {
                JOptionPane.showMessageDialog(this, "Veterinario eliminado.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el veterinario.");
            }
        });

        btnListar.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (Veterinario v : controlador.listarVeterinarios()) {
                sb.append(v.getNombre()).append(" - ").append(v.getIdentificacion()).append("\n");
            }
            areaListado.setText(sb.toString());
        });

        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Teléfono:"));
        panel.add(txtTelefono);
        panel.add(new JLabel("Especialidad:"));
        panel.add(txtEspecialidad);
        panel.add(new JLabel("Disponible:"));
        panel.add(chkDisponible);

        panel.add(btnAgregar);
        panel.add(btnBuscar);
        panel.add(btnEditar);
        panel.add(btnEliminar);
        panel.add(btnListar);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(areaListado), BorderLayout.CENTER);
    }
}
*/