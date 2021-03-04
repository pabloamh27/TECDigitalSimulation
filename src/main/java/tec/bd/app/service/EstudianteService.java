package tec.bd.app.service;

import tec.bd.app.bd.SetDB;
import tec.bd.app.domain.Estudiante;

import java.util.List;
import java.util.stream.Collectors;

public class EstudianteService {

    private SetDB database;

    public EstudianteService(SetDB database) {
        this.database = database;
    }

    public List<Estudiante> getAll() {
        return this.database.getEstudianteTable()
                .stream()
                .collect(Collectors.toList());
    }

    public Estudiante getById(long carne) {
        return this.database.getEstudianteTable().stream().filter(e -> e.getCarne() == carne).findFirst().get();
    }

    public void addNew(Estudiante e) {
        // Verificar si el estudiante ya existe en la Base de datos
        for (Estudiante estudiante : this.database.getEstudianteTable()) {
            if (estudiante.getCarne() == e.getCarne()) {
                System.out.println("\nEstudiante registrado!");
                return;
            }
        }
        this.database.getEstudianteTable().add(e);
    }

    public void updateStudent(Estudiante e) {
        // Implementar codigo de actualizacion
        // Traer el estudiante con carne X
        // modificar el estudiante con los datos del estudiante e
        for (Estudiante estudiante : this.database.getEstudianteTable()) {
            if (estudiante.getCarne() == e.getCarne()) {
                estudiante.setNombre(e.getNombre());
                estudiante.setApellido(e.getApellido());
                estudiante.setEdad(e.getEdad());
                return;
            }
        }
        System.out.println("\nNo se encontraron coincidencias en la base de datos");
    }

    public void deleteStudent(long carne) {
        // implementar codigo de borrado
        // HashSet.remove(e);
        for (Estudiante estudiante : this.database.getEstudianteTable()) {
            if (estudiante.getCarne() == carne) {
                this.database.getEstudianteTable().remove(estudiante);
                return;
            }
        }
        System.out.println("\nNo se encontraron coincidencias en la base de datos");

    }
}
