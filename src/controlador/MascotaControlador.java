package controlador;

import dao.MascotaDAO;
import dto.MascotaDTO;
import util.IDGenerator;
import java.util.ArrayList;
import java.util.List;

public class MascotaControlador {
    private final MascotaDAO dao;
    public List<MascotaDTO> listaMascotas;

    public MascotaControlador() {
        this.dao = new MascotaDAO();
        this.listaMascotas = dao.cargarMascotas();  // Cargar al iniciar
        IDGenerator.inicializarDesdeDatos(
                listaMascotas.stream().map(MascotaDTO::getCodigo).toList(),
                "M-"
        );
    }
    public void registrarMascota(MascotaDTO dto) {
        validar(dto);

        if (dto.getCodigo() == null || dto.getCodigo().isBlank()) {
            dto.setCodigo(IDGenerator.generarCodigoMascota());
        }

        listaMascotas.add(dto);
        dao.guardarMascotas(listaMascotas);
    }

    public List<MascotaDTO> obtenerMascotas() {
        return new ArrayList<>(listaMascotas); // Retornar copia para seguridad
    }

    public void eliminarMascotaPorNombre(String nombre) {
        listaMascotas.removeIf(m -> m.getNombre().equalsIgnoreCase(nombre));
        dao.guardarMascotas(listaMascotas);
    }

    public void actualizarMascota(String nombreClave, MascotaDTO nueva) {
        validar(nueva);
        for (int i = 0; i < listaMascotas.size(); i++) {
            if (listaMascotas.get(i).getNombre().equalsIgnoreCase(nombreClave)) {
                listaMascotas.set(i, nueva);
                dao.guardarMascotas(listaMascotas);
                return;
            }
        }
        throw new IllegalArgumentException("Mascota no encontrada con el nombre: " + nombreClave);
    }

    private void validar(MascotaDTO dto) {
        if (dto.getNombre() == null || dto.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (dto.getEspecie() == null || dto.getEspecie().isBlank()) {
            throw new IllegalArgumentException("La especie no puede estar vacía.");
        }
        if (dto.getEdad() < 0 || dto.getEdad() > 100) {
            throw new IllegalArgumentException("Edad inválida.");
        }
    }
}
