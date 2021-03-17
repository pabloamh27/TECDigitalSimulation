package tec.bd.app.service;

import tec.bd.app.domain.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    List<Curso> getAllCurses();

    Optional<Curso> getCurseById(int Id);

    void addNewCurse(Curso c);

    Optional<Curso> updateCurse(Curso c);

    void deleteCurse(int id);

    List<Curso> getByDepartment(String department);


}
