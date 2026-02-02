package controladores;

import basultosorno.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControladorCapturarCalificaciones {

    @FXML
    private Label lblUsuario;

    private Usuario usuarioLogueado;

    public void setUsuarioLogueado(Usuario usuario) {
        usuarioLogueado = usuario;
        lblUsuario.setText("Usuario: " + usuario.getUsuario());
    }
}
