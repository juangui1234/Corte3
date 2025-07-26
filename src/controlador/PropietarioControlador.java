package src.controlador;

import dao.PropietarioDAO;
import dto.PropietarioDTO;
import modelo.Propietario;

import java.util.List;

public class PropietarioControlador {
    private PropietarioDAO dao = new PropietarioDAO();

    public void registrar(PropietarioDTO dto) {
        validar(dto);
        Propietario p = new Propietario(dto.getNombre(), dto.getDocumento(), dto.getTelefono(), dto.getDireccion());
        dao.guardar(p);
    }

    public List<Propietario> obtenerPropietarios() {
        return dao.listar();
    }

    public void eliminar(String documento) {
        dao.eliminarPorDocumento(documento);
    }

    public void actualizar(String docClave, PropietarioDTO dto) {
        validar(dto);
        Propietario nuevo = new Propietario(dto.getNombre(), dto.getDocumento(), dto.getTelefono(), dto.getDireccion());
        dao.actualizar(docClave, nuevo);
    }

    private void validar(PropietarioDTO dto) {
        if (dto.getNombre() == null || dto.getNombre().isBlank())
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        if (dto.getDocumento() == null || dto.getDocumento().isBlank())
            throw new IllegalArgumentException("El documento no puede estar vacío.");
        if (dto.getTelefono() == null || dto.getTelefono().length() < 7)
            throw new IllegalArgumentException("El teléfono es inválido.");
        if (dto.getDireccion() == null || dto.getDireccion().isBlank())
            throw new IllegalArgumentException("La dirección no puede estar vacía.");
    }
}
