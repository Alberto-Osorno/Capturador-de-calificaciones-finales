package basultosorno;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EstadosPantallas {

    private Usuario usuarioLogueado;
    private ObservableList<Estudiante> estudiantes = FXCollections.observableArrayList();
    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuario) {
        usuarioLogueado = usuario;
    }

    public ObservableList<Estudiante> getEstudiantes() {
        return estudiantes;
    }
}
