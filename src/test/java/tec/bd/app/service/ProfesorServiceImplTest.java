package tec.bd.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tec.bd.app.dao.ProfesorDAO;
import tec.bd.app.domain.Estudiante;
import tec.bd.app.domain.Profesor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class ProfesorServiceImplTest {

    @Mock
    private ProfesorDAO profesorSetDAO;

    @InjectMocks
    private ProfesorServiceImpl profesorService;


    @BeforeEach
    public void setUp() throws Exception {

    }

    @Test
    public void whenNoDataInDB_thenNoResult() throws Exception {

        given(this.profesorSetDAO.findAll()).willReturn(Collections.emptyList());

        var profesores = this.profesorService.getAll();

        verify(this.profesorSetDAO, times(1)).findAll();

        assertThat(profesores).hasSize(0);
    }

    @Test
    public void getAllTest() throws Exception {

        given(this.profesorSetDAO.findAll()).willReturn(List.of(
                mock(Profesor.class), mock(Profesor.class), mock(Profesor.class)
        ));

        var profesores = this.profesorService.getAll();

        verify(this.profesorSetDAO, times(1)).findAll();

        assertThat(profesores).hasSize(3);

    }

    @Test
    public void addNewProfessor() throws Exception {

        /*
        En la primera invocacion va a devolver una lista de 3 estudiantes. En la segunda una lista de 4
         */
        given(this.profesorSetDAO.findAll()).willReturn(
                List.of(mock(Profesor.class), mock(Profesor.class), mock(Profesor.class)),
                List.of(mock(Profesor.class), mock(Profesor.class), mock(Profesor.class), mock(Profesor.class))
        );

        var ProfesorsBeforeSave = this.profesorService.getAll();

        var roger = new Profesor(2, "Roger", "Martinez", "San Josesito");
        profesorService.addNew(roger);

        var ProfesorsAfterSave = this.profesorService.getAll();

        verify(this.profesorSetDAO, times(1)).save(roger);
        assertThat(ProfesorsAfterSave.size()).isGreaterThan(ProfesorsBeforeSave.size());
    }

    @Test
    public void deleteProfessor() throws Exception {

        /*
        En la primera invocacion va a devolver una lista de 3 estudiantes. En la segunda una lista de 2
         */
        given(this.profesorSetDAO.findAll()).willReturn(
                List.of(mock(Profesor.class), mock(Profesor.class), mock(Profesor.class)),
                List.of(mock(Profesor.class), mock(Profesor.class))
        );

        var ProfesorsBeforeSave = this.profesorService.getAll();

        profesorService.deleteProfessor(1);

        var ProfesorsAfterSave = this.profesorService.getAll();

        verify(this.profesorSetDAO, times(1)).delete(1);
        assertThat(ProfesorsAfterSave.size()).isLessThan(ProfesorsBeforeSave.size());
    }

//    @Test
//    public void updateProfessor() throws Exception {
//
//        given(this.profesorSetDAO.findById(anyInt())).willReturn(
//                Optional.of(mock(Profesor.class)),
//                Optional.of(mock(Profesor.class))
//        );
//
//        var profesorBefore = this.profesorService.getById(2);
//
//        var soledad = new Profesor(2, "Soledad", "Marioneta", "Alajuelita");
//        profesorService.updateProfessor(soledad);
//
//        var profesorAfter = this.profesorService.getById(2);
//
//        verify(this.profesorSetDAO, times(1)).update(soledad);
//        assertThat(profesorAfter).isNotSameAs(profesorBefore);
//    }

    @Test
    public void getProfessorsByCity() throws Exception {
        //TODO: hay que implementarlo
    }

}