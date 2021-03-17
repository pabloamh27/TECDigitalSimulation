package tec.bd.app.domain;

public class Estudiante implements Entity {

    private int carne;
    private String nombre;
    private String apellido;
    private int edad;

    public Estudiante(int carne, String nombre, String apellido, int edad) {
        this.carne = carne;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public int getCarne() {
        return carne;
    }

    public void setCarne(int carne) {
        this.carne = carne;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
