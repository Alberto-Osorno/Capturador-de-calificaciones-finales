import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ManipuladorCSV {

    public ArrayList<Estudiante> leerEstudiantes(Usuario usuarioLogueado){
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        boolean bandera;

        do {
            System.out.println("\nUsuario: " + usuarioLogueado.getUsuario());
            System.out.println("Ingrese la ruta del archivo CSV de Estudiantes");
            String ruta = scanner.nextLine();

            try (BufferedReader lector = new BufferedReader(new FileReader(ruta))) {
                if (ruta.endsWith("csv")){
                    bandera = true;
                    String linea;
                    lector.readLine();

                    while ((linea = lector.readLine()) != null) {
                        String[] palabras = linea.split(",");
                        Estudiante estudiante = new Estudiante(palabras[0], palabras[1], palabras[2], palabras[3]);
                        estudiantes.add(estudiante);
                    }
                } else {
                    bandera = false;
                    System.out.println("La ruta ingresada no es de un archivo CSV, intente de nuevo\n");
                }
            } catch (Exception e) {
                bandera = false;
                System.out.println("Hubo un problema con la ruta ingresada, intente de nuevo\n");
            }

        }while (!bandera);

        return estudiantes;
    }

    public ArrayList<Usuario> leerUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(new FileReader("./Resources/usuarios.csv"))){
            String linea;
            lector.readLine();

            while((linea = lector.readLine()) != null){
                String[] palabras = linea.split(",");
                Usuario usuario = new Usuario(palabras[0], palabras[1]);
                usuarios.add(usuario);
            }

        }catch (Exception e){
            System.out.println("No existe el archivo \"usuarios.csv\" en la carpeta \"Resources\"\n");
        }

        return usuarios;
    }

    public void escribirArchivo(ArrayList<Estudiante> estudiantes){
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("./Resources/Calificaciones_DS_2026.csv"))){
            escritor.write("Matricula,Asignatura,Calificación");
            escritor.newLine();

            for (Estudiante estudiante : estudiantes) {
                escritor.write(estudiante.getMatricula() + ",Diseño de Software," + estudiante.getCalificacion());
                escritor.newLine();
            }

        }catch (Exception e){
            System.out.println("Error al escribir el archivo");
        }
    }

    public static void generarArchivoCSV(ArrayList<Estudiante> estudiantes, ManipuladorCSV archivo){
        System.out.println("\n---Generar archivo---");

        boolean estudianteSinCalificacion = false;
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCalificacion() == 0) {
                estudianteSinCalificacion = true;
                break;
            }
        }

        if(!estudianteSinCalificacion){
            System.out.println("\nGenerando...");
            archivo.escribirArchivo(estudiantes);
        } else{
            System.out.println("\nAl menos un alumno no tiene calificación. No se puede generar el archivo");
        }
    }

}