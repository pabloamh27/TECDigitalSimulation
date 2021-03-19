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
    private CursoDAO cursoSetDAO;

    @InjectMocks
    private CursoServiceImpl cursoService;


    @BeforeEach
    public void setUp() throws Exception {

    }

    @Test
    public void whenNoDataInDB_thenNoResult() throws Exception {

        given(this.cursoSetDAO.findAll()).willReturn(Collections.emptyList());

        var cursos = this.cursoService.getAll();

        verify(this.cursoSetDAO, times(1)).findAll();

        assertThat(cursos).hasSize(0);
    }

    @Test
    public void getAllTest() throws Exception {

        given(this.cursoSetDAO.findAll()).willReturn(List.of(
                mock(Curso.class), mock(Curso.class), mock(Curso.class)
        ));
        
        var cursos = this.cursoService.getAll();

        verify(this.cursoSetDAO, times(1)).findAll();

        assertThat(cursos).hasSize(3);

    }

    @Test
    public void addNewCourse() throws Exception {

        /*
        En la primera invocacion va a devolver una lista de 3 estudiantes. En la segunda una lista de 4
         */
        given(this.cursoSetDAO.findAll()).willReturn(
                List.of(mock(Curso.class), mock(Curso.class), mock(Curso.class)),
                List.of(mock(Curso.class), mock(Curso.class), mock(Curso.class), mock(Curso.class))
        );

        var cursosBeforeSave = this.cursoService.getAll();

        var karol = new Curso(2, "Karol", "Jimenez", 21);
        cursoService.addNew(karol);

        var cursosAfterSave = this.cursoService.getAll();

        verify(this.cursoSetDAO, times(1)).save(karol);
        assertThat(cursosAfterSave.size()).isGreaterThan(cursosBeforeSave.size());
    }

//    @Test
//    public void deleteCourse() throws Exception {
//
//        /*
//        En la primera invocacion va a devolver una lista de 3 estudiantes. En la segunda una lista de 2
//         */
//        given(this.cursoSetDAO.findAll()).willReturn(
//                List.of(mock(Curso.class), mock(Curso.class), mock(Curso.class)),
//                List.of(mock(Curso.class), mock(Curso.class))
//        );
//
//        var cursosBeforeSave = this.cursoService.getAll();
//
//        cursoService.deleteCourse(1);
//
//        var cursosAfterSave = this.cursoService.getAll();
//
//        verify(this.cursoSetDAO, times(1)).delete(1);
//        assertThat(cursosAfterSave.size()).isLessThan(cursosBeforeSave.size());
//    }

    @Test
    public void updateCourse() throws Exception {

        given(this.cursoSetDAO.findById(anyInt())).willReturn(
                Optional.of(mock(Curso.class)),
                Optional.of(mock(Curso.class))
        );

        var cursoBefore = this.cursoService.getById(2);

        var karol = new Curso(2, "Karol", "Jimenez", 21);
        cursoService.updateCourse(karol);

        var cursoAfter = this.cursoService.getById(2);

        verify(this.cursoSetDAO, times(1)).update(karol);
        assertThat(cursoAfter).isNotSameAs(cursoBefore);
    }

    @Test
    public void getCourseByDepartment() throws Exception {
        //TODO: hay que implementarlo
    }

}
