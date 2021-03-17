package tec.bd.app.service;

import tec.bd.app.dao.CursoDAO;
import tec.bd.app.domain.Curso;

import java.util.List;
import java.util.Optional;

public class CursoServiceImpl implements CursoService{

    private CursoDAO cursoDAO;


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
        //necesita validaciones
        /*department nulo o vacio
        * sino es nulo o vacio se va poder llama al dao sino
        * retorna lista vacía*/

        return null;
    }
}
