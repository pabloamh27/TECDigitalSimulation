package tec.bd.app.service;

import tec.bd.app.domain.Estudiante;
import tec.bd.app.domain.Profesor;

import java.util.List;
import java.util.Optional;

public interface ProfesorService {
    List<Profesor> getAll();

    Optional<Profesor> getById(int Id);

    void addNew(Profesor p);

    Optional<Profesor> updateProfessor(Profesor p);

    void deleteProfessor(int id);

    //nuevo metodo para ver los profesores por ciudad

    List<Profesor> getProfessorsByCity(String city);

}
