/*package vista;

import modelo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PanelEventosClinicos extends JPanel {
    private List<EventoClinico> eventosClinicos = new ArrayList<>();

    private JButton btnAgregarConsulta;
    private JButton btnMostrarEventos;
    private JTextArea areaEventos;

    public PanelEventosClinicos() {
        setLayout(new BorderLayout());

        // Panel superior con botones
        JPanel panelBotones = new JPanel();
        btnAgregarConsulta = new JButton("Agregar Consulta");
        btnMostrarEventos = new JButton("Mostrar Eventos");
        panelBotones.add(btnAgregarConsulta);
        panelBotones.add(btnMostrarEventos);

        // Área de texto para mostrar eventos
        areaEventos = new JTextArea(20, 50);
        areaEventos.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaEventos);

        add(panelBotones, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        configurarEventos();
    }

    private void configurarEventos() {
        btnAgregarConsulta.addActionListener(this::agregarConsultaEjemplo);
        btnMostrarEventos.addActionListener(e -> mostrarEventosClinicos());
    }

    // Agrega una consulta de ejemplo (para probar)
    private void agregarConsultaEjemplo(ActionEvent e) {
        Mascota mascota = new Mascota("Firulais", "Perro", 3);
        Veterinario vet = new Veterinario("Dra. Martínez", "123", "3210000", "Medicina interna", true);

        ConsultaVeterinaria consulta = new ConsultaVeterinaria(
                LocalDate.now(),
                mascota,
                "Consulta general por decaimiento",
                "Gastroenteritis leve",
                "Dieta blanda y reposo",
                Arrays.asList("Omeprazol", "Suero oral"),
                vet
        );

        eventosClinicos.add(consulta);
        JOptionPane.showMessageDialog(this, "Consulta agregada correctamente.");
    }

    private void mostrarEventosClinicos() {
        if (eventosClinicos.isEmpty()) {
            areaEventos.setText("No hay eventos clínicos registrados.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (EventoClinico evento : eventosClinicos) {
            sb.append(evento.mostrarDetalle()).append("\n------------------------\n");
        }
        areaEventos.setText(sb.toString());
    }
}
*/