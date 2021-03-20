package tec.bd.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import tec.bd.app.dao.EstudianteDAO;
import tec.bd.app.domain.Curso;
import tec.bd.app.domain.Estudiante;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EstudianteServiceImplTest {

    @Mock
    private EstudianteDAO estudianteSetDAO;

    @InjectMocks
    private EstudianteServiceImpl estudianteService;


    @BeforeEach
    public void setUp() throws Exception {

    }

    @Test
    public void whenNoDataInDB_thenNoResult() throws Exception {

        given(this.estudianteSetDAO.findAll()).willReturn(Collections.emptyList());

        var Estudiantes = this.estudianteService.getAll();

        verify(this.estudianteSetDAO, times(1)).findAll();

        assertThat(Estudiantes).hasSize(0);
    }

    @Test
    public void getAllTest() throws Exception {

        given(this.estudianteSetDAO.findAll()).willReturn(List.of(
                mock(Estudiante.class), mock(Estudiante.class), mock(Estudiante.class)
        ));

        var Estudiantes = this.estudianteService.getAll();

        verify(this.estudianteSetDAO, times(1)).findAll();

        assertThat(Estudiantes).hasSize(3);

    }

    @Test
    public void addNewStudent() throws Exception {

        /*
        En la primera invocacion va a devolver una lista de 3 Estudiantes. En la segunda una lista de 4
         */
        given(this.estudianteSetDAO.findAll()).willReturn(
                List.of(mock(Estudiante.class), mock(Estudiante.class), mock(Estudiante.class)),
                List.of(mock(Estudiante.class), mock(Estudiante.class), mock(Estudiante.class), mock(Estudiante.class))
        );

        var studentsBeforeSave = this.estudianteService.getAll();

        var maria = new Estudiante(10, "Maria", "Lopez", 85);
        estudianteService.addNew(maria);

        var studentsAfterSave = this.estudianteService.getAll();

        verify(this.estudianteSetDAO, times(1)).save(maria);
        assertThat(studentsAfterSave.size()).isGreaterThan(studentsBeforeSave.size());
    }

    @Test
    public void deleteStudent() throws Exception {

        /*
        En la primera invocacion va a devolver una lista de 3 Estudiantes. En la segunda una lista de 2
         */
        given(this.estudianteSetDAO.findAll()).willReturn(
                List.of(mock(Estudiante.class), mock(Estudiante.class), mock(Estudiante.class)),
                List.of(mock(Estudiante.class), mock(Estudiante.class))
        );

        given(this.estudianteSetDAO.findById(anyInt())).willReturn(Optional.of(mock(Estudiante.class)));

        var studentsBeforeSave = this.estudianteService.getAll();

        estudianteService.deleteStudent(2);

        var studentsAfterSave = this.estudianteService.getAll();

        verify(this.estudianteSetDAO, times(1)).delete(2);
        assertThat(studentsAfterSave.size()).isLessThan(studentsBeforeSave.size());
    }

    @Test
    public void updateStudent() throws Exception {

        given(this.estudianteSetDAO.findById(anyInt())).willReturn(
                Optional.of(mock(Estudiante.class)),
                Optional.of(mock(Estudiante.class))
        );

        var studentBefore = this.estudianteService.getById(2);

        var soledad = new Estudiante(52, "Susana", "Horia", 102);
        estudianteService.updateStudent(soledad);

        var studentAfter = this.estudianteService.getById(52);

        verify(this.estudianteSetDAO, times(1)).update(soledad);
        assertThat(studentAfter).isNotSameAs(studentBefore);
    }

    @Test
    public void getStudentsSortedByLastName() throws Exception {
        given(this.estudianteSetDAO.findAllSortByLastName()).willReturn(List.of(
                mock(Estudiante.class), mock(Estudiante.class), mock(Estudiante.class)));

        given(this.estudianteSetDAO.findAll()).willReturn(List.of(
                mock(Estudiante.class), mock(Estudiante.class), mock(Estudiante.class)));

        var studentsBeforeSave = this.estudianteService.getAll();

        estudianteService.getStudentsSortedByLastName();

        var studentsAfterSave = this.estudianteService.getStudentsSortedByLastName();

        verify(this.estudianteSetDAO, times(2)).findAllSortByLastName();
        assertThat(studentsAfterSave).isNotEqualTo(studentsBeforeSave);

    }

    @Test
    public void getStudentsByLastName() throws Exception {
        given(this.estudianteSetDAO.findByLastName(anyString())).willReturn(List.of(
                mock(Estudiante.class), mock(Estudiante.class), mock(Estudiante.class)));

        estudianteService.getStudentsByLastName("Hikeru");

        verify(this.estudianteSetDAO, times(1)).findByLastName("Hikeru");
    }

}
