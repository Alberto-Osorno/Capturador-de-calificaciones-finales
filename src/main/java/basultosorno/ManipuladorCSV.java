package basultosorno;

import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ManipuladorCSV {

    public ArrayList<Estudiante> leerEstudiantes(){
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
         //Lista de Estudiantes
//        Scanner scanner = new Scanner(System.in);

//        boolean bandera; //Bandera para determinar el estado de la lectura

//        do {
//            System.out.println("\nUsuario: " + usuarioLogueado.getUsuario());
//            System.out.println("Ingrese la ruta del archivo CSV de Estudiantes");
//            String ruta = scanner.nextLine();

            //Instancia de BufferedReader que usa FileReader que recibe como argumento la ruta del archivo csv
            try (BufferedReader lector = new BufferedReader(new FileReader("./src/main/resources/listaDS.csv"))) {
                //Sí la ruta termina con "csv" (para saber si es un archivo csv)
                //if (ruta.endsWith("csv")){
//                    bandera = true;
                    String linea; // String que almacena la línea actual del archivo
                    lector.readLine(); // Se consuma la primera línea del archivo (encabezado)

                    //Mientras haya contenido para leer
                    while ((linea = lector.readLine()) != null) {
                        String[] palabras = linea.split(","); //Arreglo que almacena las palabras separadas por ',' en la línea
                        //Instancia de Estudiante a la que se le setean los valores correspondientes al archivo
                        Estudiante estudiante = new Estudiante(palabras[0], palabras[1], palabras[2], palabras[3]);
                        //Se agrega la instancia a la lista de estudiantes
                        estudiantes.add(estudiante);
                    }
//                } else {
//                    //En caso de que no sea un archivo csv
//                    bandera = false;
//                    System.out.println("La ruta ingresada no es de un archivo CSV, intente de nuevo\n");
//                }
            } catch (Exception e) {
                //En caso de que ocurra una excepción al abrir el archivo
//                bandera = false;
                System.out.println("Hubo un problema con la ruta ingresada, intente de nuevo\n");
            }

//        }while (!bandera); //Mientras la bandera sea false se repite el proceso

        return estudiantes; //Se duvuelve la lista de estudiantes
    }

    public ArrayList<Usuario> leerUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<>(); //Lista de Usuarios

        //Instancia de BufferedReader lector que a su vez usa FileReader y recibe como parámetro el archivo de usuarios.csv
        try (BufferedReader lector = new BufferedReader(new FileReader("./src/main/resources/usuarios.csv"))){
            String linea;
            lector.readLine();

            while((linea = lector.readLine()) != null){
                String[] palabras = linea.split(",");
                //Instancia de Usuario a la que se le setean los valores correspondientes al archivo
                Usuario usuario = new Usuario(palabras[0], palabras[1]);
                //Se agrega la instancia a la lista de usuarios
                usuarios.add(usuario);
            }

        }catch (Exception e){
            //En caso de haber una excepción al abrir el archivo se muestra
            System.out.println("No existe el archivo \"usuarios.csv\" en la carpeta \"resources\"\n");
        }

        return usuarios; //Se devuelve la lista de usuarios
    }

    public void escribirArchivo(ArrayList<Estudiante> estudiantes){
        //Instancia de BufferedWritter que usa FileWriter y como parámetro la ruta del archivo
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("./src/main/resources/Calificaciones_DS_2026.csv"))){
            escritor.write("Matricula,Asignatura,Calificación"); //Se escribe el encabezado del archivo csv
            escritor.newLine(); //Salto de línea

            //Se recorre la lista de estudiantes
            for (Estudiante estudiante : estudiantes) {
                //Se escribe la información correspondiente
                escritor.write(estudiante.getMatricula() + ",Diseño de Software," + estudiante.getCalificacion());
                escritor.newLine();// Salto de línea
            }

        }catch (Exception e){
            //En caso de haber un error al escribir el archivo
            System.out.println("Error al escribir el archivo");
        }
    }

    public static boolean generarArchivoCSV(ArrayList<Estudiante> estudiantes, ManipuladorCSV archivo){

        boolean estudianteSinCalificacion = false; //Booleano para determinar el estado para crear el archivo csv
        for (Estudiante estudiante : estudiantes) { //Se recorre la lista de estudiantes
            if (estudiante.getCalificacion() == 0) { //Si la calificación es 0
                estudianteSinCalificacion = true; //Hay un alumno sin calificación
                break; //Se termina el ciclo, ya no tenemos que seguir buscando
            }
        }

        if(!estudianteSinCalificacion){ //Sí todos tienen calificación
            archivo.escribirArchivo(estudiantes); //Se manda a llamar a la función escribirArchivo() y se le pasa la lista de estudiantes
            return true;
        } else{
            //Si alguno no tiene calificación
            VistasAplicacion.alert(Alert.AlertType.ERROR, "Error", "Al menos un alumno no tiene calificación");
            return false;
        }
    }

}