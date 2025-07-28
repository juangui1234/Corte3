/*package modelo;

import java.util.ArrayList;
import java.util.List;


public class Historial implements List implements Serializable <EventoClinico> {
    private List<Consulta> consultaVeterinarias;

    public Historial() {
        this.consultaVeterinarias = new ArrayList<>();
    }

    public void agregarConsulta(Consulta consultaVeterinaria) {
        if (consultaVeterinaria != null) {
            consultaVeterinarias.add(consultaVeterinaria);
        }
    }

    public void mostrarConsultas() {
        if (consultaVeterinarias.isEmpty()) {
            System.out.println("Sin consultaVeterinarias registradas.");
        } else {
            for (Consulta c : consultaVeterinarias) {
                c.mostrarConsulta();
                System.out.println("--------------------------");
            }
        }
    }

    public List<Consulta> getConsultas() {
        return new ArrayList<>(consultaVeterinarias);
    }
}
*/