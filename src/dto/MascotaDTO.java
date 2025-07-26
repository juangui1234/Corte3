package src.dto;

import modelo.EventoClinico;

import java.util.ArrayList;

public class MascotaDTO {
    private String nombre;
    private String especie;
    private int edad;
    private ArrayList<EventoClinico> historial;

    public MascotaDTO(String nombre, String especie, int edad) {
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.historial = new ArrayList<>();
    }

    // Getters
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

    // Método para agregar eventos al historial
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
