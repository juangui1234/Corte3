/*package vista;

import controlador.PropietarioControlador;
import modelo.Propietario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelGestionPropietarios extends JInternalFrame {

    private JTable tabla;
    private DefaultTableModel modelo;
    private PropietarioControlador controlador;

    public PanelGestionPropietarios(PropietarioControlador controlador) {
        this.controlador = controlador;

        setTitle("Gestión de Propietarios");
        setClosable(true);
        setSize(600, 400);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(new Object[]{"Nombre", "Documento", "Teléfono", "Dirección"}, 0);
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminarSeleccionado());
        add(btnEliminar, BorderLayout.SOUTH);

        cargarPropietarios(); // ✅ Cargar al inicio
    }

    private void cargarPropietarios() {
        modelo.setRowCount(0); // Limpiar tabla
        for (Propietario p : controlador.obtenerPropietarios()) {
            modelo.addRow(new Object[]{p.getNombre(), p.getDocumento(), p.getTelefono(), p.getDireccion()});
        }
    }

    private void eliminarSeleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            String documento = (String) modelo.getValueAt(fila, 1); // documento = columna 1
            controlador.eliminar(documento); // ✅ Uso directo
            cargarPropietarios(); // ✅ Refrescar tabla
            JOptionPane.showMessageDialog(this, "Propietario eliminado correctamente");
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un propietario para eliminar");
        }
    }
}
*/