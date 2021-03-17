package tec.bd.app.service;

import tec.bd.app.dao.CursoDAO;
import tec.bd.app.domain.Curso;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CursoServiceImpl implements CursoService{

    private CursoDAO cursoDAO;
    private Arrays table;


    public CursoServiceImpl(CursoDAO cursoDAO){
        this.cursoDAO = cursoDAO;
    }


    @Override
    public List<Curso> getAllCurses() {
        return this.cursoDAO.findAll();
    }

    @Override
    public Optional<Curso> getCurseById(int id) {

        if (id<=0){
            return Optional.empty();
        }
        return this.cursoDAO.findById(id);
    }


    @Override
    public void addNewCurse(Curso c) {
        Optional<Curso> curso = this.cursoDAO.findById(c.getId());
        if(!curso.isPresent()) {
            this.cursoDAO.save(c);
        }

    }

    @Override
    public Optional<Curso> updateCurse(Curso c) {
        Optional<Curso> curso = this.cursoDAO.findById(c.getId());
        if(curso.isPresent()) {
            return this.cursoDAO.update(c);
        }
        return Optional.empty();
    }

    @Override
    public void deleteCurse(int id) {
        Optional<Curso> curso = this.cursoDAO.findById(id);
        if(curso.isPresent()) {
            this.cursoDAO.delete(id);
        }
    }

    @Override
    public List<Curso> getByDepartment(String department) {
        List<Curso> listaPorDepartamento = new List <Curso>();
        var cursos : List<Curso> = this.table.stream().map(this::rowToEntity).collect(Collectors.toList());
        for(int i = 0; i<cursos.size() ; i++) {
            var actual : Curso = cursos.get(i);
            if (actual.getDepartament() == departamento) {
                listaPorDepartamento.add(actual);
            }
        }
        return listaPorDepartamento.stream().collect(Collectors.toList());
    }
}
