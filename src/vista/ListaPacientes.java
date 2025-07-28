package vista;

import controlador.MascotaControlador;
import controlador.PropietarioControlador;
import dto.MascotaDTO;
import dto.PropietarioDTO;
import modelo.Propietario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ListaPacientes extends JInternalFrame {

    private JTable tabla;
    private DefaultTableModel modelo;
    private JProgressBar barraCarga;
    private MascotaControlador mascotaControlador;
    private PropietarioControlador propietarioControlador;

    public ListaPacientes(MascotaControlador mascotaControlador, PropietarioControlador propietarioControlador) {
        super("Lista de pacientes", true, true, true, true);
        this.mascotaControlador = mascotaControlador;
        this.propietarioControlador = propietarioControlador;

        setSize(600, 400);
        setLayout(new BorderLayout());
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
        timer.addActionListener((ActionEvent e) -> {
            if (progreso[0] >= 100) {
                ((Timer) e.getSource()).stop();
                llenarTabla();
                barraCarga.setString("Datos cargados");
            } else {
                progreso[0] += 5;
                barraCarga.setValue(progreso[0]);
            }
        });
        timer.start();
    }

    private void llenarTabla() {
        modelo.setRowCount(0);
        List<MascotaDTO> listaMascotas = mascotaControlador.obtenerMascotas();

        for (MascotaDTO m : listaMascotas) {
            String propietarioNombre = obtenerPropietarioDeMascota(m.getNombre());
            modelo.addRow(new Object[]{
                    m.getNombre(),
                    m.getEspecie(),
                    m.getEdad(),
                    propietarioNombre
            });
        }
    }

    private String obtenerPropietarioDeMascota(String nombreMascota) {
        for (dto.PropietarioDTO p : propietarioControlador.obtenerPropietarios()) {
            if (p.getMascotas() != null) {
                for (dto.MascotaDTO m : p.getMascotas()) {
                    if (m.getNombre().equalsIgnoreCase(nombreMascota)) {
                        return p.getNombre();
                    }
                }
            }
        }
        return "Desconocido";
    }
}
