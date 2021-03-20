package tec.bd.app.dao;

import tec.bd.app.domain.Profesor;

import java.util.List;

public interface ProfesorDAO extends GenericDAO<Profesor, Integer> {

    List<Profesor> findByCity(String city);


}
