package vista;
import controlador.ConsultaControlador;
import dto.MascotaDTO;
import modelo.*;
import modelo.Consulta;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PanelConsulta extends JInternalFrame {

    private JComboBox<Mascota> comboMascotas;
    private JComboBox<Veterinario> comboVeterinarios;
    private JTextField txtDiagnostico;
    private JTextField txtTratamiento;
    private JTextField txtMedicamentos;
    private JTextField txtDescripcion;
    private JButton btnRegistrar;

    public PanelConsulta(List<MascotaDTO> mascotaDTOs, List<Veterinario> listaVeterinarios, ConsultaControlador controlador) {
        setLayout(new GridLayout(8, 2, 10, 10));

        comboMascotas = new JComboBox<>();
        comboVeterinarios = new JComboBox<>();
        txtDiagnostico = new JTextField();
        txtTratamiento = new JTextField();
        txtMedicamentos = new JTextField();
        txtDescripcion = new JTextField();
        btnRegistrar = new JButton("Registrar Consulta");

        for (MascotaDTO dto : mascotaDTOs) {
            // si tienes una conversión a modelo, úsala, si no, muestra nombre
            comboMascotas.addItem(new Mascota(dto.getNombre(), dto.getCodigo(), dto.getEdad()));
        }

        for (Veterinario v : listaVeterinarios) {
            comboVeterinarios.addItem(v);
        }

        add(new JLabel("Mascota:"));
        add(comboMascotas);
        add(new JLabel("Veterinario:"));
        add(comboVeterinarios);
        add(new JLabel("Diagnóstico:"));
        add(txtDiagnostico);
        add(new JLabel("Tratamiento:"));
        add(txtTratamiento);
        add(new JLabel("Medicamentos (separados por coma):"));
        add(txtMedicamentos);
        add(new JLabel("Descripción:"));
        add(txtDescripcion);
        add(new JLabel(""));
        add(btnRegistrar);

        btnRegistrar.addActionListener(e -> registrarConsulta());
    }

    private void registrarConsulta() {
        try {
            Mascota mascota = (Mascota) comboMascotas.getSelectedItem();
            Veterinario veterinario = (Veterinario) comboVeterinarios.getSelectedItem();
            String diagnostico = txtDiagnostico.getText().trim();
            String tratamiento = txtTratamiento.getText().trim();
            String descripcion = txtDescripcion.getText().trim();
            String medicamentosTexto = txtMedicamentos.getText().trim();

            if (mascota == null || veterinario == null ||
                    diagnostico.isEmpty() || tratamiento.isEmpty() || medicamentosTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<String> medicamentos = new ArrayList<>();
            for (String med : medicamentosTexto.split(",")) {
                medicamentos.add(med.trim());
            }

            LocalDate fecha = LocalDate.now(); // Fecha actual
            Consulta consulta = new Consulta(
                    fecha, mascota, descripcion, diagnostico, tratamiento, medicamentos, veterinario
            );

            mascota.agregarEvento(consulta);
            JOptionPane.showMessageDialog(this, "Consulta registrada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Limpiar campos
            txtDiagnostico.setText("");
            txtTratamiento.setText("");
            txtMedicamentos.setText("");
            txtDescripcion.setText("");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al registrar la consulta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}