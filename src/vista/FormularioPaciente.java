package src.vista;

import controlador.MascotaControlador;
import controlador.PropietarioControlador;
import dto.MascotaDTO;
import modelo.Mascota;
import modelo.Propietario;

import javax.swing.*;
import java.awt.*;

public class FormularioPaciente extends JInternalFrame {

    private MascotaControlador mascotaControlador;
   // private CrudPropietarios crudPropietarios;
    private PropietarioControlador propietarioControlador;

    public FormularioPaciente(MascotaControlador controladorMascotas, PropietarioControlador propietarioControlador) {
        super("Nuevo Paciente", true, true, true, true);
        this.mascotaControlador = mascotaControlador;
        this.propietarioControlador = propietarioControlador;


                //this.crudPropietarios = crudPropietarios;

        setSize(400, 300);
        setLayout(new GridLayout(6, 2, 5, 5));

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();

        JLabel lblEspecie = new JLabel("Especie:");
        JComboBox<String> cbEspecie = new JComboBox<>(new String[]{"Perro", "Gato", "Ave", "Otro"});

        JLabel lblEdad = new JLabel("Edad:");
        JSpinner spEdad = new JSpinner(new SpinnerNumberModel(1, 0, 50, 1));

        JLabel lblPropietario = new JLabel("Propietario:");
        JComboBox<Propietario> comboPropietarios = new JComboBox<>();
        for (Propietario p : propietarioControlador.obtenerPropietarios()) {
            comboPropietarios.addItem(p);
        }

        JButton btnRegistrar = new JButton("Registrar");

        btnRegistrar.addActionListener(_ -> {
            String nombre = txtNombre.getText().trim();
            String especie = cbEspecie.getSelectedItem().toString();
            int edad = (int) spEdad.getValue();
            Propietario propietarioSeleccionado = (Propietario) comboPropietarios.getSelectedItem();

            if (nombre.isEmpty() || propietarioSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Nombre y propietario son obligatorios.");
                return;
            }

            try {
                MascotaDTO mascotaDTO = new MascotaDTO(nombre, especie, edad);
                mascotaControlador.registrarMascota(mascotaDTO);

                Mascota mascota = new Mascota(nombre, especie, edad);
                propietarioSeleccionado.agregarMascota(mascota);

                JOptionPane.showMessageDialog(this, "Mascota registrada con éxito.");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        add(lblNombre);
        add(txtNombre);
        add(lblEspecie);
        add(cbEspecie);
        add(lblEdad);
        add(spEdad);
        add(lblPropietario);
        add(comboPropietarios);
        add(new JLabel());
        add(btnRegistrar);
    }
}
