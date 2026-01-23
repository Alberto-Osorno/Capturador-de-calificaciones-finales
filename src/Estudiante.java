public class Estudiante {
    private String primerApellido;
    private String segundoApellido;
    private String nombres;
    private String matricula;
    private int calificacion;

    //Constructor
    public Estudiante(String matricula, String primerApellido, String segundoApellido, String nombres) {
        this.matricula = matricula;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.nombres = nombres;
        this.calificacion = 0;
    }

    //Override del metodo para imprimir el contenido de la clase
    @Override
    public String toString() {
        return primerApellido + " " + segundoApellido+ " " + nombres + " con matricula: " + matricula;
    }

    //Setters y Getters
    public String getPrimerApellido() {
        return primerApellido;
    }
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getCalificacion() {
        return calificacion;
    }
    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

}