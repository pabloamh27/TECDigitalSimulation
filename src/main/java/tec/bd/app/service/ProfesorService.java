package tec.bd.app.service;

import tec.bd.app.dao.ProfesorDAO;
import tec.bd.app.domain.Curso;
import tec.bd.app.domain.Profesor;

import java.util.List;
import java.util.Optional;

public interface ProfesorService {

    List<Profesor> getAll();

    Optional<Profesor> getById(Integer id);

    void addNew(Profesor p);

    Optional<Profesor> updateProfessor(Profesor p);

    void deleteProfessor(Integer id);

    List<Profesor> getProfessorsByCity(String city);
}
