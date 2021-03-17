package tec.bd.app.dao;

import tec.bd.app.database.set.Row;
import tec.bd.app.database.set.RowAttribute;
import tec.bd.app.database.set.SetDB;
import tec.bd.app.domain.Curso;


import java.util.List;

public class CursoDAOImpl extends GenericSetDAOImpl<Curso, Integer> implements CursoDAO {

    public CursoDAOImpl(SetDB setDB, Class<Curso> clazz) {
        super(setDB, clazz);
    }

    @Override
    public List<Curso> findByDeparment(String departament) {

        return null;
    }



    @Override
    protected Curso rowToEntity(Row row) {
        // conversiones de Row a Curso
        var id = row.intAttributeValue("id");
        var nombre = row.stringAttributeValue("nombre");
        var departamento = row.stringAttributeValue("departamento");
        var creditos = row.intAttributeValue("creditos");
        return new Curso(id, nombre, departamento, creditos);
    }

    @Override
    protected Row entityToRow(Curso e) {
        // conversiones de Curso a Row
        return new Row(new RowAttribute[] {
                new RowAttribute("id", e.getId()),
                new RowAttribute("nombre", e.getNombre()),
                new RowAttribute("departamento", e.getDepartamento()),
                new RowAttribute("creditos", e.getCreditos()) });
    }



}
