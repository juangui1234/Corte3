package controlador;

import dto.ConsultaDTO;
import modelo.Veterinario;
import util.IDGenerator;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsultaControlador {

    private List<ConsultaDTO> listaConsultas;
    private final String archivo = "consultas.dat";

    public ConsultaControlador() {
        listaConsultas = cargarConsultas();
    }

    public boolean registrarConsulta(LocalDate fecha, String nombreMascota, String descripcion,
                                     Veterinario veterinarioSeleccionado) {

        if (veterinarioSeleccionado == null || !veterinarioSeleccionado.isDisponible()) {
            return false; // Veterinario no disponible
        }

        String idConsulta = IDGenerator.generarCodigoConsulta();
        ConsultaDTO nueva = new ConsultaDTO(
                idConsulta,
                fecha,
                nombreMascota,
                descripcion,
                veterinarioSeleccionado.getDocumento(),
                veterinarioSeleccionado.getNombre(),
                veterinarioSeleccionado.getEspecialidad()
        );

        listaConsultas.add(nueva);
        guardarConsultas();
        return true;
    }

    public List<ConsultaDTO> obtenerConsultas() {
        return listaConsultas;
    }

    public ConsultaDTO buscarPorID(String idConsulta) {
        for (ConsultaDTO c : listaConsultas) {
            if (c.getIdConsulta().equalsIgnoreCase(idConsulta)) {
                return c;
            }
        }
        return null;
    }

    private void guardarConsultas() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
            out.writeObject(listaConsultas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<ConsultaDTO> cargarConsultas() {
        File file = new File(archivo);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<ConsultaDTO>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
