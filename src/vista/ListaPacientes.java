/*package vista;

import controlador.MascotaControlador;
import controlador.PropietarioControlador;
import dao.CrudMascotas;
//import dao.CrudPropietarios;
import modelo.Mascota;
import modelo.Propietario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListaPacientes extends JInternalFrame {

    private CrudMascotas crudMascotas;
    //private CrudPropietarios crudPropietarios;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JProgressBar barraCarga;
    private MascotaControlador mascotaControlador;
    private PropietarioControlador propietarioControlador;

    public ListaPacientes(MascotaControlador mascotaControlador, PropietarioControlador propietarioControlador) {
        super("Lista de pacientes", true, true, true, true);
        this.crudMascotas = crudMascotas;
        //this.crudPropietarios = crudPropietarios;
        this.mascotaControlador = mascotaControlador;
        this.propietarioControlador = propietarioControlador;

        setSize(600, 400);
        setLayout(new BorderLayout());
        setClosable(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        modelo = new DefaultTableModel(new String[]{"Nombre", "Especie", "Edad", "Propietario"}, 0);
        tabla = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        barraCarga = new JProgressBar(0, 100);
        barraCarga.setStringPainted(true);
        add(barraCarga, BorderLayout.SOUTH);

        cargarDatosConSimulacion();
    }

    private void cargarDatosConSimulacion() {
        modelo.setRowCount(0); // Limpia la tabla

        Timer timer = new Timer(30, null); // 30 ms por ciclo
        final int[] progreso = {0};
        timer.addActionListener(e -> {
            if (progreso[0] >= 100) {
                ((Timer) e.getSource()).stop();

                crudMascotas.getMascotas();
            } else {
                progreso[0] += 5;
                barraCarga.setValue(progreso[0]);
                llenarTabla();
            }
        });
        timer.start();
    }

    private void llenarTabla() {

        MascotaControlador controlador = new MascotaControlador(); // Asegúrate de importar bien
        List<Mascota> lista = controlador.obtenerMascotas();
        modelo.setRowCount(0); //limpia la tabla

        /*for (Mascota m : lista) {
            // Buscar propietario asociado a la mascota con el archivo txt
            String propietarioNombre = obtenerPropietarioDeMascota(m);
            modelo.addRow(new Object[]{
                    m.getNombre(),
                    m.getEspecie(),
                    m.getEdad(),
                    propietarioNombre
            });
        }

        barraCarga.setValue(100);
        barraCarga.setString("Datos cargados");
    }

    /*private String obtenerPropietarioDeMascota(Mascota mascota) {
        for (Propietario p : crudPropietarios.getTodos()) {
            if (p != null && p.getNombre() != null) {
                if (p.getMascotas() != null && p.getMascotas().contains(mascota)) {
                    return p.getNombre();
                }
            }
        }
        return "Desconocido";
    }
}*/