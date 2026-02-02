package controladores;

import basultosorno.Usuario;
import basultosorno.VistasAplicacion;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.io.IOException;

public class ControladorMenu {

    @FXML
    private Label lblUsuario;

    private Usuario usuarioLogueado;

    // Este método se llama desde el Login después de cargar Menu.fxml
    public void setUsuarioLogueado(Usuario usuario) {
        usuarioLogueado = usuario;
        lblUsuario.setText("Usuario: " + usuario.getUsuario());
    }

    @FXML
    private void capturarCalificaciones() {
        try {
            // Cambia a CapturarCalificaciones y obtiene su controller para pasarle el usuario
            ControladorCapturarCalificaciones capturaController = VistasAplicacion.cambiarEscenaYObtenerController(lblUsuario, "/views/CapturarCalificacionesView.fxml");
            capturaController.setUsuarioLogueado(usuarioLogueado);

        } catch (IOException e) {
            VistasAplicacion.alert(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ventana de captura:\n" + e.getMessage());
        }
    }

    @FXML
    private void generarCSV() {
        try {
            // TODO: aquí llamas tu lógica real
            // Ejemplo: CSV.generar(...);

            VistasAplicacion.alert(Alert.AlertType.INFORMATION, "Listo", "CSV generado correctamente.");
        } catch (Exception e) {
            VistasAplicacion.alert(Alert.AlertType.ERROR, "Error", "No se pudo generar el CSV:\n" + e.getMessage());
        }
    }

    @FXML
    private void generarPDF() {
        try {
            // TODO: aquí llamas tu lógica real
            // Ejemplo: PDF.generar(...);

            VistasAplicacion.alert(Alert.AlertType.INFORMATION, "Listo", "PDF generado correctamente.");
        } catch (Exception e) {
            VistasAplicacion.alert(Alert.AlertType.ERROR, "Error", "No se pudo generar el PDF:\n" + e.getMessage());
        }
    }

    @FXML
    private void cerrarSesion() {
        try {
            VistasAplicacion.cambiarEscena(lblUsuario, "/views/LoginView.fxml");
        } catch (IOException e) {
            VistasAplicacion.alert(Alert.AlertType.ERROR, "Error", "No se pudo regresar al login:\n" + e.getMessage());
        }
    }
}

