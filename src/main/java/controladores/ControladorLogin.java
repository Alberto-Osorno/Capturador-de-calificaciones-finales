package controladores;

import basultosorno.Login;
import basultosorno.Usuario;
import basultosorno.ManipuladorCSV;
import basultosorno.VistasAplicacion;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class ControladorLogin {
    @FXML
    private  TextField usuario;
    @FXML
    private PasswordField contrasenia;

    private final ManipuladorCSV archivoCSV = new ManipuladorCSV();
    private ArrayList<Usuario> usuarios;
    private final Usuario usuarioLogueado = new Usuario();

    @FXML
    private void initialize() {
        usuarios = archivoCSV.leerUsuarios(); //Se lee el archivo de usuarios y se guarda en la lista
    }

    @FXML
    private void iniciarSesion(){
        //Valor de las etiquetas
        String usuarioIngresado = usuario.getText();
        String contraseniaIngresada = contrasenia.getText();

        //Verificar el inicio de sesión
        boolean inicioCorrecto = Login.iniciarSesion(usuarios, usuarioLogueado, usuarioIngresado, contraseniaIngresada);


        if (!inicioCorrecto) {
            VistasAplicacion.alert(Alert.AlertType.ERROR, "Error", "Usuario o contraseña incorrectos:\n");
            usuario.clear(); //Se limpia campo usuario
            contrasenia.clear(); //Se limpia campo contraseña
            return; //Se sale del método
        }

        try {
            // Cambia a Menú y obtiene su controller para pasarle el usuario
            ControladorMenu controladorMenu = VistasAplicacion.cambiarEscenaYObtenerController(usuario, "/views/MenuView.fxml");
            controladorMenu.setUsuarioLogueado(usuarioLogueado);

        } catch (IOException e) {
            VistasAplicacion.alert(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ventana de menú:\n" + e.getMessage());
        }
    }

    @FXML
    private void cerrarAplicacion() {
        Platform.exit(); //Se termina la ejecución del programa al cerrar todas las ventanas
    }

}
