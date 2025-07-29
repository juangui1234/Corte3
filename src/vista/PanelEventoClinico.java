package vista;

import controlador.ConsultaControlador;
import controlador.VacunaControlador;
import controlador.VeterinarioControlador;
import dto.ConsultaDTO;
import dto.VacunaDTO;
import modelo.Veterinario;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class PanelEventoClinico extends JInternalFrame {

    private final ConsultaControlador consultaControlador;
    private final VacunaControlador vacunaControlador;
    private final VeterinarioControlador veterinarioControlador;

    private final JTextArea areaEventos;

    public PanelEventoClinico() {
        setTitle("EventoClínico Completo");
        setSize(800, 500);
        setClosable(true);
        setIconifiable(true);
        setLayout(new BorderLayout());

        this.consultaControlador = new ConsultaControlador();
        this.vacunaControlador = new VacunaControlador();
        this.veterinarioControlador = new VeterinarioControlador();

        setLayout(new BorderLayout());

        // Panel de botones
        JPanel panelBotones = new JPanel();
        JButton btnRegistrarConsulta = new JButton("➕ Consulta");
        JButton btnRegistrarVacuna = new JButton("💉 Vacuna");
        JButton btnMostrar = new JButton("📋 Ver Todos");

        panelBotones.add(btnRegistrarConsulta);
        panelBotones.add(btnRegistrarVacuna);
        panelBotones.add(btnMostrar);

        // Área de texto
        areaEventos = new JTextArea(20, 60);
        areaEventos.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaEventos);

        add(panelBotones, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // Eventos de botones
        //btnRegistrarConsulta.addActionListener(e -> registrarConsultaDemo());
      //  btnRegistrarVacuna.addActionListener(e -> registrarVacunaDemo());
        btnMostrar.addActionListener(e -> mostrarEventos());
    }

    // 📋 Mostrar todos los eventos
    private void mostrarEventos() {
        StringBuilder sb = new StringBuilder();

        List<ConsultaDTO> consultas = consultaControlador.obtenerConsultas();
        List<VacunaDTO> vacunas = vacunaControlador.listarVacunas();

        if (consultas.isEmpty() && vacunas.isEmpty()) {
            sb.append("📭 No hay eventos clínicos registrados.\n");
        } else {
            if (!consultas.isEmpty()) {
                sb.append("📘 CONSULTAS:\n");
                for (ConsultaDTO c : consultas) {
                    sb.append("- ID: ").append(c.getIdConsulta())
                            .append("\n Mascota: ").append(c.getNombreMascota())
                            .append("\n Veterinario: ").append(c.getNombreVeterinario())
                            .append("\n Diagnóstico: ").append(c.getDiagnostico())
                            .append("\n Tratamiento: ").append(c.getTratamiento())
                            .append("\n Medicamentos: ").append(c.getMedicamentos())
                            .append("\n Fecha: ").append(c.getFecha())
                            .append("\n Descripción: ").append(c.getDescripcion())
                            .append("\n-------------------------------\n");
                }
            }

            if (!vacunas.isEmpty()) {
                sb.append("💉 VACUNAS:\n");
                for (VacunaDTO v : vacunas) {
                    sb.append("- Mascota: ").append(v.getNombreMascota())
                            .append("\n Tipo: ").append(v.getTipoVacuna())
                            .append("\n Lote: ").append(v.getLote())
                            .append("\n Fecha: ").append(v.getFecha())
                            .append("\n Próxima dosis: ").append(v.getProximaDosis())
                            .append("\n Descripción: ").append(v.getDescripcion())
                            .append("\n-------------------------------\n");
                }
            }
        }

        areaEventos.setText(sb.toString());
    }

}
