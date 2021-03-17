package tec.bd.app.service;

import tec.bd.app.domain.Profesor;

import java.util.List;
import java.util.Optional;

public class ProfesorServiceImpl implements ProfesorService{

    @Override
    public List<Profesor> getAll() {
        return null;
    }

    @Override
    public Optional<Profesor> getById(int Id) {
        return Optional.empty();
    }

    @Override
    public void addNew(Profesor p) {

    }

    @Override
    public Optional<Profesor> updateProfessor(Profesor p) {
        return Optional.empty();
    }

    @Override
    public void deleteProfessor(int id) {

    }
}
