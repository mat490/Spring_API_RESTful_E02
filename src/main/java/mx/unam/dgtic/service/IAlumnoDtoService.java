package mx.unam.dgtic.service;

import mx.unam.dgtic.dto.AlumnoDto;
import mx.unam.dgtic.exception.EstadoNoExisteException;
import mx.unam.dgtic.model.Alumno;

import java.text.ParseException;
import java.util.List;

public interface IAlumnoDtoService {
    List<AlumnoDto> getAlumnoList();
    List<Alumno> getAlumnosPageable(int page,
                                    int size,
                                    String dirSort,
                                    String sort);
    AlumnoDto getAlumnoById(String matricula);
    AlumnoDto updateAlumno(AlumnoDto alumno) throws ParseException, EstadoNoExisteException;
    List<AlumnoDto> findAlumnosByEstado(String estado);
    AlumnoDto createAlumno(AlumnoDto alumno) throws ParseException, EstadoNoExisteException;
    boolean deleteAlumno(String matricula);
}
