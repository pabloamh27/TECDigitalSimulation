package tec.bd.app.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tec.bd.app.database.set.Row;
import tec.bd.app.database.set.RowAttribute;
import tec.bd.app.database.set.SetDB;
import tec.bd.app.domain.Entity;
import tec.bd.app.domain.Estudiante;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

public class EstudianteDAOImplTest {

    private EstudianteDAOImpl estudianteDAO;

    @BeforeEach
    public void setUp() throws Exception {

        var robertoId = new RowAttribute("id", 5);
        var robertoNombre = new RowAttribute("nombre", "Roberto");
        var robertoApellido = new RowAttribute("apellido", "Araya");
        var robertoEdad = new RowAttribute("edad", 29);
        var robertoRow = new Row(new RowAttribute[]{ robertoId, robertoNombre, robertoApellido, robertoEdad });

        var manuelaId = new RowAttribute("id", 10);
        var manuelaNombre = new RowAttribute("nombre", "Manuela");
        var manuelaApellido = new RowAttribute("apellido", "Martinez");
        var manuelaEdad = new RowAttribute("edad", 45);
        var manuelaRow = new Row(new RowAttribute[]{ manuelaId, manuelaNombre, manuelaApellido, manuelaEdad });

        var kimberlyId = new RowAttribute("id", 15);
        var kimberlyNombre = new RowAttribute("nombre", "Kimberly");
        var kimberlyApellido = new RowAttribute("apellido", "Hidalgo");
        var kimberlyEdad = new RowAttribute("edad", 15);
        var kimberlyRow = new Row(new RowAttribute[]{ kimberlyId, kimberlyNombre, kimberlyApellido, kimberlyEdad });

        var tables = new HashMap<Class<? extends Entity>, Set<Row>>();
        tables.put(Estudiante.class, new HashSet<>() {{
            add(robertoRow);
            add(manuelaRow);
            add(kimberlyRow);
        }});
        var setDB = new SetDB(tables);
        this.estudianteDAO = new EstudianteDAOImpl(setDB, Estudiante.class);
    }

    @Test
    public void findAll() throws Exception {
        var actual = this.estudianteDAO.findAll();
        assertThat(actual).hasSize(3);
    }

    @Test
    public void findById() throws Exception {
        var student = this.estudianteDAO.findById(10);
        assertThat(student.get().getCarne()).isEqualTo(10);
        assertThat(student.get().getNombre()).isEqualTo("Manuela");
        assertThat(student.get().getApellido()).isEqualTo("Martinez");
        assertThat(student.get().getEdad()).isEqualTo(45);
    }

    @Test
    public void save() throws Exception {
        this.estudianteDAO.save(new Estudiante(40, "Jorge", "Chacon", 27));
        var estudiante = this.estudianteDAO.findById(40);
        assertThat(this.estudianteDAO.findAll()).hasSize(4);
        assertThat(estudiante.isPresent()).isTrue();
        assertThat(estudiante.get().getCarne()).isEqualTo(40);
        assertThat(estudiante.get().getNombre()).isEqualTo("Jorge");
        assertThat(estudiante.get().getApellido()).isEqualTo("Chacon");
        assertThat(estudiante.get().getEdad()).isEqualTo(27);
    }

    @Test
    public void update() throws Exception {
        var current = this.estudianteDAO.findById(10);
        current.get().setApellido("Rodriguez");
        current.get().setEdad(30);
        var actual = this.estudianteDAO.update(current.get());
        assertThat(this.estudianteDAO.findAll()).hasSize(3);
        assertThat(actual.get().getCarne()).isEqualTo(10);
        assertThat(actual.get().getNombre()).isEqualTo("Manuela");
        assertThat(actual.get().getApellido()).isEqualTo("Rodriguez");
        assertThat(actual.get().getEdad()).isEqualTo(30);
    }

    @Test
    public void delete() throws Exception {
        this.estudianteDAO.delete(10);
        assertThat(this.estudianteDAO.findAll()).hasSize(2);
    }

    @Test
    public void findByLastName() throws Exception {
        var estudiantes = this.estudianteDAO.findByLastName("Martinez");
        assertThat(estudiantes.get(0).getCarne()).isEqualTo(10);
        assertThat(estudiantes.get(0).getNombre()).isEqualTo("Manuela");
        assertThat(estudiantes.get(0).getApellido()).isEqualTo("Martinez");
        assertThat(estudiantes.get(0).getEdad()).isEqualTo(45);
    }

    @Test
    public void findAllSortedByLastName() throws Exception {
        var estudiantes = this.estudianteDAO.findAllSortByLastName();
        assertThat(estudiantes.get(0).getCarne()).isEqualTo(5);
        assertThat(estudiantes.get(0).getNombre()).isEqualTo("Roberto");
        assertThat(estudiantes.get(0).getApellido()).isEqualTo("Araya");
        assertThat(estudiantes.get(0).getEdad()).isEqualTo(29);
        //------------------------------------------------------------------------
        assertThat(estudiantes.get(1).getCarne()).isEqualTo(15);
        assertThat(estudiantes.get(1).getNombre()).isEqualTo("Kimberly");
        assertThat(estudiantes.get(1).getApellido()).isEqualTo("Hidalgo");
        assertThat(estudiantes.get(1).getEdad()).isEqualTo(15);
        //------------------------------------------------------------------------
        assertThat(estudiantes.get(2).getCarne()).isEqualTo(10);
        assertThat(estudiantes.get(2).getNombre()).isEqualTo("Manuela");
        assertThat(estudiantes.get(2).getApellido()).isEqualTo("Martinez");
        assertThat(estudiantes.get(2).getEdad()).isEqualTo(45);
    }


}
