package src;

import vista.VentanaPrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}

/*import java.util.Scanner;

        public class Main {
            public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);

              /*  // === Registro del Modelo.Propietario ===
                System.out.println("=== Nombre del Modelo.Propietario ===");
                System.out.print("Nombre: ");
                String nombreProp = sc.nextLine();
                System.out.print("Documento: ");
                String documento = sc.nextLine();
                System.out.print("Teléfono: ");
                String telefono = sc.nextLine();
                Modelo.Propietario propietario = new Modelo.Propietario(nombreProp, documento, telefono);

                // === Registro de Mascotas ===
                System.out.print("\n¿Cuántas mascotas desea registrar?: ");
                int numMascotas = Integer.parseInt(sc.nextLine());

                for (int i = 1; i <= numMascotas; i++) {
                    System.out.println("\n--- Modelo.MascotaDTO #" + i + " ---");
                    System.out.print("Nombre de la mascota: ");
                    String nombreMascota = sc.nextLine();
                    System.out.print("Especie: ");
                    String especie = sc.nextLine();
                    System.out.print("Edad: ");
                    int edad = Integer.parseInt(sc.nextLine());

                    // Código automático usando Util.IDGenerator
                    String codigoMascota = Util.IDGenerator.generarCodigoMascota(); // no almacenado en clase
                    Modelo.MascotaDTO mascota = new Modelo.MascotaDTO(nombreMascota, especie, edad);
                    propietario.agregarMascota(mascota);

                    // === Registro de Consultas ===
                    System.out.print("¿Cuántas consultas desea agregar a esta mascota?: ");
                    int numConsultas = Integer.parseInt(sc.nextLine());

                    for (int j = 1; j <= numConsultas; j++) {
                        System.out.println("-> Modelo.ConsultaVeterinaria #" + j);
                        String codigo = Util.IDGenerator.generarCodigoConsulta();
                        System.out.print("Fecha (YYYY-MM-DD): ");
                        String fecha = sc.nextLine();
                        System.out.println("Información del veterinario:");
                        System.out.print("Nombre: ");
                        String nombreVet = sc.nextLine();
                        System.out.print("Especialidad: ");
                        String especialidad = sc.nextLine();
                        Modelo.ConsultaVeterinaria consulta = new Modelo.ConsultaVeterinaria(codigo, fecha, new Modelo.Veterinario(nombreVet, especialidad));
                        mascota.agregarConsulta(consulta);
                    }
                }

               */

                /* === MENÚ CRUD DE MASCOTAS ===
                System.out.println("\n¿Desea abrir el CRUD del veterinario? (s/n): ");
                String opcionCrud = sc.nextLine();
                if (opcionCrud.equalsIgnoreCase("s")) {
                    dao.CrudMascotas crud = new dao.CrudMascotas();
                    crud.menuCrud(); // Abre el menú CRUD del veterinario
                }

                sc.close();
            }
        }
*/