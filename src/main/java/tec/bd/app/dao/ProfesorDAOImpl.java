package tec.bd.app.dao;

import tec.bd.app.database.set.Row;
import tec.bd.app.database.set.SetDB;
import tec.bd.app.domain.Profesor;

public class ProfesorDAOImpl extends GenericSetDAOImpl<Profesor, Integer> implements ProfesorDAO {

    protected ProfesorDAOImpl(SetDB setDB, Class<Profesor> clazz) {
        super(setDB, clazz);
    }

    @Override
    protected Profesor rowToEntity(Row row) {
        return null;
    }

    @Override
    protected Row entityToRow(Profesor e) {
        return null;
    }
}
