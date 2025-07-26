package src.vista;

import controlador.*;
import dao.*;
import dao.CrudMascotas;
import dao.CrudPropietarios;
import dao.CrudVeterinarios;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private MascotaDAO mascotaDAO;
    private JDesktopPane desktopPane;
    private CrudMascotas crudMascotas;
    private CrudPropietarios crudPropietarios;
    private CrudVeterinarios crudVeterinarios;
    private MascotaControlador mascotaControlador;
    private PropietarioControlador propietarioControlador;

    public VentanaPrincipal() {
        setTitle("Sistema de Gestión Clínica Veterinaria");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        //crudPropietarios = new CrudPropietarios(); //crud propietario
        //crudMascotas = new CrudMascotas(); // Instancia central del CRUD
        crudVeterinarios = new CrudVeterinarios();
        mascotaDAO = new MascotaDAO();
        propietarioControlador = new PropietarioControlador();

        desktopPane = new JDesktopPane();
        add(desktopPane, BorderLayout.CENTER);

        crearMenu();
        JOptionPane.showMessageDialog(
                this,
                "👋 Bienvenido al Sistema de Gestión Clínica Veterinaria de Mascotas **LA MEJOR** realizado por Juan Guillermo Salazar",
                "Bienvenido",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    private void crearMenu() {
        JMenuBar menuBar = new JMenuBar();

        // Menú Archivo
        JMenu menuArchivo = getJMenu();

        //menu vista
        JMenu menuVista = new JMenu("vista");


        JMenu menuConsultas = new JMenu("Consultas / Eventos");
        JMenuItem itemEventosClinicos = new JMenuItem("Ver Eventos Clínicos");

        itemEventosClinicos.addActionListener(e -> {
            PanelEventosClinicos panel = new PanelEventosClinicos();
            desktopPane.add(panel); // Asegúrate que 'escritorio' sea tu JDesktopPane
            panel.setVisible(true);
        });

        menuConsultas.add(itemEventosClinicos);
        menuVista.add(menuConsultas);

        JMenuItem itemPacientes = new JMenuItem("Pacientes");
        itemPacientes.addActionListener(_ -> {
            ListaPacientes lista = new ListaPacientes(mascotaControlador, propietarioControlador);
            desktopPane.add(lista);
            lista.setVisible(true);
        });

        JMenuItem itemHistorial = new JMenuItem("Historial de consultas");
        itemHistorial.addActionListener(_ -> {
            PanelVacunas panel = new PanelVacunas(crudMascotas);
            desktopPane.add(panel);
            panel.setVisible(true);
        });

        menuVista.add(itemHistorial);menuVista.add(itemHistorial);

        JMenuItem itemConsulta = new JMenuItem("Registrar ConsultaVeterinaria");
        itemConsulta.addActionListener(_ -> {
            PanelRegistrarConsulta panel = new PanelRegistrarConsulta(
                    crudMascotas.getMascotas(),
                    crudVeterinarios.getVeterinarios()
            );
            desktopPane.add(panel);
            panel.setVisible(true);
        });
        JMenuItem itemVacunas = new JMenuItem("Vacunas");
        itemVacunas.addActionListener(e -> {
            PanelVacunas panelVacunas = new PanelVacunas(crudMascotas); // 👈 este debe existir
            desktopPane.add(panelVacunas); // escritorio es tu JDesktopPane
            panelVacunas.setVisible(true);
        });
        menuVista.add(itemVacunas);

        JMenuItem itemGestion = new JMenuItem("Gestión Mascotas");
        itemGestion.addActionListener(_ -> {
            PanelGestionMascotas panelMascotas = new PanelGestionMascotas();
            desktopPane.add(panelMascotas);
            panelMascotas.setVisible(true);
        });
        menuVista.add(itemGestion);

        menuVista.add(itemPacientes);
        menuVista.add(itemConsulta);

        menuBar.add(menuArchivo);
        menuBar.add(menuVista);

        setJMenuBar(menuBar);
    }



    private JMenu getJMenu() {
        JMenu menuArchivo = new JMenu("Archivo");

        //menu propietarios
        JMenuItem itemPropietarios = new JMenuItem("Registrar Propietario");
        itemPropietarios.addActionListener(_ -> {
            PanelRegistrarPropietario panel = new PanelRegistrarPropietario(propietarioControlador);
            desktopPane.add(panel);
            panel.setVisible(true);
        });
        menuArchivo.add(itemPropietarios);

        JMenuItem itemNuevo = new JMenuItem("Registrar Mascota");
        itemNuevo.addActionListener(_ -> {
            MascotaControlador mascotaControlador = new MascotaControlador();
            FormularioPaciente form = new FormularioPaciente(mascotaControlador, propietarioControlador);
            desktopPane.add(form);
            form.setVisible(true);
        });


       // Registra la consulta
        JMenuItem itemRegistrarConsulta = new JMenuItem("Registrar ConsultaVeterinaria");
        itemRegistrarConsulta.addActionListener(_ -> {
            PanelRegistrarConsulta panel = new PanelRegistrarConsulta(
                    crudMascotas.getMascotas(),
                    crudVeterinarios.getVeterinarios() // ✅ CORREGIDO
            );
            desktopPane.add(panel);
            panel.setVisible(true);
        });
        menuArchivo.add(itemRegistrarConsulta);

        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(_ -> {
            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Estas seguro que deseas salir?",
                    "Confirma tu salida",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (opcion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        menuArchivo.add(itemNuevo);
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