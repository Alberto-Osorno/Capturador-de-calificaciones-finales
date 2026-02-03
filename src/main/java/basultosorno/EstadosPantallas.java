package basultosorno;

import java.util.ArrayList;

public class EstadosPantallas {

    private Usuario usuarioLogueado;
    private ArrayList<Estudiante> estudiantes = new ArrayList<>();

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuario) {
        usuarioLogueado = usuario;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }
}
