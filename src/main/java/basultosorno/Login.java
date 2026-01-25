package basultosorno;

import java.util.ArrayList;
import java.util.Scanner;

public class Login {
    public static boolean iniciarSesion(ArrayList<Usuario> usuarios, Usuario usuarioLogueado){
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nIngrese usuario: ");
        String nombreUsuario = scanner.nextLine();

        boolean resultadoUsuario = false; //Booleano para determinar el estado del inincio de sesión
        for(Usuario usuario : usuarios){ //Se recorre la lista de usuarios
            if(usuario.getUsuario().equals(nombreUsuario)){ //Verificar que el usuario ingresado sea igual a alguno del archivo csv
                System.out.println("Usuario encontrado");
                //Seteamos los datos para la instancia logeada
                usuarioLogueado.setUsuario(usuario.getUsuario());
                usuarioLogueado.setContrasenia(usuario.getContrasenia());
                resultadoUsuario = true; //En caso de que se econtró al usuario booleano igual a true
                break; //Terminamos el ciclo, ya no tenemos que seguir buscando usuarios
            }
        }

        if(resultadoUsuario){ //Sí se econtró el usuario
            int intentos = 3; //Contador de intentos para ingresar la contraseña
            do {
                System.out.println("\nIngrese contraseña: ");
                String contrasenia = scanner.nextLine();
                //Variable que almacena la contraseña desencriptada usando el método desencriptar() de la clase Ecriptador
                String contraseniaDesencriptada = Encriptador.desencriptar(usuarioLogueado.getContrasenia());

                if(contrasenia.equals(contraseniaDesencriptada)){ //Verificar que la contraseña desencriptada sea igual a la ingresada
                    System.out.println("Inicio de sesión exitoso!");
                    intentos = 0; //Si las contraseñas son iguales seteamos el contador de intentos a 0 para terminar la petición
                }else{
                    System.out.println("Contraseña incorrecta");
                    --intentos; //Decrementamos en 1 de forma prefija al contador de intentos
                    if(intentos == 0){
                        //Si falló 3 veces entonces se muestra un mensaje y el booleano se setea en false indicando que no hubo inicio de sesión
                        System.out.println("No quedan mas intentos...");
                        resultadoUsuario = false;
                    }
                }
            }while (intentos != 0); // Pedir la contraseña se repite mientras no se haya acabado los 3 intentos

        }else{
            //Si no se econtró el usario se muestra el siguiente mensaje y el booleana no se cambia porque se quedó en false
            System.out.println("Usuario no encontrado");
        }

        return resultadoUsuario; //Devolvemos el booleano representando el estado del inicio de sesión
    }
}