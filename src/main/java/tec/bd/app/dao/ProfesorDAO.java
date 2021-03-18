package tec.bd.app.dao;

import tec.bd.app.domain.Estudiante;
import tec.bd.app.domain.Profesor;

import java.util.List;

public interface ProfesorDAO extends GenericDAO<Profesor, Integer> {
    //nuevo metodo para buscar por ciudad

    /**
     * Obtener todos los estudiantes por ciudad
     * @param city
     * @return
     */
    List<Profesor> findByCity(String city);


}
