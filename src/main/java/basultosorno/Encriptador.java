package basultosorno;

public class Encriptador {

    //Metodo para encriptar contraseñas con un cifrado César recorriendo cada carácter 5 lugares a la derecha
    public static String encriptar(String password){
        StringBuilder passwordEncriptada = new StringBuilder();

        for(char letra : password.toCharArray()){
            passwordEncriptada.append((char)(letra+5));
        }

        return passwordEncriptada.toString();
    }

    //Metodo para desencriptar contraseñas con un cifrado César recorriendo cada carácter 5 lugares a la izquierda
    public static String desencriptar(String password){
        StringBuilder passwordDesencriptada = new StringBuilder();

        for(char letra : password.toCharArray()){
            passwordDesencriptada.append((char)(letra-5));
        }

        return passwordDesencriptada.toString();
    }

}