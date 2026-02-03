package controladores;

import basultosorno.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class ControladorMenu {
    @FXML private Label lblUsuario;

    private ManipuladorCSV archivo = new ManipuladorCSV();
    private EstadosPantallas estadosPantallas;

    public void setEstadosPantallas(EstadosPantallas estadosPantallas) {
        this.estadosPantallas = estadosPantallas;
        lblUsuario.setText("Sesión: " + estadosPantallas.getUsuarioLogueado().getUsuario());
    }

    @FXML
    private void capturarCalificaciones() {
        try {
            ControladorCapturarCalificaciones c = VistasAplicacion.cambiarEscenaYObtenerController(lblUsuario, "/views/CapturarCalificacionesView.fxml");
            c.setEstadosPantallas(estadosPantallas);
        } catch (IOException e) {
            VistasAplicacion.alert(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ventana de captura:\n" + e.getMessage());
        }
    }


    @FXML
    private void generarCSV() {
        try {
            boolean CSVGenerado = ManipuladorCSV.generarArchivoCSV(estadosPantallas.getEstudiantes(), archivo);
            if (CSVGenerado){
                VistasAplicacion.alert(Alert.AlertType.INFORMATION, "Listo", "CSV generado correctamente.");
            }
        } catch (Exception e) {
            VistasAplicacion.alert(Alert.AlertType.ERROR, "Error", "No se pudo generar el CSV:\n" + e.getMessage());
        }
    }

    @FXML
    private void generarPDF() {
        try {
            ManipuladorPDF.generarArchivoPDF(estadosPantallas.getEstudiantes());
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

