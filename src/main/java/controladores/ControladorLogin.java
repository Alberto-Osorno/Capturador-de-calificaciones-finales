package controladores;

import basultosorno.*;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class ControladorLogin {
    @FXML private  TextField usuario;
    @FXML private PasswordField contrasenia;

    private ManipuladorCSV archivoCSV = new ManipuladorCSV();
    private ArrayList<Usuario> usuarios;
    private Usuario usuarioLogueado = new Usuario();

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
            EstadosPantallas estadosPantallas = new EstadosPantallas();
            estadosPantallas.setUsuarioLogueado(usuarioLogueado); //Se setea el usuariologeado

            //modificar: quitar usuarioLogeado como argumento
            estadosPantallas.getEstudiantes().addAll(archivoCSV.leerEstudiantes()); //Carga la lista de estudiantes

            ControladorMenu controladorMenu = VistasAplicacion.cambiarEscenaYObtenerController(usuario,"/views/MenuView.fxml");
            controladorMenu.setEstadosPantallas(estadosPantallas);

        } catch (IOException e) {
            VistasAplicacion.alert(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ventana de menú:\n" + e.getMessage());
        }
    }

    @FXML
    private void cerrarAplicacion() {
        Platform.exit(); //Se termina la ejecución del programa al cerrar todas las ventanas
    }

}
