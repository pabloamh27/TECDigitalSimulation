package tec.bd.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tec.bd.app.bd.SetDB;
import tec.bd.app.domain.Estudiante;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

public class EstudianteServiceTest {

    private EstudianteService estudianteService;


    @BeforeEach
    public void setUp() throws Exception {
        Set<Estudiante> estudiantesTable = new HashSet<>(){{
            add(new Estudiante(1, "Juan", "Perez", 20));
            add(new Estudiante(32, "Maria", "Rojas", 21));
            add(new Estudiante(22, "Roberto", "Brenes", 22));
        }};
        SetDB database = new SetDB(estudiantesTable);

        this.estudianteService = new EstudianteService(database);
    }

    @Test
    public void whenNoDataInDB_thenNoResult() throws Exception {
        SetDB database = new SetDB(Collections.emptySet());

        EstudianteService localEstudianteService = new EstudianteService(database);
        var estudiantes = localEstudianteService.getAll();

        assertThat(estudiantes).hasSize(0);
    }

    @Test
    public void getAllTest() throws Exception {

        var estudiantes = this.estudianteService.getAll();

        assertThat(estudiantes).hasSize(3);

    }

    @Test
    public void addNewStudent() throws Exception {

        var karol = new Estudiante(2, "Karol", "Jimenez", 21);
        estudianteService.addNew(karol);

        assertThat(this.estudianteService.getAll()).hasSize(4);

        var maria = new Estudiante(32, "Maria", "Rojas", 21);
        estudianteService.addNew(maria);

        assertThat(this.estudianteService.getAll()).hasSize(4);
    }

    @Test
    public void deleteStudent() throws Exception {

        estudianteService.deleteStudent(1);

        assertThat(this.estudianteService.getAll()).hasSize(2);

        estudianteService.deleteStudent(58);

        assertThat(this.estudianteService.getAll()).hasSize(2);

    }

    @Test
    public void updateStudent() throws Exception {
        var rosalia = new Estudiante(32, "Rosalia", "Solano", 25);
        estudianteService.updateStudent(rosalia);

        assertThat(this.estudianteService.getAll()).hasSize(3);

        var josefa = new Estudiante(58, "Josefa", "Viquez", 55);
        estudianteService.updateStudent(josefa);

        assertThat(this.estudianteService.getAll()).hasSize(3);
    }

}