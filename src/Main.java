import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //Declaracion inicial de atributps
        Scanner scanner = new Scanner(System.in);
        ManipuladorCSV archivoCSV = new ManipuladorCSV();
        Usuario usuarioLogueado = new Usuario();

        ArrayList<Usuario> usuarios = archivoCSV.leerUsuarios(); //Lectura y guardado de los usuarios del archivo csv para el Login

        //Inicio del Login
        System.out.println("---Inicio de sesión---");
        boolean inicioSesion;
        do {
            inicioSesion = Login.iniciarSesion(usuarios, usuarioLogueado);
        }while (!inicioSesion);

        ArrayList<Estudiante> estudiantes = archivoCSV.leerEstudiantes(usuarioLogueado); //Lectura y guardado de los estudiantes del archivo csv

        //Inicio del bucle principal
        int op;
        do {
            System.out.println("\n---Menu---\n");
            System.out.println("Bienvenido " + usuarioLogueado.getUsuario() + " que deseas hacer:");
            System.out.println("1.- Capturar");
            System.out.println("2.- Generar documento CSV");
            System.out.println("3.- Generar documento PDF");
            System.out.println("4.- Salir");
            op = scanner.nextInt();

            switch (op){
                case 1:
                    capturarCalificaciones(estudiantes);
                    break;
                case 2:
                    ManipuladorCSV.generarArchivoCSV(estudiantes, archivoCSV);
                    break;
                case 3:
                    ManipuladorPDF.generarArchivoPDF(estudiantes);
                    break;
                case 4:
                    System.out.println("\nSaliendo...");
                    break;
                default:
                    System.out.println("\nOpción invalida");
            }

        } while(op != 4);
    }

    // Metodo para asignarle calificaciones a todos los alumnos guardados del archivo csv
    // Acepta calificaciones del 0 - 100, tomando el 0 como calificacion vacía
    public static void capturarCalificaciones(ArrayList<Estudiante> estudiantes){
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n---Capturar calificaciones---");
        for(Estudiante estudiante : estudiantes){
            int calificacion = 0;
            boolean calificacionValida;
            boolean calificacionEnRango;
            do {
                do {
                    System.out.println("Capturar calificación del alumno " + estudiante);
                    String aux = scanner.nextLine();
                    calificacionValida = true; //Se asume que la calificacion es válida
                    try {
                        calificacion = Integer.parseInt(aux); //Se intenta convertir el String leído a un Int
                    } catch (NumberFormatException e) {
                        System.out.println("\nCalificación no entera, intente de nuevo");
                        calificacionValida = false; //Si no se pudo convertir el String a un Int se actualiza el estado de la calificacion
                    }
                } while(!calificacionValida); //Se repite el proceso hasta que el usuario ingresa una calificacion entera

                if (calificacion >= 0 && calificacion <= 100){ //Se verifica que la calificacion esté en el rango 0 - 100
                    estudiante.setCalificacion(calificacion);
                    calificacionEnRango = true;
                } else {
                    System.out.println("\nCalificación invalida, intente de nuevo");
                    calificacionEnRango = false;
                }
            } while (!calificacionEnRango); //Se repite el proceso hasta que la calificacion sea válida y esté en el rango 0 - 100

        }
    }

}