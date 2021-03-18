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
        var matematicasId = new RowAttribute("id", 5);
        var matematicasNombre = new RowAttribute("nombre", "Matematicas");
        var matematicasDept = new RowAttribute("departamento", "Ciencias Exactas");
        var matematicasCreditos = new RowAttribute("creditos", 4);
        var matematicasRow = new Row(new RowAttribute[]{ matematicasId, matematicasNombre, matematicasDept, matematicasCreditos });

        var espanolId = new RowAttribute("id", 10);
        var espanolNombre = new RowAttribute("nombre", "Espanol");
        var espanolDept = new RowAttribute("departamento", "Lenguajes");
        var espanolCreditos = new RowAttribute("creditos", 3);
        var espanolRow = new Row(new RowAttribute[]{ espanolId, espanolNombre, espanolDept, espanolCreditos });

        var civicaId = new RowAttribute("id", 1);
        var civicaNombre = new RowAttribute("nombre", "Civica");
        var civicaDept = new RowAttribute("departamento", "Ciencias Sociales");
        var civicaCreditos = new RowAttribute("creditos", 1);
        var civicaRow = new Row(new RowAttribute[]{ civicaId, civicaNombre, civicaDept, civicaCreditos });

        var quimicaId = new RowAttribute("id", 4);
        var quimicaNombre = new RowAttribute("nombre", "Quimica");
        var quimicaDept = new RowAttribute("departamento", "Ciencias Exactas");
        var quimicaCreditos = new RowAttribute("creditos", 4);
        var quimicaRow = new Row(new RowAttribute[]{ quimicaId, quimicaNombre, quimicaDept, quimicaCreditos });

        // ---------------------------------------------------------------
        // Registros de la tabla profesor
        // ---------------------------------------------------------------

        var rogelioId = new RowAttribute("id", 1);
        var rogelioNombre = new RowAttribute("nombre", "Rogelio");
        var rogelioApellido = new RowAttribute("apellido", "Prendas");
        var rogelioCiudad = new RowAttribute("ciudad", "Heredia");
        var rogelioRow = new Row(new RowAttribute[]{ rogelioId, rogelioNombre, rogelioApellido, rogelioCiudad });


        var lorenaId = new RowAttribute("id", 2);
        var lorenaNombre = new RowAttribute("nombre", "Lorena");
        var lorenaApellido = new RowAttribute("apellido", "Benavides");
        var lorenaCiudad = new RowAttribute("ciudad", "Alajuela");
        var lorenaRow = new Row(new RowAttribute[]{ lorenaId, lorenaNombre, lorenaApellido, lorenaCiudad });


        var belizaId = new RowAttribute("id", 4);
        var belizaNombre = new RowAttribute("nombre", "Beliza");
        var belizaApellido = new RowAttribute("apellido", "Cascante");
        var belizaCiudad = new RowAttribute("ciudad", "San Carlos");
        var belizaRow = new Row(new RowAttribute[]{ belizaId, belizaNombre, belizaApellido, belizaCiudad });


        var carmenId = new RowAttribute("id", 11);
        var carmenNombre = new RowAttribute("nombre", "Carmen");
        var carmenApellido = new RowAttribute("apellido", "Prendas");
        var carmenCiudad = new RowAttribute("ciudad", "Puntarenas");
        var carmenRow = new Row(new RowAttribute[]{ carmenId, carmenNombre, carmenApellido, carmenCiudad });
        var tables = new HashMap<Class<? extends Entity>, Set<Row>>();


        tables.put(Estudiante.class, new HashSet<>() {{
            add(juanRow);
            add(mariaRow);
            add(pedroRow);
            add(raquelRow);
        }});

        tables.put(Curso.class, new HashSet<>() {{
            add(quimicaRow);
            add(espanolRow);
            add(matematicasRow);
            add(civicaRow);

        }});

        tables.put(Profesor.class, new HashSet<>() {{
            add(carmenRow);
            add(rogelioRow);
            add(lorenaRow);
            add(belizaRow);
        }});


        return new SetDB(tables);
    }

    private static EstudianteDAO initEstudianteSetDAO(SetDB setDB) {
        return new EstudianteDAOImpl(setDB, Estudiante.class);
    }

    private static EstudianteService initEstudianteSetService(EstudianteDAO estudianteDAO) {
        return new EstudianteServiceImpl(estudianteDAO);
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

    public EstudianteDAO getEstudianteSetDAO() {
        return this.estudianteDAO;
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

    public ProfesorDAO getProfesorDAO(){return this.profesorDAO;}

    public ProfesorService getProfesorService(){return this.profesorService;}


}