package src.vista;

import controlador.PropietarioControlador;
import dto.PropietarioDTO;

import javax.swing.*;
import java.awt.*;

public class PanelRegistrarPropietario extends JInternalFrame {

    private JTextField campoNombre;
    private JTextField campoDocumento;
    private JTextField campoTelefono;
    private JTextField campoDireccion;
    private PropietarioControlador controlador;

    public PanelRegistrarPropietario(PropietarioControlador controlador) {
        this.controlador = controlador;

        setTitle("Registrar Propietario");
        setClosable(true);
        setSize(400, 300);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Nombre:"));
        campoNombre = new JTextField();
        add(campoNombre);

        add(new JLabel("Documento:"));
        campoDocumento = new JTextField();
        add(campoDocumento);

        add(new JLabel("Teléfono:"));
        campoTelefono = new JTextField();
        add(campoTelefono);

        add(new JLabel("Dirección:"));
        campoDireccion = new JTextField();
        add(campoDireccion);

        JButton botonRegistrar = new JButton("Registrar");
        botonRegistrar.addActionListener(e -> registrarPropietario());
        add(botonRegistrar);

        JButton botonLimpiar = new JButton("Limpiar");
        botonLimpiar.addActionListener(e -> limpiarCampos());
        add(botonLimpiar);
    }

    private void registrarPropietario() {
        String nombre = campoNombre.getText();
        String documento = campoDocumento.getText();
        String telefono = campoTelefono.getText();
        String direccion = campoDireccion.getText();

        PropietarioDTO dto = new PropietarioDTO(nombre, documento, telefono, direccion);

        try {
            controlador.registrar(dto); // ✅ No devuelve boolean
            JOptionPane.showMessageDialog(this, "Propietario registrado correctamente");
            limpiarCampos();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void limpiarCampos() {
        campoNombre.setText("");
        campoDocumento.setText("");
        campoTelefono.setText("");
        campoDireccion.setText("");
    }
}
