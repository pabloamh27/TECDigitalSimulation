package tec.bd.app.dao.set;

import tec.bd.app.dao.ProfesorDAO;
import tec.bd.app.database.set.Row;
import tec.bd.app.database.set.RowAttribute;
import tec.bd.app.database.set.SetDB;
import tec.bd.app.domain.Curso;
import tec.bd.app.domain.Profesor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProfesorSetDAOImpl extends GenericSetDAOImpl<Profesor, Integer> implements ProfesorDAO {

    public ProfesorSetDAOImpl(SetDB setDB) {
        super(setDB, Profesor.class);
    }

    @Override
    public List<Profesor> findByCity(String city) {
        var listaProfesores = this.table.stream().map(this::rowToEntity).collect(Collectors.toList());
        ArrayList<Profesor> listaApellidos = new ArrayList<>();
        for(int i = 0; i< listaProfesores.size(); i++){
            Profesor actual = listaProfesores.get(i);
            if(actual.getCiudad().equals(city)){

                listaApellidos.add(actual);
            }

        }

        return listaApellidos.stream().collect(Collectors.toList());
    }

    @Override
    protected Profesor rowToEntity(Row row) {
        // conversiones de Row a Profesor
        var id = row.intAttributeValue("id");
        var nombre = row.stringAttributeValue("nombre");
        var apellido = row.stringAttributeValue("apellido");
        var ciudad = row.stringAttributeValue("ciudad");
        return new Profesor(id, nombre, apellido, ciudad);
    }

    @Override
    protected Row entityToRow(Profesor p) {
        // conversiones de Curso a Row
        return new Row(new RowAttribute[] {
                new RowAttribute("id", p.getId()),
                new RowAttribute("nombre", p.getNombre()),
                new RowAttribute("apellido", p.getApellido()),
                new RowAttribute("ciudad", p.getCiudad())
        });
    }
}
