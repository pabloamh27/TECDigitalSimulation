package tec.bd.app.domain;

import java.util.List;

public class Profesor implements Entity {

    private int carneProfesor;
    private String nombre;
    private String apellido;
    private String ciudad;
    private List<Curso> cursos;

    public Profesor(int id, String nombre, String apellido, String ciudad, List<Curso> cursos) {
        carneProfesor = carneProfesor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ciudad = ciudad;
        this.cursos = cursos;
    }

    public int getCarneProfesor() {
        return carneProfesor;
    }

    public void setCarneProfesor(int carneProfesor) {
        carneProfesor = carneProfesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}
