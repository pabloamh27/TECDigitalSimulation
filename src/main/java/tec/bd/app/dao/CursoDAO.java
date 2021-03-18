package tec.bd.app.dao;

import tec.bd.app.domain.Curso;
import tec.bd.app.domain.Estudiante;

import java.util.List;

public interface CursoDAO extends GenericDAO<Curso,Integer> {

    List<Curso> findByDeparment(String departament);
}
