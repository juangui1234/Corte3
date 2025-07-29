package vista;

import controlador.ConsultaControlador;
import controlador.VacunaControlador;
import controlador.PropietarioControlador;
import controlador.VeterinarioControlador;
import dto.ConsultaDTO;
import dto.PropietarioDTO;
import dto.VacunaDTO;
import dto.VeterinarioDTO;
import modelo.Propietario;
import modelo.Veterinario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class PanelEventoClinico extends JInternalFrame {

    // Controladores
    private final ConsultaControlador consultaControlador;
    private final VacunaControlador vacunaControlador;
    private final PropietarioControlador propietarioControlador;
    private final VeterinarioControlador veterinarioControlador;

    // Componentes de tablas
    private JTable tablaConsultas, tablaVacunas, tablaPropietarios, tablaVeterinarios;
    private DefaultTableModel modeloConsultas, modeloVacunas, modeloPropietarios, modeloVeterinarios;

    // Filtro
    private JComboBox<String> comboMascotas;

    // Para abrir otros paneles
    private final JDesktopPane desktopPane;

    public PanelEventoClinico(JDesktopPane desktopPane) {
        this.desktopPane = desktopPane;

        setTitle("Gestión de Eventos Clínicos");
        setSize(1000, 650);
        setClosable(true);
        setIconifiable(true);
        setLayout(new BorderLayout());

        // Inicializar controladores
        consultaControlador = new ConsultaControlador();
        vacunaControlador = new VacunaControlador();
        propietarioControlador = new PropietarioControlador();
        veterinarioControlador = new VeterinarioControlador();

        // Inicializar tablas
        initTablas();

        // Panel superior con filtros y botones
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        comboMascotas = new JComboBox<>();
        comboMascotas.addItem("Todas las Mascotas");
        cargarMascotasEnCombo();

        JButton btnFiltrar = new JButton("Filtrar");
        JButton btnLimpiar = new JButton("Limpiar");
        JButton btnRegistrarConsulta = new JButton("➕ Consulta");
        JButton btnRegistrarVacuna = new JButton("💉 Vacuna");
        JButton btnImprimir = new JButton("🖨 Imprimir");

        panelSuperior.add(new JLabel("Mascota:"));
        panelSuperior.add(comboMascotas);
        panelSuperior.add(btnFiltrar);
        panelSuperior.add(btnLimpiar);
        panelSuperior.add(btnRegistrarConsulta);
        panelSuperior.add(btnRegistrarVacuna);
        panelSuperior.add(btnImprimir);

        add(panelSuperior, BorderLayout.NORTH);

        // Panel central con pestañas
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Consultas", new JScrollPane(tablaConsultas));
        tabs.addTab("Vacunas", new JScrollPane(tablaVacunas));
        tabs.addTab("Propietarios", new JScrollPane(tablaPropietarios));
        tabs.addTab("Veterinarios", new JScrollPane(tablaVeterinarios));
        add(tabs, BorderLayout.CENTER);

        // Listeners
        btnFiltrar.addActionListener(e -> filtrarPorMascota());
        btnLimpiar.addActionListener(e -> {
            comboMascotas.setSelectedIndex(0);
            cargarTablas();
        });
        btnImprimir.addActionListener(e -> imprimirEventoClinico(tabs.getSelectedIndex()));
        btnRegistrarConsulta.addActionListener(e -> abrirPanelConsulta());
        btnRegistrarVacuna.addActionListener(e -> abrirPanelVacuna());

        // Cargar datos iniciales
        cargarTablas();
    }

    private void initTablas() {
        // Consultas
        modeloConsultas = new DefaultTableModel(
                new String[]{"Código", "Fecha", "Mascota", "Veterinario", "Diagnóstico"}, 0
        );
        tablaConsultas = new JTable(modeloConsultas);

        // Vacunas
        modeloVacunas = new DefaultTableModel(
                new String[]{"Mascota", "Tipo", "Lote", "Fecha", "Próxima Dosis"}, 0
        );
        tablaVacunas = new JTable(modeloVacunas);

        // Propietarios
        modeloPropietarios = new DefaultTableModel(
                new String[]{"ID", "Nombre", "Teléfono", "Dirección"}, 0
        );
        tablaPropietarios = new JTable(modeloPropietarios);

        // Veterinarios
        modeloVeterinarios = new DefaultTableModel(
                new String[]{"ID", "Nombre", "Especialidad", "Disponible"}, 0
        );
        tablaVeterinarios = new JTable(modeloVeterinarios);
    }

    private void cargarMascotasEnCombo() {
        comboMascotas.removeAllItems();
        comboMascotas.addItem("Todas las Mascotas");

        // Obtiene las mascotas desde las consultas y vacunas
        List<String> nombres = consultaControlador.obtenerConsultas().stream()
                .map(ConsultaDTO::getNombreMascota)
                .collect(Collectors.toList());

        vacunaControlador.listarVacunas().forEach(v -> {
            if (!nombres.contains(v.getNombreMascota())) {
                nombres.add(v.getNombreMascota());
            }
        });

        for (String nombre : nombres) {
            comboMascotas.addItem(nombre);
        }
    }

    private void cargarTablas() {
        // Limpiar tablas
        modeloConsultas.setRowCount(0);
        modeloVacunas.setRowCount(0);
        modeloPropietarios.setRowCount(0);
        modeloVeterinarios.setRowCount(0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Consultas
        for (ConsultaDTO c : consultaControlador.obtenerConsultas()) {
            modeloConsultas.addRow(new Object[]{
                    c.getIdConsulta(),
                    c.getFecha().format(formatter),
                    c.getNombreMascota(),
                    c.getNombreVeterinario(),
                    c.getDiagnostico()
            });
        }

        // Vacunas
        for (VacunaDTO v : vacunaControlador.listarVacunas()) {
            modeloVacunas.addRow(new Object[]{
                    v.getNombreMascota(),
                    v.getTipoVacuna(),
                    v.getLote(),
                    v.getFecha().format(formatter),
                    v.getProximaDosis().format(formatter)
            });
        }

        // Propietarios
        for (PropietarioDTO p : propietarioControlador.obtenerPropietarios()) {
            modeloPropietarios.addRow(new Object[]{
                    p.getDocumento(),
                    p.getNombre(),
                    p.getTelefono(),
                    p.getDireccion()
            });
        }

        // Veterinarios
        for (Veterinario v : veterinarioControlador.getListaVeterinarios()) {
            modeloVeterinarios.addRow(new Object[]{
                    v.getTipo(),
                    v.getNombre(),
                    v.getEspecialidad(),
                    v.isDisponible() ? "Sí" : "No"
            });
        }
    }

    private void filtrarPorMascota() {
        String seleccion = (String) comboMascotas.getSelectedItem();
        if (seleccion == null || seleccion.equals("Todas las Mascotas")) {
            cargarTablas();
            return;
        }

        modeloConsultas.setRowCount(0);
        modeloVacunas.setRowCount(0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (ConsultaDTO c : consultaControlador.obtenerConsultas()) {
            if (c.getNombreMascota().equalsIgnoreCase(seleccion)) {
                modeloConsultas.addRow(new Object[]{
                        c.getIdConsulta(),
                        c.getFecha().format(formatter),
                        c.getNombreMascota(),
                        c.getNombreVeterinario(),
                        c.getDiagnostico()
                });
            }
        }

        for (VacunaDTO v : vacunaControlador.listarVacunas()) {
            if (v.getNombreMascota().equalsIgnoreCase(seleccion)) {
                modeloVacunas.addRow(new Object[]{
                        v.getNombreMascota(),
                        v.getTipoVacuna(),
                        v.getLote(),
                        v.getFecha().format(formatter),
                        v.getProximaDosis().format(formatter)
                });
            }
        }
    }

    private void imprimirEventoClinico(int pestaña) {
        JTable tabla;
        switch (pestaña) {
            case 0 -> tabla = tablaConsultas;
            case 1 -> tabla = tablaVacunas;
            case 2 -> tabla = tablaPropietarios;
            case 3 -> tabla = tablaVeterinarios;
            default -> throw new IllegalStateException("Índice de pestaña desconocido: " + pestaña);
        }

        try {
            boolean completo = tabla.print(
                    JTable.PrintMode.FIT_WIDTH,
                    new MessageFormat("Eventos Clínicos - Pestaña " + pestaña),
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

    // 🔹 Abrir paneles de registro reales
    private void abrirPanelConsulta() {
        PanelConsulta panel = new PanelConsulta();
        desktopPane.add(panel);
        panel.setVisible(true);
        panel.toFront();
    }

    private void abrirPanelVacuna() {
        PanelVacunas panel = new PanelVacunas(new controlador.MascotaControlador());
        desktopPane.add(panel);
        panel.setVisible(true);
        panel.toFront();
    }
}
