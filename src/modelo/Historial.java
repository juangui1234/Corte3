package src.modelo;/*package modelo;

import java.util.ArrayList;
import java.util.List;


public class Historial implements List<EventoClinico> {
    private List<ConsultaVeterinaria> consultaVeterinarias;

    public Historial() {
        this.consultaVeterinarias = new ArrayList<>();
    }

    public void agregarConsulta(ConsultaVeterinaria consultaVeterinaria) {
        if (consultaVeterinaria != null) {
            consultaVeterinarias.add(consultaVeterinaria);
        }
    }

    public void mostrarConsultas() {
        if (consultaVeterinarias.isEmpty()) {
            System.out.println("Sin consultaVeterinarias registradas.");
        } else {
            for (ConsultaVeterinaria c : consultaVeterinarias) {
                c.mostrarConsulta();
                System.out.println("--------------------------");
            }
        }
    }

    public List<ConsultaVeterinaria> getConsultas() {
        return new ArrayList<>(consultaVeterinarias);
    }
}
*/