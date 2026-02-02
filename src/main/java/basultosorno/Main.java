package basultosorno;

import java.util.Scanner;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("Capturador de Calificaciones");
        stage.setScene(scene);
        stage.setResizable(false); //Para no ajustar la ventana
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
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