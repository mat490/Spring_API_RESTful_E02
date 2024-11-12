package mx.unam.dgtic.service;

import mx.unam.dgtic.model.Alumno;
import mx.unam.dgtic.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImpl implements IAlumnoService{
    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public List<Alumno> getAlumnoList() {
        return alumnoRepository.findAll();
    }

    @Override
    public Alumno getAlumnoById(String matricula) {
        Optional<Alumno> alumno = alumnoRepository.findById(matricula);
        return alumno.orElse(null);
    }

    @Override
    public Alumno updateAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Override
    public List<Alumno> findAlumnosByEstado(String estado) {
        return alumnoRepository.findByEstadoEstado(estado);
    }

    @Override
    public Alumno createAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Override
    public boolean deleteAlumno(String matricula) {
        Optional<Alumno> alumno = alumnoRepository.findById(matricula);
        if (alumno.isPresent()){
            alumnoRepository.deleteById(matricula);
            return true;
        }
        return false;
    }


}
