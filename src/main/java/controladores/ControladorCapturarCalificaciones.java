package controladores;

import basultosorno.EstadosPantallas;
import basultosorno.Estudiante;
import basultosorno.VistasAplicacion;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;

public class ControladorCapturarCalificaciones {
    @FXML private TableView<Estudiante> tablaEstudiantes;
    @FXML private TableColumn<Estudiante, String> colMatricula;
    @FXML private TableColumn<Estudiante, String> colApellido1;
    @FXML private TableColumn<Estudiante, String> colApellido2;
    @FXML private TableColumn<Estudiante, String> colNombres;
    @FXML private TableColumn<Estudiante, Integer> colCalificacion;
    @FXML private Label lblUsuario;

    private EstadosPantallas estadosPantallas;

    public void setEstadosPantallas(EstadosPantallas estadosPantallas) {
        this.estadosPantallas = estadosPantallas;
        lblUsuario.setText("Sesión: " + estadosPantallas.getUsuarioLogueado().getUsuario());
        tablaEstudiantes.setItems(estadosPantallas.getEstudiantes());
    }

    @FXML
    public void initialize() {
        // Enlazar columnas con propiedades del modelo
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colApellido1.setCellValueFactory(new PropertyValueFactory<>("primerApellido"));
        colApellido2.setCellValueFactory(new PropertyValueFactory<>("segundoApellido"));
        colNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        colCalificacion.setCellValueFactory(new PropertyValueFactory<>("calificacion"));

        // Hacer editable la columna de calificación
        tablaEstudiantes.setEditable(true);
        colCalificacion.setEditable(true);
        colCalificacion.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        // Guardar el valor editado en el objeto Estudiante
        colCalificacion.setOnEditCommit(event -> {
            Estudiante est = event.getRowValue();
            Integer nueva = event.getNewValue();

            if (nueva == null || nueva < 0 || nueva > 100) {
                VistasAplicacion.alert(Alert.AlertType.WARNING, "Aviso", "Calificación inválida (0 a 100).");
                tablaEstudiantes.refresh();
                return;
            }

            est.setCalificacion(nueva);
        });
    }

    @FXML
    private void volverAlMenu() {
        try {
            VistasAplicacion.cambiarEscenaYObtenerController(lblUsuario, "/views/MenuView.fxml");
        } catch (IOException e) {
            VistasAplicacion.alert(Alert.AlertType.ERROR, "Error", "No se pudo regresar al menu:\n" + e.getMessage());
        }
    }
}
