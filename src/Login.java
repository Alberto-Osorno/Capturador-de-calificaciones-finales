import java.util.ArrayList;
import java.util.Scanner;

public class Login {
    public static boolean iniciarSesion(ArrayList<Usuario> usuarios, Usuario usuarioLogueado){
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nIngrese usuario: ");
        String nombreUsuario = scanner.nextLine();

        boolean resultadoUsuario = false;
        for(Usuario usuario : usuarios){
            if(usuario.getUsuario().equals(nombreUsuario)){
                System.out.println("Usuario encontrado");
                usuarioLogueado.setUsuario(usuario.getUsuario());
                usuarioLogueado.setContrasenia(usuario.getContrasenia());
                resultadoUsuario = true;
                break;
            }
        }

        if(resultadoUsuario){
            int intentos = 3;
            do {
                System.out.println("\nIngrese contraseña: ");
                String contrasenia = scanner.nextLine();
                String contraseniaDesencriptada = Encriptador.desencriptar(usuarioLogueado.getContrasenia());

                if(contrasenia.equals(contraseniaDesencriptada)){
                    System.out.println("Inicio de sesión exitoso!");
                    intentos = 0;
                }else{
                    System.out.println("Contraseña incorrecta");
                    --intentos;
                    if(intentos == 0){
                        System.out.println("No quedan mas intentos...");
                        resultadoUsuario = false;
                    }
                }
            }while (intentos != 0);

        }else{
            System.out.println("Usuario no encontrado");
        }

        return resultadoUsuario;
    }
}