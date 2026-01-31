package basultosorno;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class VistasAplicacion {
    //Método que únicamente cambia la vista de la ventan actual
    public static void cambiarEscena(Node nodoActual, String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(VistasAplicacion.class.getResource(fxml));
        Parent root = loader.load();
        Stage stage = (Stage) nodoActual.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /*
    Método que cambia la vista en la ventana actual y devuelve el controlador de la nueva vista para poder comunicarse
    con ella y pasarle el usuarioLogeado y mostrarlo
     */
    //Usa generics para devolver cualquier tipo de controlador esperado
    public static <T> T cambiarEscenaYObtenerController(Node nodoActual, String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(VistasAplicacion.class.getResource(fxml));
        Parent root = loader.load();
        Stage stage = (Stage) nodoActual.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        return loader.getController();
    }

    //Método que muestra alertas (para generarCSV y generarPDF)
    public static void alert(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert a = new Alert(tipo);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(mensaje);
        a.showAndWait();
    }
}
