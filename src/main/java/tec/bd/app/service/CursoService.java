package tec.bd.app.service;

import tec.bd.app.domain.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    List<Curso> getAll();

    Optional<Curso> getById(int id);

    void addNew(Curso c);

    Optional<Curso> updateCourse(Curso c);

    void deleteCourse(int id);

    List<Curso> getByDepartment(String department);


}
