package tec.bd.app.service;

import tec.bd.app.dao.EstudianteDAOSet;
import tec.bd.app.domain.Estudiante;

import java.util.List;
import java.util.Optional;

public class EstudianteServiceImpl implements EstudianteService {

    private EstudianteDAOSet estudianteDAOSet;

    public EstudianteServiceImpl(EstudianteDAOSet estudianteDAOSet) {
        this.estudianteDAOSet = estudianteDAOSet;
    }

    @Override
    public List<Estudiante> getAll() {
        return this.estudianteDAOSet.findAll();
    }

    @Override
    public Optional<Estudiante> getById(int carne) {

        //TODO: validar el carne > 0. Si no cumple con eso se devuelve Optional.empty()
        if (carne > 0){
            return this.estudianteDAOSet.findById(carne);
        }
        return Optional.empty();
    }

    public void addNew(Estudiante e) {
        Optional<Estudiante> estudiante = this.estudianteDAOSet.findById(e.getCarne());
        if(!estudiante.isPresent()) {
            this.estudianteDAOSet.save(e);
        }
    }

    public Optional<Estudiante> updateStudent(Estudiante e) {
        //TODO: validar que el carne exista en la BD. Si existe se actualiza
        if(this.getById(e.getCarne()).isPresent()){
            return this.estudianteDAOSet.update(e);
        }
        return Optional.empty();
    }

    public void deleteStudent(int carne) {
        //TODO: validar que el carne exista en la BD. Si existe se borra
        Optional<Estudiante> estudiante = this.estudianteDAOSet.findById(carne);
        if(!estudiante.isPresent()) {
            this.estudianteDAOSet.delete(carne);
        }
    }

    public List<Estudiante> getStudentsSortedByLastName() {
        return this.estudianteDAOSet.findAllSortByLastName();
    }

    @Override
    public List<Estudiante> getStudentsByLastName(String lastName) {
        //TODO: implementarlo
        //validar que el lastName no sea nulo
        return null;
    }

}

