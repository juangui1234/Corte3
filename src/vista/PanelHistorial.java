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
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

public class PanelHistorial extends JInternalFrame {

    private JTable tablaHistorial;
    private DefaultTableModel modeloTabla;
    private JComboBox<String> comboMascotas; // 🔹 Combo para filtrar

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

        // Panel superior: Filtro
        JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltro.add(new JLabel("Filtrar por Mascota:"));
        comboMascotas = new JComboBox<>();
        cargarMascotasEnCombo();
        panelFiltro.add(comboMascotas);
        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.addActionListener(e -> filtrarPorMascota());
        panelFiltro.add(btnFiltrar);

        add(panelFiltro, BorderLayout.NORTH);

        // Modelo de la tabla
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

        // Panel inferior: Botón imprimir
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnImprimir = new JButton("Imprimir Historial");
        btnImprimir.addActionListener(e -> imprimirHistorial());
        panelBotones.add(btnImprimir);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        cargarHistorialCompleto();
    }

    private void cargarMascotasEnCombo() {
        comboMascotas.removeAllItems();
        comboMascotas.addItem("Todas"); // opción para ver todo
        List<MascotaDTO> mascotas = mascotaControlador.obtenerMascotas();
        for (MascotaDTO mascota : mascotas) {
            comboMascotas.addItem(mascota.getNombre());
        }
    }

    private void cargarHistorialCompleto() {
        modeloTabla.setRowCount(0); // limpiar tabla
        try {
            List<ConsultaDTO> consultas = consultaControlador.obtenerConsultas();
            List<MascotaDTO> mascotas = mascotaControlador.obtenerMascotas();
            List<PropietarioDTO> propietarios = propietarioControlador.obtenerPropietarios();

            for (ConsultaDTO consulta : consultas) {
                agregarFila(consulta, mascotas, propietarios);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar el historial: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 🔹 Método para filtrar por mascota seleccionada
    private void filtrarPorMascota() {
        String seleccion = (String) comboMascotas.getSelectedItem();
        modeloTabla.setRowCount(0);

        try {
            List<ConsultaDTO> consultas = consultaControlador.obtenerConsultas();
            if (seleccion != null && !"Todas".equals(seleccion)) {
                consultas = consultas.stream()
                        .filter(c -> c.getNombreMascota().equalsIgnoreCase(seleccion))
                        .collect(Collectors.toList());
            }

            List<MascotaDTO> mascotas = mascotaControlador.obtenerMascotas();
            List<PropietarioDTO> propietarios = propietarioControlador.obtenerPropietarios();

            for (ConsultaDTO consulta : consultas) {
                agregarFila(consulta, mascotas, propietarios);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al filtrar historial: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 🔹 Reutilizado para agregar filas a la tabla
    private void agregarFila(ConsultaDTO consulta, List<MascotaDTO> mascotas, List<PropietarioDTO> propietarios) {
        String nombreMascota = consulta.getNombreMascota();

        MascotaDTO mascota = mascotas.stream()
                .filter(m -> m.getNombre().equalsIgnoreCase(nombreMascota))
                .findFirst().orElse(null);

        PropietarioDTO propietario = null;
        if (mascota != null && mascota.getPropietarioId() != null) {
            String documentoProp = mascota.getPropietarioId();
            propietario = propietarios.stream()
                    .filter(p -> p.getDocumento().equalsIgnoreCase(documentoProp))
                    .findFirst().orElse(null);
        }

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

    private void imprimirHistorial() {
        try {
            boolean completo = tablaHistorial.print(
                    JTable.PrintMode.FIT_WIDTH,
                    new MessageFormat("Historial Clínico de Consultas"),
                    new MessageFormat("Página {0}"),
                    true,
                    null,
                    true
            );

            if (completo) {
                JOptionPane.showMessageDialog(this, "Impresión realizada correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "La impresión fue cancelada.");
            }

        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(this, "Error al imprimir: " + ex.getMessage());
        }
    }
}
