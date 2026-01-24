package basultosorno;

public class Usuario {
    private String usuario;
    private String contrasenia;

    //Constructores
    public Usuario(){
    }

    public Usuario(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    //Setters y Getters
    public String getUsuario() {
        return usuario;
    }
    public String getContrasenia() {
        return contrasenia;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

}