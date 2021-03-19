package tec.bd.app.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tec.bd.app.database.set.Row;
import tec.bd.app.database.set.RowAttribute;
import tec.bd.app.database.set.SetDB;
import tec.bd.app.domain.Curso;
import tec.bd.app.domain.Entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;


public class CursoDAOImplTest {
    private CursoDAOImpl cursoDAO;

    @BeforeEach
    public void setUp() throws Exception {

        var calculoId = new RowAttribute("id", 1);
        var calculoNombre = new RowAttribute("nombre", "Calculo");
        var calculoDepartamento = new RowAttribute("departamento", "Ciencias exactas");
        var calculoCreditos = new RowAttribute("creditos", 4);
        var calculoRow = new Row(new RowAttribute[]{ calculoId, calculoNombre, calculoDepartamento, calculoCreditos });

        var españolId = new RowAttribute("id", 2);
        var españolNombre = new RowAttribute("nombre", "Español");
        var españolDepartamento = new RowAttribute("departamento", "Lenguas");
        var españolCreditos = new RowAttribute("creditos", 2);
        var españolRow = new Row(new RowAttribute[]{ españolId, españolNombre, españolDepartamento, españolCreditos });

        var civicaId = new RowAttribute("id", 3);
        var civicaNombre = new RowAttribute("nombre", "Civica");
        var civicaDepartamento = new RowAttribute("departamento", "Ciencias sociales");
        var civicaCreditos = new RowAttribute("creditos", 1);
        var civicaRow = new Row(new RowAttribute[]{ civicaId, civicaNombre, civicaDepartamento, civicaCreditos });

        var tables = new HashMap<Class<? extends Entity>, Set<Row>>();
        tables.put(Curso.class, new HashSet<>() {{
            add(calculoRow);
            add(españolRow);
            add(civicaRow);
        }});
        var setDB = new SetDB(tables);
        this.cursoDAO = new CursoDAOImpl(setDB, Curso.class);
    }

    @Test
    public void findAll() throws Exception {
        var actual = this.cursoDAO.findAll();
        assertThat(actual).hasSize(3);
    }

    @Test
    public void findById() throws Exception {
        var curso = this.cursoDAO.findById(3);
        assertThat(curso.get().getId()).isEqualTo(3);
        assertThat(curso.get().getNombre()).isEqualTo("Civica");
        assertThat(curso.get().getDepartamento()).isEqualTo("Ciencias sociales");
        assertThat(curso.get().getCreditos()).isEqualTo(1);
    }

    @Test
    public void save() throws Exception {
        this.cursoDAO.save(new Curso(5, "Fisica", "Ciencias exactas", 4));
        var curso = this.cursoDAO.findById(5);
        assertThat(this.cursoDAO.findAll()).hasSize(4);
        assertThat(curso.isPresent()).isTrue();
        assertThat(curso.get().getId()).isEqualTo(5);
        assertThat(curso.get().getNombre()).isEqualTo("Fisica");
        assertThat(curso.get().getDepartamento()).isEqualTo("Ciencias exactas");
        assertThat(curso.get().getCreditos()).isEqualTo(4);
    }

    @Test
    public void update() throws Exception {
        var current = this.cursoDAO.findById(3);
        current.get().setDepartamento("Matematicas");
        current.get().setCreditos(6);
        var actual = this.cursoDAO.update(current.get());
        assertThat(this.cursoDAO.findAll()).hasSize(3);
        assertThat(actual.get().getId()).isEqualTo(3);
        assertThat(actual.get().getNombre()).isEqualTo("Civica");
        assertThat(actual.get().getDepartamento()).isEqualTo("Matematicas");
        assertThat(actual.get().getCreditos()).isEqualTo(6);
    }

    @Test
    public void delete() throws Exception {
        this.cursoDAO.delete(2);
        assertThat(this.cursoDAO.findAll()).hasSize(2);
    }


}
