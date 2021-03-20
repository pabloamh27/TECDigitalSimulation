package tec.bd.app;

import tec.bd.app.dao.*;
import tec.bd.app.database.set.Row;
import tec.bd.app.database.set.RowAttribute;
import tec.bd.app.database.set.SetDB;
import tec.bd.app.domain.Curso;
import tec.bd.app.domain.Entity;
import tec.bd.app.domain.Estudiante;
import tec.bd.app.domain.Profesor;
import tec.bd.app.service.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ApplicationContext {

    private SetDB setDB;
    private EstudianteDAO estudianteDAO;
    private EstudianteService estudianteServiceSet;

    private CursoDAO cursoDAO;
    private CursoService cursoService;

    private ProfesorDAO profesorDAO;
    private ProfesorService profesorService;


    private ApplicationContext() {

    }

    public static ApplicationContext init() {
        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.setDB = initSetDB();
        applicationContext.estudianteDAO = initEstudianteSetDAO(applicationContext.setDB);
        applicationContext.estudianteServiceSet = initEstudianteSetService(applicationContext.estudianteDAO);
        applicationContext.cursoDAO =  initCursoSetDAO(applicationContext.setDB);
        applicationContext.cursoService = initCursoService(applicationContext.cursoDAO);
        applicationContext.profesorDAO = initProfesorSetDAO(applicationContext.setDB);
        applicationContext.profesorService = initProfesorService(applicationContext.profesorDAO);

        return applicationContext;
    }

    private static SetDB initSetDB() {
        // Registros de la tabla estudiante
        var ronaldId = new RowAttribute("id", 1);
        var ronaldNombre = new RowAttribute("nombre", "Ronald");
        var ronaldApellido = new RowAttribute("apellido", "Sequeira");
        var ronaldEdad = new RowAttribute("edad", 59);
        var ronaldRow = new Row(new RowAttribute[]{ ronaldId, ronaldNombre, ronaldApellido, ronaldEdad });

        var luciaId = new RowAttribute("id", 2);
        var luciaNombre = new RowAttribute("nombre", "Lucia");
        var luciaApellido = new RowAttribute("apellido", "Rojas");
        var luciaEdad = new RowAttribute("edad", 94);
        var luciaRow = new Row(new RowAttribute[]{ luciaId, luciaNombre, luciaApellido, luciaEdad });

        var josePabloId = new RowAttribute("id", 3);
        var josePabloNombre = new RowAttribute("nombre", "JosePablo");
        var josePabloApellido = new RowAttribute("apellido", "Sanabria");
        var josePabloEdad = new RowAttribute("edad", 14);
        var josePabloRow = new Row(new RowAttribute[]{ josePabloId, josePabloNombre, josePabloApellido, josePabloEdad });

        var manriqueId = new RowAttribute("id", 4);
        var manriqueNombre = new RowAttribute("nombre", "Manrique");
        var manriqueApellido = new RowAttribute("apellido", "Guillen");
        var manriqueEdad = new RowAttribute("edad", 19);
        var manriqueRow = new Row(new RowAttribute[]{ manriqueId, manriqueNombre, manriqueApellido, manriqueEdad });

        // ---------------------------------------------------------------
        // Registros de la tabla curso
        // ---------------------------------------------------------------
        var inglesId = new RowAttribute("id", 1);
        var inglesNombre = new RowAttribute("nombre", "Ingles");
        var inglesDept = new RowAttribute("departamento", "Lenguas");
        var inglesCreditos = new RowAttribute("creditos", 2);
        var inglesRow = new Row(new RowAttribute[]{ inglesId, inglesNombre, inglesDept, inglesCreditos });

        var biofisicaId = new RowAttribute("id", 2);
        var biofisicaNombre = new RowAttribute("nombre", "Biofisica");
        var biofisicaDept = new RowAttribute("departamento", "CienciasExactas");
        var biofisicaCreditos = new RowAttribute("creditos", 4);
        var biofisicaRow = new Row(new RowAttribute[]{ biofisicaId, biofisicaNombre, biofisicaDept, biofisicaCreditos });

        var programacionId = new RowAttribute("id", 3);
        var programacionNombre = new RowAttribute("nombre", "Programacion");
        var programacionDept = new RowAttribute("departamento", "CienciasComputacionales");
        var programacionCreditos = new RowAttribute("creditos", 4);
        var programacionRow = new Row(new RowAttribute[]{ programacionId, programacionNombre, programacionDept, programacionCreditos });

        var servidoresId = new RowAttribute("id", 4);
        var servidoresNombre = new RowAttribute("nombre", "Servidores");
        var servidoresDept = new RowAttribute("departamento", "CienciasComputacionales");
        var servidoresCreditos = new RowAttribute("creditos", 4);
        var servidoresRow = new Row(new RowAttribute[]{ servidoresId, servidoresNombre, servidoresDept, servidoresCreditos });

        // ---------------------------------------------------------------
        // Registros de la tabla profesor
        // ---------------------------------------------------------------

        var marinId = new RowAttribute("id", 1);
        var marinNombre = new RowAttribute("nombre", "Marin");
        var marinApellido = new RowAttribute("apellido", "Pochet");
        var marinCiudad = new RowAttribute("ciudad", "LeonXIII");
        var marinRow = new Row(new RowAttribute[]{ marinId, marinNombre, marinApellido, marinCiudad });


        var melvinId = new RowAttribute("id", 2);
        var melvinNombre = new RowAttribute("nombre", "Melvin");
        var melvinApellido = new RowAttribute("apellido", "Quesada");
        var melvinCiudad = new RowAttribute("ciudad", "SantaBarbara");
        var melvinRow = new Row(new RowAttribute[]{ melvinId, melvinNombre, melvinApellido, melvinCiudad });


        var alejandroId = new RowAttribute("id", 4);
        var alejandroNombre = new RowAttribute("nombre", "Alejandro");
        var alejandroApellido = new RowAttribute("apellido", "Barrantes");
        var alejandroCiudad = new RowAttribute("ciudad", "SanRamon");
        var alejandroRow = new Row(new RowAttribute[]{ alejandroId, alejandroNombre, alejandroApellido, alejandroCiudad });


        var allanId = new RowAttribute("id", 11);
        var allanNombre = new RowAttribute("nombre", "Allan");
        var allanApellido = new RowAttribute("apellido", "Nunez");
        var allanCiudad = new RowAttribute("ciudad", "Jaco");
        var allanRow = new Row(new RowAttribute[]{ allanId, allanNombre, allanApellido, allanCiudad });
        var tables = new HashMap<Class<? extends Entity>, Set<Row>>();


        tables.put(Estudiante.class, new HashSet<>() {{
            add(ronaldRow);
            add(luciaRow);
            add(josePabloRow);
            add(manriqueRow);
        }});

        tables.put(Curso.class, new HashSet<>() {{
            add(servidoresRow);
            add(biofisicaRow);
            add(inglesRow);
            add(programacionRow);

        }});

        tables.put(Profesor.class, new HashSet<>() {{
            add(allanRow);
            add(marinRow);
            add(melvinRow);
            add(alejandroRow);
        }});


        return new SetDB(tables);
    }

    //-------------------------------inits------------------------------------------------------------------
    private static EstudianteDAO initEstudianteSetDAO(SetDB setDB) {
        return new EstudianteDAOImpl(setDB, Estudiante.class);
    }

    private static EstudianteService initEstudianteSetService(EstudianteDAO estudianteDAO) {
        return new EstudianteServiceImpl(estudianteDAO);
    }

    public static CursoDAO initCursoSetDAO(SetDB setDB){
        return new CursoDAOImpl(setDB, Curso.class);
    }

    public static CursoService initCursoService(CursoDAO cursoDAO){
        return new CursoServiceImpl(cursoDAO);
    }

    public static ProfesorDAO initProfesorSetDAO(SetDB setDB){
        return new ProfesorDAOImpl(setDB, Profesor.class);
    }

    public static ProfesorService initProfesorService(ProfesorDAO profesorDAO){
        return new ProfesorServiceImpl(profesorDAO);
    }

    //-------------------------------getters------------------------------------------------------------------
    public SetDB getSetDB() {
        return this.setDB;
    }

    public EstudianteDAO getEstudianteSetDAO() {
        return this.estudianteDAO;
    }

    public EstudianteService getEstudianteServiceSet() {
        return this.estudianteServiceSet;
    }

    public CursoDAO getCursoSetDAO(){
        return this.cursoDAO;
    }

    public CursoService getCursoService(){
        return this.cursoService;
    }

    public ProfesorDAO getProfersorSetDAO() {
        return this.profesorDAO;
    }

    public ProfesorService getProfesorService() {
        return this.profesorService;
    }
}
