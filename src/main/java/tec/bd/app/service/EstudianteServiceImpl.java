package tec.bd.app.service;

import tec.bd.app.dao.EstudianteDAO;
import tec.bd.app.domain.Estudiante;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class EstudianteServiceImpl implements EstudianteService {

    private EstudianteDAO estudianteDAO;

    public EstudianteServiceImpl(EstudianteDAO estudianteDAO) {
        this.estudianteDAO = estudianteDAO;
    }

    @Override
    public List<Estudiante> getAll() {
        return this.estudianteDAO.findAll();
    }

    @Override
    public Optional<Estudiante> getById(int carne) {

        //TODO: validar el carne > 0. Si no cumple con eso se devuelve Optional.empty()
        if (carne > 0){
            return this.estudianteDAO.findById(carne);
        }
        return Optional.empty();
    }

    public void addNew(Estudiante e) {
        Optional<Estudiante> estudiante = this.estudianteDAO.findById(e.getCarne());
        if(!estudiante.isPresent()) {
            this.estudianteDAO.save(e);
        }
    }

    public Optional<Estudiante> updateStudent(Estudiante e) {
        //TODO: validar que el carne exista en la BD. Si existe se actualiza
        if(this.getById(e.getCarne()).isPresent()){
            return this.estudianteDAO.update(e);
        }
        return Optional.empty();
    }

    public void deleteStudent(int carne) {
        //TODO: validar que el carne exista en la BD. Si existe se borra
        Optional<Estudiante> estudiante = this.estudianteDAO.findById(carne);
        if(!estudiante.isPresent()) {
            this.estudianteDAO.delete(carne);
        }
    }

    public List<Estudiante> getStudentsSortedByLastName() {

        return this.estudianteDAO.findAllSortByLastName();
    }

    @Override
    public List<Estudiante> getStudentsByLastName(String lastName) {
        //TODO: implementarlo
        //validar que el lastName no sea nulo
        if (!lastName.isEmpty()){
            return this.estudianteDAO.findByLastName(lastName);
        }
        return Collections.emptyList();
    }

}

