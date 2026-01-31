package controladores;

import basultosorno.Usuario;
import basultosorno.VistasAplicacion;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.io.IOException;

public class ControladorMenu {

    @FXML private Label lblUsuario;

    private Usuario usuarioLogueado;

    // Este método se llama desde el Login después de cargar Menu.fxml
    public void setUsuarioLogueado(Usuario usuario) {
        usuarioLogueado = usuario;
        lblUsuario.setText("Usuario: " + usuario.getUsuario());
    }

    @FXML
    private void abrirCapturarCalificaciones() {
        try {
            // Cambia a CapturarCalificaciones y obtiene su controller para pasarle el usuario
            ControladorCapturarCalificaciones capturaController = VistasAplicacion.cambiarEscenaYObtenerController(lblUsuario, "/views/CapturarCalificacionesView.fxml");
            capturaController.setUsuarioLogueado(usuarioLogueado);

        } catch (IOException e) {
            alert(Alert.AlertType.ERROR, "No se pudo abrir la ventana de captura:\n" + e.getMessage());
        }
    }

    @FXML
    private void generarCSV() {
        try {
            // TODO: aquí llamas tu lógica real
            // Ejemplo: CSV.generar(...);

            alert(Alert.AlertType.INFORMATION, "CSV generado correctamente.");
        } catch (Exception e) {
            alert(Alert.AlertType.ERROR, "No se pudo generar el CSV:\n" + e.getMessage());
        }
    }

    @FXML
    private void generarPDF() {
        try {
            // TODO: aquí llamas tu lógica real
            // Ejemplo: PDF.generar(...);

            alert(Alert.AlertType.INFORMATION, "PDF generado correctamente.");
        } catch (Exception e) {
            alert(Alert.AlertType.ERROR, "No se pudo generar el PDF:\n" + e.getMessage());
        }
    }

    @FXML
    private void cerrarSesion() {
        // (Opcional) confirmar antes de cerrar sesión
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmación");
        confirm.setHeaderText(null);
        confirm.setContentText("¿Deseas cerrar sesión?");
        ButtonType r = confirm.showAndWait().orElse(ButtonType.CANCEL);

        if (r != ButtonType.OK) return;

        try {
            VistasAplicacion.cambiarEscena(lblUsuario, "/views/LoginView.fxml");
        } catch (IOException e) {
            alert(Alert.AlertType.ERROR, "No se pudo regresar al login:\n" + e.getMessage());
        }
    }

    private void alert(Alert.AlertType tipo, String msg) {
        Alert a = new Alert(tipo);
        a.setTitle("Información");
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}

