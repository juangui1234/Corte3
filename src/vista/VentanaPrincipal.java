package vista;

import controlador.*;
import dao.*;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private JDesktopPane desktopPane;
    private MascotaDAO mascotaDAO;
    private CrudMascotas crudMascotas;

    private MascotaControlador mascotaControlador;
    private PropietarioControlador propietarioControlador;
    private VeterinarioControlador veterinarioControlador;

    public VentanaPrincipal() {
        setTitle("Sistema de Gestión Clínica Veterinaria");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Instanciación de controladores y DAO
        mascotaDAO = new MascotaDAO();
        crudMascotas = new CrudMascotas(); // Para paneles de vacunas y consultas
        propietarioControlador = new PropietarioControlador();
        mascotaControlador = new MascotaControlador();
        veterinarioControlador = new VeterinarioControlador();

        desktopPane = new JDesktopPane();
        add(desktopPane, BorderLayout.CENTER);

        crearMenu();

        JOptionPane.showMessageDialog(
                this,
                "👋 Bienvenido al Sistema de Gestión Clínica Veterinaria de Mascotas **LA MEJOR**\nRealizado por Juan Guillermo Salazar",
                "Bienvenido",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void crearMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = crearMenuArchivo();
        JMenu menuVista = new JMenu("Vista");

        // Gestión Mascotas
        JMenuItem itemGestion = new JMenuItem("Gestión Mascotas");
        itemGestion.addActionListener(_ -> {
            PanelGestionMascotas panel = new PanelGestionMascotas();
            desktopPane.add(panel);
            panel.setVisible(true);
        });

        // Vacunas
        JMenuItem itemVacunas = new JMenuItem("Vacunas");
        itemVacunas.addActionListener(_ -> {
            PanelVacunas panel = new PanelVacunas(crudMascotas);
            desktopPane.add(panel);
            panel.setVisible(true);
        });

        // Pacientes
        JMenuItem itemPacientes = new JMenuItem("Pacientes");
        itemPacientes.addActionListener(_ -> {
            ListaPacientes lista = new ListaPacientes(mascotaControlador, propietarioControlador);
            desktopPane.add(lista);
            lista.setVisible(true);
        });

        // Registro Consulta
        JMenuItem itemConsulta = new JMenuItem("Registrar ConsultaVeterinaria");
        itemConsulta.addActionListener(_ -> {
            PanelRegistrarConsulta panel = new PanelRegistrarConsulta(
                    crudMascotas.getMascotas(),
                    veterinarioControlador.getListaVeterinarios()
            );
            desktopPane.add(panel);
            panel.setVisible(true);
        });

        // Veterinarios
        JMenuItem itemVeterinarios = new JMenuItem("Gestión Veterinarios");
        itemVeterinarios.addActionListener(_ -> {
            PanelVeterinarios panel = new PanelVeterinarios();
            desktopPane.add(panel);
            panel.setVisible(true);
        });

        // Agregamos al menú Vista
        menuVista.add(itemGestion);
        menuVista.add(itemVacunas);
        menuVista.add(itemPacientes);
        menuVista.add(itemConsulta);
        menuVista.add(itemVeterinarios);

        // Agregar menús a la barra
        menuBar.add(menuArchivo);
        menuBar.add(menuVista);

        setJMenuBar(menuBar);
    }

    private JMenu crearMenuArchivo() {
        JMenu menuArchivo = new JMenu("Archivo");

        JMenuItem itemPropietarios = new JMenuItem("Registrar Propietario");
        itemPropietarios.addActionListener(_ -> {
            PanelPropietarios panel = new PanelPropietarios();
            desktopPane.add(panel);
            panel.setVisible(true);
        });
        JMenuItem itemVeterinariosArchivo = new JMenuItem("Registrar Veterinarios");
        itemVeterinariosArchivo.addActionListener(_ -> {
            PanelVeterinarios panel = new PanelVeterinarios();
            desktopPane.add(panel);
            panel.setVisible(true);
        });
        menuArchivo.add(itemVeterinariosArchivo);
        JMenuItem itemRegistrarConsulta = new JMenuItem("Registrar Consulta Veterinaria");
        itemRegistrarConsulta.addActionListener(_ -> {
            PanelRegistrarConsulta panel = new PanelRegistrarConsulta(
                    crudMascotas.getMascotas(),
                    veterinarioControlador.getListaVeterinarios()
            );
            desktopPane.add(panel);
            panel.setVisible(true);
        });

        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(_ -> {
            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Estás seguro que deseas salir?",
                    "Confirmar salida",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            if (opcion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        menuArchivo.add(itemPropietarios);
        menuArchivo.add(itemRegistrarConsulta);
        menuArchivo.addSeparator();
        menuArchivo.add(itemSalir);
        return menuArchivo;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}
