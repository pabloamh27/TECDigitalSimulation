package tec.bd.app.dao;

import tec.bd.app.database.set.RowAttribute;
import tec.bd.app.database.set.SetDB;
import tec.bd.app.database.set.Row;
import tec.bd.app.domain.Estudiante;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;


public class EstudianteDAOImpl extends GenericDAOImpl<Estudiante, Integer> implements EstudianteDAO {

    public EstudianteDAOImpl(SetDB setDB, Class<Estudiante> clazz) {
        super(setDB, clazz);
    }

    @Override
    public List<Estudiante> findByLastName(String lastName) {
        var listaEstudiantes = this.table.stream().map(this::rowToEntity).collect(Collectors.toList());
        ArrayList<Estudiante> listaApellidos = new ArrayList<>();
        for(int i = 0; i< listaEstudiantes.size(); i++){
            Estudiante actual = listaEstudiantes.get(i);
            if(actual.getApellido().equals(lastName)){
                listaApellidos.add(actual);
            }
        }
        return listaApellidos.stream().collect(Collectors.toList());
    }


    @Override
    public List<Estudiante> findAllSortByLastName() {
        var listaEstudiantes = this.table.stream().map(this::rowToEntity).collect(Collectors.toList());
        ArrayList<String> listaApellidos = new ArrayList<>();
        for(int i = 0; i<listaEstudiantes.size(); i++){
            String actual = listaEstudiantes.get(i).getApellido();
            listaApellidos.add(actual);
        }
        Collections.sort(listaApellidos);

        ArrayList<Estudiante> listaEstudianteOrdenados = new ArrayList<>();
        for( String i : listaApellidos){
            for(Estudiante actual : listaEstudiantes){
                if(actual.getApellido().equals(i)){
                    listaEstudianteOrdenados.add(actual);
                    break;
                }
            }
        }

        return listaEstudianteOrdenados.stream().collect(Collectors.toList());
    }

    @Override
    protected Estudiante rowToEntity(Row row) {
        // conversiones de Row a Estudiante
        var carne = row.intAttributeValue("id");
        var nombre = row.stringAttributeValue("nombre");
        var apellido = row.stringAttributeValue("apellido");
        var edad = row.intAttributeValue("edad");
        return new Estudiante(carne, nombre, apellido, edad);
    }

    @Override
    protected Row entityToRow(Estudiante e) {
        // conversiones de Estudiante a Row
        return new Row(new RowAttribute [] {
                new RowAttribute("id", e.getCarne()),
                new RowAttribute("nombre", e.getNombre()),
                new RowAttribute("apellido", e.getApellido()),
                new RowAttribute("edad", e.getEdad()) });
    }

}
