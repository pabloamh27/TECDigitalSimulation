package tec.bd.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import tec.bd.app.dao.CursoDAO;
import org.mockito.junit.jupiter.MockitoExtension;
import tec.bd.app.domain.Curso;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CursoServiceImplTest {

    @Mock
    private CursoDAO cursoDAO;

    @InjectMocks
    private CursoServiceImpl cursoService;


    @BeforeEach
    public void setUp() throws Exception {

    }

    @Test
    public void whenNoDataInDB_thenNoResult() throws Exception {

        given(this.cursoDAO.findAll()).willReturn(Collections.emptyList());

        var cursos = this.cursoService.getAll();

        verify(this.cursoDAO, times(1)).findAll();

        assertThat(cursos).hasSize(0);
    }

    @Test
    public void getAllTest() throws Exception {

        given(this.cursoDAO.findAll()).willReturn(List.of(
                mock(Curso.class), mock(Curso.class), mock(Curso.class)
        ));
        
        var cursos = this.cursoService.getAll();

        verify(this.cursoDAO, times(1)).findAll();

        assertThat(cursos).hasSize(3);

    }

    @Test
    public void addNewCourse() throws Exception {

        /*
        En la primera invocacion va a devolver una lista de 3 estudiantes. En la segunda una lista de 4
         */
        given(this.cursoDAO.findAll()).willReturn(
                List.of(mock(Curso.class), mock(Curso.class), mock(Curso.class)),
                List.of(mock(Curso.class), mock(Curso.class), mock(Curso.class), mock(Curso.class))
        );

        var cursosBeforeSave = this.cursoService.getAll();

        var fisica = new Curso(1, "Quimica", "CienciasExactas", 4);
        cursoService.addNew(fisica);

        var cursosAfterSave = this.cursoService.getAll();

        verify(this.cursoDAO, times(1)).save(fisica);
        assertThat(cursosAfterSave.size()).isGreaterThan(cursosBeforeSave.size());
    }

    @Test
    public void deleteCourse() throws Exception {

        /*
        En la primera invocacion va a devolver una lista de 3 estudiantes. En la segunda una lista de 2
         */
        given(this.cursoDAO.findAll()).willReturn(
                List.of(mock(Curso.class), mock(Curso.class), mock(Curso.class)),
                List.of(mock(Curso.class), mock(Curso.class))
        );

        given(this.cursoDAO.findById(anyInt())).willReturn(Optional.of(mock(Curso.class)));

        var ProfesorsBeforeSave = this.cursoService.getAll();

        cursoService.deleteCourse(1);

        var ProfesorsAfterSave = this.cursoService.getAll();

        verify(this.cursoDAO, times(1)).delete(1);
        assertThat(ProfesorsAfterSave.size()).isLessThan(ProfesorsBeforeSave.size());
    }


    @Test
    public void updateCourse() throws Exception {

        given(this.cursoDAO.findById(anyInt())).willReturn(
                Optional.of(mock(Curso.class)),
                Optional.of(mock(Curso.class))
        );



        var cursoBefore = this.cursoService.getById(2);

        var historia = new Curso(2, "ApreciacionAlCine", "Cultura", 3);
        cursoService.updateCourse(historia);

        var cursoAfter = this.cursoService.getById(2);

        verify(this.cursoDAO, times(1)).update(historia);
        assertThat(cursoAfter).isNotSameAs(cursoBefore);
    }

    @Test
    public void getCourseByDepartment() throws Exception {
        given(this.cursoDAO.findByDepartment(anyString())).willReturn(List.of(
                mock(Curso.class), mock(Curso.class), mock(Curso.class)));

        cursoService.getCourseByDepartment("Programacion");

        verify(this.cursoDAO, times(1)).findByDepartment("Programacion");
    }

}
