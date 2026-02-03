package controladores;

import basultosorno.EstadosPantallas;
import basultosorno.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.EnumSet;

public class ControladorCapturarCalificaciones {

    @FXML
    private Label lblUsuario;

    private EstadosPantallas estadosPantallas;

    public void setEstadosPantallas(EstadosPantallas estadosPantallas) {
        this.estadosPantallas = estadosPantallas;
        lblUsuario.setText("Sesión: " + estadosPantallas.getUsuarioLogueado().getUsuario());
    }
}
