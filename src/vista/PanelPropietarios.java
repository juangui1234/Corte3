package vista;

import controlador.PropietarioControlador;
import modelo.Propietario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelPropietarios extends JInternalFrame {
    private PropietarioControlador propietarioControlador;
    private JTextField txtNombre, txtDocumento, txtTelefono, txtDireccion;
    private JList<String> listaPropietarios;
    private DefaultListModel<String> modeloLista;
    private String documentoSeleccionado = null;
    private PanelMascotas panelMascotas; // para actualizar combo en mascotas

    // 🔹 Constructor principal
    public PanelPropietarios(PropietarioControlador propietarioControlador) {
        super("Gestión de Propietarios", true, true, true, true);
        this.propietarioControlador = propietarioControlador;
        initUI();
    }

    // 🔹 Constructor por defecto (para pruebas sueltas)
    public PanelPropietarios() {
        this(new PropietarioControlador());
    }

    // Inicializa la interfaz
    private void initUI() {
        setSize(600, 450);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior - Formulario
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2));
        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Documento:"));
        txtDocumento = new JTextField();
        panelFormulario.add(txtDocumento);

        panelFormulario.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelFormulario.add(txtTelefono);

        panelFormulario.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panelFormulario.add(txtDireccion);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(this::agregarPropietario);
        panelFormulario.add(btnAgregar);

        JButton btnListar = new JButton("Listar");
        btnListar.addActionListener(e -> mostrarPropietarios());
        panelFormulario.add(btnListar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(this::actualizarPropietario);
        panelFormulario.add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(this::eliminarPropietario);
        panelFormulario.add(btnEliminar);

        add(panelFormulario, BorderLayout.NORTH);

        // Panel central - lista
        modeloLista = new DefaultListModel<>();
        listaPropietarios = new JList<>(modeloLista);
        listaPropietarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaPropietarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarDatosDesdeSeleccion();
            }
        });

        JScrollPane scroll = new JScrollPane(listaPropietarios);
        add(scroll, BorderLayout.CENTER);

        // Cargar propietarios al iniciar
        mostrarPropietarios();
    }

    // 🔹 Métodos de CRUD
    private void agregarPropietario(ActionEvent e) {
        String nombre = txtNombre.getText().trim();
        String documento = txtDocumento.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String direccion = txtDireccion.getText().trim();

        if (propietarioControlador.buscarPorIdentificacion(documento) != null) {
            JOptionPane.showMessageDialog(this, "⚠️ Ya existe un propietario con ese documento.");
            return;
        }

        boolean exito = propietarioControlador.agregarPropietario(nombre, documento, telefono, direccion);
        if (exito) {
            JOptionPane.showMessageDialog(this, "✅ Propietario agregado correctamente");
            limpiarCampos();
            mostrarPropietarios();

            // 🔄 Actualiza combo de mascotas si el panel está enlazado
            if (panelMascotas != null) {
                panelMascotas.recargarComboPropietarios();
            }
        } else {
            JOptionPane.showMessageDialog(this, "❌ Error al agregar propietario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarPropietario(ActionEvent e) {
        if (documentoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "⚠️ Selecciona un propietario para actualizar.");
            return;
        }

        String nombre = txtNombre.getText().trim();
        String documento = txtDocumento.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String direccion = txtDireccion.getText().trim();

        Propietario p = propietarioControlador.buscarPorIdentificacion(documentoSeleccionado);
        if (p != null) {
            try {
                p.setNombre(nombre);
                p.setDocumento(documento);
                p.setTelefono(telefono);
                p.setDireccion(direccion);

                propietarioControlador.guardarPropietarios();
                mostrarPropietarios();
                limpiarCampos();
                JOptionPane.showMessageDialog(this, "✅ Propietario actualizado.");
                documentoSeleccionado = null;

                if (panelMascotas != null) {
                    panelMascotas.recargarComboPropietarios();
                }

            } catch (excepciones.DatoInvalidoException ex) {
                JOptionPane.showMessageDialog(this,
                        "❌ No se pudo actualizar el propietario: " + ex.getMessage(),
                        "Error de validación", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarPropietario(ActionEvent e) {
        if (documentoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "⚠️ Selecciona un propietario para eliminar.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de eliminar este propietario?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            propietarioControlador.eliminarPropietario(documentoSeleccionado);
            mostrarPropietarios();
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "🗑️ Propietario eliminado.");
            documentoSeleccionado = null;

            if (panelMascotas != null) {
                panelMascotas.recargarComboPropietarios();
            }
        }
    }

    // 🔹 Métodos auxiliares
    private void cargarDatosDesdeSeleccion() {
        String seleccion = listaPropietarios.getSelectedValue();
        if (seleccion != null && seleccion.contains("]")) {
            String[] partes = seleccion.split(" - ");
            if (partes.length >= 4) {
                txtNombre.setText(partes[0].split("] ")[1]);
                txtDocumento.setText(partes[1]);
                txtTelefono.setText(partes[2]);
                txtDireccion.setText(partes[3]);
                documentoSeleccionado = partes[1];
            }
        }
    }

    private void mostrarPropietarios() {
        modeloLista.clear();
        for (Propietario p : propietarioControlador.getPropietarios()) {
            modeloLista.addElement("[" + p.getCodigo() + "] " + p.getNombre() + " - "
                    + p.getDocumento() + " - " + p.getTelefono() + " - " + p.getDireccion());
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtDocumento.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        listaPropietarios.clearSelection();
        documentoSeleccionado = null;
    }

    public void setPanelMascotas(PanelMascotas panelMascotas) {
        this.panelMascotas = panelMascotas;
    }
}
