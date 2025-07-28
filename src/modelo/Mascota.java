package modelo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Mascota implements Serializable {
    private String nombre;
    private String especie;
    private int edad;
    private String codigo;

    private List<EventoClinico> historial = new ArrayList<>();
    public Mascota(String nombre, String especie, int edad) {
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.historial = new ArrayList<>();

        //setNombre(nombre);
        //setEspecie(especie);
        //setEdad(edad);


    //private ArrayList<Consulta> consultas;
    //private List<Vacuna> vacunas = new ArrayList<>();
    //private String nombreMascota;

        //private Historial historial; //delegar manejo de consultas a historial

        /* Código original:
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.consultas = new ArrayList<>();
        */
        //usar setters con validación y crear historial

      //  this.vacunas = new ArrayList<>(); //lista vacunas


    }

  /*  public void agregarConsulta(Consulta consulta) {
        // consultas.add(consulta); //original
        //delegar a historial
        historial.agregarConsulta(consulta);
    }
    public void agregarVacuna(Vacuna vacuna) {
        vacunas.add(vacuna);
    }*/

   /* public void mostrarVacunas() {
        if (vacunas.isEmpty()) {
            System.out.println("No hay vacunas registradas.");
        } else {
            System.out.println("Vacunas aplicadas a " + nombre + ":");
            for (Vacuna v : vacunas) {
                v.mostrarDetalle();
                System.out.println("--------------------------");
            }
        }
    }*/


    public void mostrarHistorial() {
        System.out.println("📋 Mascota: " + nombre + " | Especie: " + especie + " | Edad: " + edad + " años");
        if (historial.isEmpty()) {
            System.out.println("🔸 Sin eventos clínicos registrados.");
        } else {
            for (EventoClinico evento : historial) {
                evento.mostrarDetalle(); // polimórfico
                System.out.println("--------------------------");
            }
        }
    }
    /*public void mostrarHistorial() {
        System.out.println("📋 Mascota: " + nombre + " | Especie: " + especie + " | Edad: " + edad + " años");
        System.out.println("Historial de consultas:");
        //metodo de historial
        historial.mostrarConsultas();
    }

     Código original:
    if (consultas.isEmpty()) {
        System.out.println("Sin consultas registradas.");
    } else {
        for (Consulta c : consultas) {
            c.mostrarConsulta();
            System.out.println("--------------------------");
        }
    }
    */
    //Getters y setters con validación
    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String toLineaArchivo() {
        return nombre + "," + especie + "," + edad;
    }

    public static Mascota desdeLineaArchivo(String linea) {
        String[] partes = linea.split(",");
        if (partes.length != 3) return null;
        try {
            int edad = Integer.parseInt(partes[2]);
            return new Mascota(partes[0], partes[1], edad);
        } catch (NumberFormatException e) {
            return null;
        }
    }


    public void agregarEvento(EventoClinico evento) {
        historial.add(evento);
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre inválido.");
        }
        this.nombre = nombre;
    }
    public List<EventoClinico> getHistorial() {
        return historial;
    }

    public String getEspecie() {
        return especie;
    }

    public List<Vacuna> getVacunaciones() {
        List<Vacuna> vacunaciones = new ArrayList<>();
        for (EventoClinico evento : historial) {
            if (evento instanceof Vacuna) {
                vacunaciones.add((Vacuna) evento);
            }
        }
        return vacunaciones;
    }
    public void setEspecie(String especie) {
        if (especie == null || especie.isBlank()) {
            throw new IllegalArgumentException("Especie inválida.");
        }
        this.especie = especie;
    }

    public List<Consulta> getConsultas() {
        List<Consulta> consultas = new ArrayList<>();
        for (EventoClinico evento : historial) {
            if (evento instanceof Consulta) {
                consultas.add((Consulta) evento);
            }
        }
        return consultas;
    }

    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        if (edad < 0) {
            throw new IllegalArgumentException("Edad no puede ser negativa.");
        }
        this.edad = edad;
    }

   /* public List<Vacuna> getVacunas() {
        return vacunas;
    }*/
    @Override
    public String toString() {
        return nombre + " (" + especie + ")";
    }
}
