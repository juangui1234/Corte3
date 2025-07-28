package controlador;
import util.IDGenerator;
import dao.PropietarioDAO;
import dto.PropietarioDTO;
import excepciones.DatoInvalidoException;
import excepciones.ErrorPersistenciaException;
import modelo.Propietario;

import java.util.ArrayList;
import java.util.List;

public class PropietarioControlador {
    private ArrayList<Propietario> propietarios;
    private PropietarioDAO propietarioDAO;

    public PropietarioControlador() {
        propietarioDAO = new PropietarioDAO();
        propietarios = new ArrayList<>();
        cargarPropietarios(); // Cargar desde archivo al iniciar
    }

    public boolean agregarPropietario(String nombre, String documento, String telefono, String direccion) {
        try {
            String codigo = IDGenerator.generarCodigoPropietario(); // ✅ genera el código único

            Propietario nuevo = new Propietario(nombre, documento, telefono, direccion);
            nuevo.setCodigo(codigo); // ✅ le asignamos el código

            propietarios.add(nuevo); // ✅ lo agregamos a la lista
            guardarPropietarios();   // ✅ guardamos la lista
            return true;
        } catch (DatoInvalidoException e) {
            System.err.println("Error al agregar propietario: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Propietario> getPropietarios() {
        return propietarios;
    }

    public Propietario buscarPorIdentificacion(String id) {
        for (Propietario p : propietarios) {
            if (p.getDocumento().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    public void eliminarPropietario(String id) {
        Propietario p = buscarPorIdentificacion(id);
        if (p != null) {
            propietarios.remove(p);
            guardarPropietarios();
        }
    }

    public List<PropietarioDTO> obtenerPropietarios() {
        return convertirModeloaDTO(propietarios);
    }
    public void guardarPropietarios() {
        List<PropietarioDTO> listaDTO = convertirModeloaDTO(propietarios);
        propietarioDAO.guardarPropietarios(listaDTO);
        }

    public void cargarPropietarios() {
        List<PropietarioDTO> listaDTO = propietarioDAO.cargarPropietarios();
        propietarios = convertirDTOaModelo(listaDTO);

        //evitamos codigos duplicados
        List<String> codigos = new ArrayList<>();
        for (Propietario p : propietarios) {
            codigos.add(p.getCodigo());
        }
        IDGenerator.inicializarDesdeDatos(codigos, "P-");
    }

    // 🔁 Conversión de modelo a DTO
    private List<PropietarioDTO> convertirModeloaDTO(List<Propietario> listaModelo) {
        List<PropietarioDTO> listaDTO = new ArrayList<>();
        for (Propietario p : listaModelo) {
            listaDTO.add(new PropietarioDTO(
                    p.getNombre(),
                    p.getDocumento(),
                    p.getTelefono(),
                    p.getDireccion(),
                    p.getCodigo() // ✅ Agregar código al DTO
            ));
        }
        return listaDTO;
    }

    // 🔁 Conversión de DTO a modelo
    private ArrayList<Propietario> convertirDTOaModelo(List<PropietarioDTO> listaDTO) {
        ArrayList<Propietario> listaModelo = new ArrayList<>();
        for (PropietarioDTO dto : listaDTO) {
            try {
                Propietario propietario = new Propietario(
                        dto.getNombre(),
                        dto.getDocumento(),
                        dto.getTelefono(),
                        dto.getDireccion()
                );
                propietario.setCodigo(dto.getCodigo()); // ✅ Recuperar código
                listaModelo.add(propietario);
            } catch (DatoInvalidoException e) {
                System.err.println("❌ Error al cargar propietario desde DTO: " + e.getMessage());
            }
        }
        return listaModelo;
    }
    public Object[][] obtenerDatosParaTabla() {
        Object[][] datos = new Object[propietarios.size()][4];
        for (int i = 0; i < propietarios.size(); i++) {
            Propietario p = propietarios.get(i);
            datos[i][0] = p.getNombre();
            datos[i][1] = p.getDocumento();
            datos[i][2] = p.getTelefono();
            datos[i][3] = p.getDireccion();
        }
        return datos;
    }
}
