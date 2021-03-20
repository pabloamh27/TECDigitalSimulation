package tec.bd.app.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tec.bd.app.database.set.Row;
import tec.bd.app.database.set.RowAttribute;
import tec.bd.app.database.set.SetDB;
import tec.bd.app.domain.Entity;
import tec.bd.app.domain.Profesor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;



public class ProfesorDAOImplTest {

    private ProfesorDAOImpl profesorDAO;

    @BeforeEach
    public void setUp() throws Exception {

        var carolinaId = new RowAttribute("id", 2);
        var carolinaNombre = new RowAttribute("nombre", "Carolina");
        var carolinaApellido = new RowAttribute("apellido", "Quiros");
        var carolinaCiudad = new RowAttribute("ciudad", "TresRios");
        var carolinaRow = new Row(new RowAttribute[]{carolinaId, carolinaNombre, carolinaApellido, carolinaCiudad});

        var lorenaId = new RowAttribute("id", 4);
        var lorenaNombre = new RowAttribute("nombre", "Lorena");
        var lorenaApellido = new RowAttribute("apellido", "Phelps");
        var lorenaCiudad = new RowAttribute("ciudad", "Cartago");
        var lorenaRow = new Row(new RowAttribute[]{lorenaId, lorenaNombre, lorenaApellido, lorenaCiudad});

        var geraldId = new RowAttribute("id", 3);
        var geraldNombre = new RowAttribute("nombre", "Gerald");
        var geraldApellido = new RowAttribute("apellido", "OfRivia");
        var geraldCiudad = new RowAttribute("ciudad", "Infiernillo");
        var geraldRow = new Row(new RowAttribute[]{geraldId, geraldNombre, geraldApellido, geraldCiudad});

        var tables = new HashMap<Class<? extends Entity>, Set<Row>>();
        tables.put(Profesor.class, new HashSet<>() {{
            add(carolinaRow);
            add(lorenaRow);
            add(geraldRow);
        }});
        var setDB = new SetDB(tables);
        this.profesorDAO = new ProfesorDAOImpl(setDB, Profesor.class);
    }

    @Test
    public void findAll() throws Exception {
        var actual = this.profesorDAO.findAll();
        assertThat(actual).hasSize(3);
    }

    @Test
    public void findById() throws Exception {
        var profe = this.profesorDAO.findById(3);
        assertThat(profe.get().getId()).isEqualTo(3);
        assertThat(profe.get().getNombre()).isEqualTo("Gerald");
        assertThat(profe.get().getApellido()).isEqualTo("OfRivia");
        assertThat(profe.get().getCiudad()).isEqualTo("Infiernillo");
    }

    @Test
    public void save() throws Exception {
        this.profesorDAO.save(new Profesor(40, "Jorge", "Chacon", "Puntarenas"));
        var profe = this.profesorDAO.findById(40);
        assertThat(this.profesorDAO.findAll()).hasSize(4);
        assertThat(profe.isPresent()).isTrue();
        assertThat(profe.get().getId()).isEqualTo(40);
        assertThat(profe.get().getNombre()).isEqualTo("Jorge");
        assertThat(profe.get().getApellido()).isEqualTo("Chacon");
        assertThat(profe.get().getCiudad()).isEqualTo("Puntarenas");
    }

    @Test
    public void update() throws Exception {
        var current = this.profesorDAO.findById(4);
        current.get().setApellido("Phelps");
        current.get().setCiudad("SanJuan");
        var actual = this.profesorDAO.update(current.get());
        assertThat(this.profesorDAO.findAll()).hasSize(3);
        assertThat(actual.get().getId()).isEqualTo(4);
        assertThat(actual.get().getNombre()).isEqualTo("Lorena");
        assertThat(actual.get().getApellido()).isEqualTo("Phelps");
        assertThat(actual.get().getCiudad()).isEqualTo("SanJuan");
    }

    @Test
    public void delete() throws Exception {
        this.profesorDAO.delete(4);
        assertThat(this.profesorDAO.findAll()).hasSize(2);
    }

    @Test
    public void getByCity() throws Exception {
        var profesores = this.profesorDAO.findByCity("TresRios");
        assertThat(profesores.get(0).getId()).isEqualTo(2);
        assertThat(profesores.get(0).getNombre()).isEqualTo("Carolina");
        assertThat(profesores.get(0).getApellido()).isEqualTo("Quiros");
        assertThat(profesores.get(0).getCiudad()).isEqualTo("TresRios");
    }
}
