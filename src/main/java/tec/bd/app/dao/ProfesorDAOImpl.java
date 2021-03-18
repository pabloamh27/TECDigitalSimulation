package tec.bd.app.dao;

import tec.bd.app.database.set.Row;
import tec.bd.app.database.set.RowAttribute;
import tec.bd.app.database.set.SetDB;
import tec.bd.app.domain.Profesor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProfesorDAOImpl extends GenericDAOImpl<Profesor, Integer> implements ProfesorDAO {

    protected ProfesorDAOImpl(SetDB setDB, Class<Profesor> clazz) {
        super(setDB, clazz);
    }

    @Override
    public List<Profesor> findByCity(String city) {
        List<Profesor> listaPorCiudad = new ArrayList<>();
        var profesor =this.table.stream().map(this::rowToEntity).collect(Collectors.toList());
        for(Profesor actual : profesor){
            if(actual.getApellido().equals(city) ){
                listaPorCiudad.add(actual);

            }
        }
        return listaPorCiudad.stream().collect(Collectors.toList());
    }

    @Override
    protected Profesor rowToEntity(Row row) {
        // conversiones de Row a Profesor
        var id = row.intAttributeValue("id");
        var nombre = row.stringAttributeValue("nombre");
        var apellido = row.stringAttributeValue("apellido");
        var ciudad = row.stringAttributeValue("edad");
        return new Profesor(id, nombre, apellido, ciudad);
    }

    @Override
    protected Row entityToRow(Profesor p) {

        // conversiones de Estudiante a Row
        return new Row(new RowAttribute[] {
                new RowAttribute("id", p.getId()),
                new RowAttribute("nombre", p.getNombre()),
                new RowAttribute("apellido", p.getApellido()),
                new RowAttribute("ciudad", p.getCiudad()) });
    }
}

