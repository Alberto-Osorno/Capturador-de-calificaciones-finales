package controladores;

import basultosorno.Login;
import basultosorno.Usuario;
import basultosorno.ManipuladorCSV;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class ControladorLogin {
    @FXML
    private  TextField usuario;
    @FXML
    private PasswordField contrasenia;
    @FXML
    private Label lblMensaje;

    private final ManipuladorCSV archivoCSV = new ManipuladorCSV();
    private ArrayList<Usuario> usuarios;
    private final Usuario usuarioLogueado = new Usuario();

    @FXML
    private void initialize() {
        lblMensaje.setText(""); //Mensaje en blanco
        usuarios = archivoCSV.leerUsuarios(); //Se lee el archivo de usuarios y se guarda en la lista
    }

    @FXML
    private void iniciarSesion(){
        //Valor de las etiquetas
        String usuarioIngresado = usuario.getText();
        String contraseniaIngresada = contrasenia.getText();

        //Verificar el inicio de sesión
        boolean inicioCorrecto = Login.iniciarSesion(usuarios, usuarioLogueado, usuarioIngresado, contraseniaIngresada);

        if(inicioCorrecto){
            // TODO: cambiar a otra vista si quieres (menú, principal, etc.)
        }else{
            lblMensaje.setText("Usuario o contraseña incorrectos");
            usuario.clear(); //Se limpia el campo de usuario
            contrasenia.clear(); //Se limpia el campo de contrasenia
        }
    }

    @FXML
    private void cerrarAplicacion() {
        Platform.exit(); //Se termina la ejecución del programa al cerrar todas las ventanas
    }

}
