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

        var discretaId = new RowAttribute("id", 1);
        var discretaNombre = new RowAttribute("nombre", "Discreta");
        var discretaDepartamento = new RowAttribute("departamento", "CienciasExactas");
        var discretaCreditos = new RowAttribute("creditos", 4);
        var discretaRow = new Row(new RowAttribute[]{ discretaId, discretaNombre, discretaDepartamento, discretaCreditos });

        var inglesId = new RowAttribute("id", 2);
        var inglesNombre = new RowAttribute("nombre", "Ingles");
        var inglesDepartamento = new RowAttribute("departamento", "Lenguas");
        var inglesCreditos = new RowAttribute("creditos", 2);
        var inglesRow = new Row(new RowAttribute[]{ inglesId, inglesNombre, inglesDepartamento, inglesCreditos });

        var estudiossocialesId = new RowAttribute("id", 3);
        var estudiossocialesNombre = new RowAttribute("nombre", "EstudiosSociales");
        var estudiossocialesDepartamento = new RowAttribute("departamento", "CienciasSociales");
        var estudiossocialesCreditos = new RowAttribute("creditos", 1);
        var estudiossocialesRow = new Row(new RowAttribute[]{ estudiossocialesId, estudiossocialesNombre, estudiossocialesDepartamento, estudiossocialesCreditos });

        var tables = new HashMap<Class<? extends Entity>, Set<Row>>();
        tables.put(Curso.class, new HashSet<>() {{
            add(discretaRow);
            add(inglesRow);
            add(estudiossocialesRow);
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
        assertThat(curso.get().getNombre()).isEqualTo("EstudiosSociales");
        assertThat(curso.get().getDepartamento()).isEqualTo("CienciasSociales");
        assertThat(curso.get().getCreditos()).isEqualTo(1);
    }

    @Test
    public void save() throws Exception {
        this.cursoDAO.save(new Curso(5, "Fisica", "CienciasExactas", 4));
        var curso = this.cursoDAO.findById(5);
        assertThat(this.cursoDAO.findAll()).hasSize(4);
        assertThat(curso.isPresent()).isTrue();
        assertThat(curso.get().getId()).isEqualTo(5);
        assertThat(curso.get().getNombre()).isEqualTo("Fisica");
        assertThat(curso.get().getDepartamento()).isEqualTo("CienciasExactas");
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
        assertThat(actual.get().getNombre()).isEqualTo("EstudiosSociales");
        assertThat(actual.get().getDepartamento()).isEqualTo("Matematicas");
        assertThat(actual.get().getCreditos()).isEqualTo(6);
    }

    @Test
    public void delete() throws Exception {
        this.cursoDAO.delete(2);
        assertThat(this.cursoDAO.findAll()).hasSize(2);
    }

    @Test
    public void getByDepartment() throws Exception {
        var cursos = this.cursoDAO.findByDepartment("Lenguas");
        assertThat(cursos.get(0).getId()).isEqualTo(2);
        assertThat(cursos.get(0).getNombre()).isEqualTo("Ingles");
        assertThat(cursos.get(0).getDepartamento()).isEqualTo("Lenguas");
        assertThat(cursos.get(0).getCreditos()).isEqualTo(2);
    }

}


