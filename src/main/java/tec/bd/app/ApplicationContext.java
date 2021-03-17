package tec.bd.app;

import tec.bd.app.dao.*;
import tec.bd.app.database.set.Row;
import tec.bd.app.database.set.RowAttribute;
import tec.bd.app.database.set.SetDB;
import tec.bd.app.domain.Curso;
import tec.bd.app.domain.Entity;
import tec.bd.app.domain.Estudiante;
import tec.bd.app.service.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ApplicationContext {

    private SetDB setDB;
    private EstudianteDAOSet estudianteDAOSet;
    private EstudianteService estudianteServiceSet;

    private CursoDAO cursoDAO;
    private CursoService cursoService;

    private ProfesorSetDAO profesorSetDAO;
    private ProfesorService profesorService;


    private ApplicationContext() {

    }

    public static ApplicationContext init() {
        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.setDB = initSetDB();
        applicationContext.estudianteDAOSet = initEstudianteSetDAO(applicationContext.setDB);
        applicationContext.estudianteServiceSet = initEstudianteSetService(applicationContext.estudianteDAOSet);

        applicationContext.cursoDAO = initCursoSetDAO(applicationContext.setDB);
        applicationContext.cursoService = initCursoService(applicationContext.cursoDAO);



        return applicationContext;
    }

    private static SetDB initSetDB() {
        // Registros de la tabla estudiante
        var juanId = new RowAttribute("id", 1);
        var juanNombre = new RowAttribute("nombre", "Juan");
        var juanApellido = new RowAttribute("apellido", "Perez");
        var juanEdad = new RowAttribute("edad", 20);
        var juanRow = new Row(new RowAttribute[]{ juanId, juanNombre, juanApellido, juanEdad });

        var mariaId = new RowAttribute("id", 3);
        var mariaNombre = new RowAttribute("nombre", "Maria");
        var mariaApellido = new RowAttribute("apellido", "Rojas");
        var mariaEdad = new RowAttribute("edad", 21);
        var mariaRow = new Row(new RowAttribute[]{ mariaId, mariaNombre, mariaApellido, mariaEdad });

        var pedroId = new RowAttribute("id", 2);
        var pedroNombre = new RowAttribute("nombre", "Pedro");
        var pedroApellido = new RowAttribute("apellido", "Infante");
        var pedroEdad = new RowAttribute("edad", 23);
        var pedroRow = new Row(new RowAttribute[]{ pedroId, pedroNombre, pedroApellido, pedroEdad });

        var raquelId = new RowAttribute("id", 10);
        var raquelNombre = new RowAttribute("nombre", "Raquel");
        var raquelApellido = new RowAttribute("apellido", "Rojas");
        var raquelEdad = new RowAttribute("edad", 25);
        var raquelRow = new Row(new RowAttribute[]{ raquelId, raquelNombre, raquelApellido, raquelEdad });

        // ---------------------------------------------------------------
        // Registros de la tabla curso
        // ---------------------------------------------------------------


        // ---------------------------------------------------------------
        // Registros de la tabla profesor
        // ---------------------------------------------------------------



        var tables = new HashMap<Class<? extends Entity>, Set<Row>>();
        tables.put(Estudiante.class, new HashSet<>() {{
            add(juanRow);
            add(mariaRow);
            add(pedroRow);
            add(raquelRow);
        }});

        // Agregar las filas de curso y estudiante a tables
        // tables.put(Curso.class, new HashSet<>() {{ ... }}
        // tables.put(Profesor.class, new HashSet<>() {{ ... }}

        return new SetDB(tables);
    }

    private static EstudianteDAOSet initEstudianteSetDAO(SetDB setDB) {
        return new EstudianteDAOSetImpl(setDB, Estudiante.class);
    }

    private static EstudianteService initEstudianteSetService(EstudianteDAOSet estudianteDAOSet) {
        return new EstudianteServiceImpl(estudianteDAOSet);
    }

    private static CursoDAO initCursoSetDAO(SetDB setDB){
        return new CursoDAOImpl(setDB, Curso.class);
    }

    private static CursoService initCursoService(CursoDAO cursoDAO){
        return new CursoServiceImpl(cursoDAO);
    }


    public SetDB getSetDB() {
        return this.setDB;
    }

    public EstudianteDAOSet getEstudianteSetDAO() {
        return this.estudianteDAOSet;
    }

    public EstudianteService getEstudianteServiceSet() {
        return this.estudianteServiceSet;
    }

    public CursoDAO getCursoSetDAO(){
        return this.cursoDAO;
    }

    public  CursoService getCursoService(){
        return this.cursoService;
    }

    public ProfesorSetDAO getProfesorDAO(){return this.profesorSetDAO;}

    public ProfesorService getProfesorService(){return this.profesorService;}


}