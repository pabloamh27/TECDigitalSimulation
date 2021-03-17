package tec.bd.app.dao;

import tec.bd.app.database.set.Row;
import tec.bd.app.database.set.SetDB;
import tec.bd.app.domain.Profesor;

public class ProfesorSetDAOImpl extends GenericSetDAOImpl<Profesor, Integer> implements ProfesorSetDAO {

    protected ProfesorSetDAOImpl(SetDB setDB, Class<Profesor> clazz) {
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
