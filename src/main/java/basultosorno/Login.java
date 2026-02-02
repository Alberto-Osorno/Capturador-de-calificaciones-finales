package basultosorno;

import java.util.ArrayList;

public class Login {
    public static boolean iniciarSesion(ArrayList<Usuario> usuarios, Usuario usuarioLogueado, String nombreUsuario, String contraseniaIngresada){

        //Si no se escribio algo o el dato es nulo
        if (nombreUsuario == null || nombreUsuario.isBlank() || contraseniaIngresada == null || contraseniaIngresada.isBlank()) {
            return false;
        }

        //El objeto usuario econtrado nos sirve como booleano para verificar
        Usuario encontrado = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getUsuario().equals(nombreUsuario)) {
                encontrado = usuario;
                break;
            }
        }

        if (encontrado == null) {
            return false; // Usuario no encontrado
        }

        // Guardamos el usuario encontrado en el objeto logueado
        usuarioLogueado.setUsuario(encontrado.getUsuario());
        usuarioLogueado.setContrasenia(encontrado.getContrasenia());

        // Desencriptar y comparar
        String contraseniaDesencriptada = Encriptador.desencriptar(encontrado.getContrasenia());

        //Retorna true o false
        return contraseniaIngresada.equals(contraseniaDesencriptada);
    }
}