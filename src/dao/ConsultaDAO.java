package dao;

import dto.ConsultaDTO;
import persistencia.GestorPersistencia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    private final String archivo = "data/consultas.dat";
    private final GestorPersistencia gestor;

    public ConsultaDAO() {
        this.gestor = GestorPersistencia.getInstance();
        File carpeta = new File("data");
        if (!carpeta.exists()) {
            carpeta.mkdirs(); // Crea carpeta si no existe
        }
    }

    // Guarda todas las consultas
    public void guardarConsultas(List<ConsultaDTO> lista) {
        gestor.guardarLista(archivo, lista);
    }

    // Carga todas las consultas desde archivo
    public List<ConsultaDTO> cargarConsultas() {
        List<ConsultaDTO> lista = gestor.cargarLista(archivo);
        return (lista != null) ? lista : new ArrayList<>();
    }
}
