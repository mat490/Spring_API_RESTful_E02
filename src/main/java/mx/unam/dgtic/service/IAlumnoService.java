package mx.unam.dgtic.service;

import mx.unam.dgtic.model.Alumno;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IAlumnoService {
    List<Alumno> getAlumnoList();
    Alumno getAlumnoById(String matricula);
    Alumno updateAlumno(Alumno alumno);
    List<Alumno> findAlumnosByEstado(String estado);
    Alumno createAlumno(Alumno alumno);
    boolean deleteAlumno(String matricula);


}
