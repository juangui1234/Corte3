package vista;

import controlador.ConsultaControlador;
import controlador.MascotaControlador;
import controlador.PropietarioControlador;

import dto.ConsultaDTO;
import dto.MascotaDTO;
import dto.PropietarioDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelHistorial extends JInternalFrame {

    private JTable tablaHistorial;
    private DefaultTableModel modeloTabla;

    // Controladores usados
    private final ConsultaControlador consultaControlador;
    private final MascotaControlador mascotaControlador;
    private final PropietarioControlador propietarioControlador;

    public PanelHistorial() {
        setTitle("Historial Clínico Completo");
        setSize(800, 500);
        setClosable(true);
        setIconifiable(true);
        setLayout(new BorderLayout());

        // Inicializar controladores
        consultaControlador = new ConsultaControlador();
        mascotaControlador = new MascotaControlador();
        propietarioControlador = new PropietarioControlador();

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Código Consulta");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Mascota");
        modeloTabla.addColumn("Especie");
        modeloTabla.addColumn("Propietario");
        modeloTabla.addColumn("Veterinario");
        modeloTabla.addColumn("Especialidad");

        tablaHistorial = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaHistorial);

        add(scrollPane, BorderLayout.CENTER);

        cargarHistorialCompleto();
    }

    private void cargarHistorialCompleto() {
        try {
            List<ConsultaDTO> consultas = consultaControlador.obtenerConsultas();
            List<MascotaDTO> mascotas = mascotaControlador.obtenerMascotas();
            List<PropietarioDTO> propietarios = propietarioControlador.obtenerPropietarios();

            for (ConsultaDTO consulta : consultas) {
                String nombreMascota = consulta.getNombreMascota();
                String documentoVeterinario = consulta.getIdVeterinario();

                // Buscar la mascota por nombre
                MascotaDTO mascota = mascotas.stream()
                        .filter(m -> m.getNombre().equalsIgnoreCase(nombreMascota))
                        .findFirst().orElse(null);

                // Buscar el propietario asociado a la mascota
                PropietarioDTO propietario = null;
                if (mascota != null && mascota.getPropietarioId() != null) {
                    String documentoProp = mascota.getPropietarioId();
                    propietario = propietarios.stream()
                            .filter(p -> p.getDocumento().equalsIgnoreCase(documentoProp))
                            .findFirst().orElse(null);
                }

                // Agregar la fila a la tabla
                modeloTabla.addRow(new Object[]{
                        consulta.getIdConsulta(),
                        consulta.getFecha(),
                        mascota != null ? mascota.getNombre() : "N/D",
                        mascota != null ? mascota.getEspecie() : "N/D",
                        propietario != null ? propietario.getNombre() : "N/D",
                        consulta.getNombreVeterinario(),
                        consulta.getEspecialidadVeterinario()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar el historial: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
