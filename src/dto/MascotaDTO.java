package dto;

import modelo.EventoClinico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MascotaDTO implements Serializable {
    private String codigo;
    private String nombre;
    private String especie;
    private String propietarioId;
    private int edad;
    private ArrayList<EventoClinico> historial;

        public MascotaDTO(String nombre, String especie, int edad, String codigo, String idPropietario) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.especie = especie;
        this.edad = edad;
        this.historial = new ArrayList<>();
        this.propietarioId = idPropietario;
    }

    // Getters

    public String getCodigo() { return codigo; }

    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getPropietarioId() {
        return propietarioId;
    }

    public void setPropietarioId(String propietarioId) {
        this.propietarioId = propietarioId;
    }

   public String getNombre() {
   return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public int getEdad() {
        return edad;
    }

    public ArrayList<EventoClinico> getHistorial() {
        return historial;
    }

    // Setters que hacían falta
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    //Metodo para agregar eventos al historial
    public void agregarEvento(EventoClinico evento) {
        historial.add(evento);
    }

    public void mostrarHistorial() {
        for (EventoClinico e : historial) {
            e.mostrarDetalle();
        }
    }

    @Override
    public String toString() {
        return nombre + " (" + especie + ")";
    }
}
