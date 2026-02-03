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
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("Matricula"));
        colApellido1.setCellValueFactory(new PropertyValueFactory<>("Primer Apellido"));
        colApellido2.setCellValueFactory(new PropertyValueFactory<>("Segundo Apellido"));
        colNombres.setCellValueFactory(new PropertyValueFactory<>("Nombres"));
        colCalificacion.setCellValueFactory(new PropertyValueFactory<>("Calificación"));

        // Hacer editable la columna de calificación
        tablaEstudiantes.setEditable(true);
        colCalificacion.setEditable(true);
        colCalificacion.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        // Guardar el valor editado en el objeto Estudiante
        colCalificacion.setOnEditCommit(event -> {
            Estudiante est = event.getRowValue();
            Integer nueva = event.getNewValue();

            // Validación de contenido
            if (nueva == null || nueva < 0 || nueva > 100) {
                VistasAplicacion.alert(Alert.AlertType.WARNING, "Aviso", "Calificación inválida.");

                Integer anterior = event.getOldValue();
                est.setCalificacion(anterior == null ? 0 : anterior);
                return;
            }

            //Se actualiza el MISMO objeto en la lista compartida
            est.setCalificacion(nueva);
        });
    }
}
