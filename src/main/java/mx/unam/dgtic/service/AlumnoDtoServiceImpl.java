package mx.unam.dgtic.service;

import mx.unam.dgtic.dto.AlumnoDto;
import mx.unam.dgtic.exception.EstadoNoExisteException;
import mx.unam.dgtic.model.Alumno;
import mx.unam.dgtic.model.Estado;
import mx.unam.dgtic.repository.AlumnoRepository;
import mx.unam.dgtic.repository.CalificacionRepository;
import mx.unam.dgtic.repository.EstadoRepository;
import mx.unam.dgtic.repository.GrupoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlumnoDtoServiceImpl implements IAlumnoDtoService{
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AlumnoRepository alumnoRepository;

    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    CalificacionRepository calificacionRepository;

    @Autowired
    GrupoRepository grupoRepository;

    @Override
    public List<AlumnoDto> getAlumnoList() {
        List<Alumno> alumnos = alumnoRepository.findAll();
        return alumnos.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<Alumno> getAlumnosPageable(int page, int size,
                                              String dirSort, String sort) {

        PageRequest pageRequest = PageRequest.of(page, size,
                                                        Sort.Direction.fromString(dirSort), sort);
        Page<Alumno> pageResult = alumnoRepository.findAll(pageRequest);
        return pageResult.stream().toList();
    }

    @Override
    public AlumnoDto getAlumnoById(String matricula) {
        Optional<Alumno> alumno = alumnoRepository.findById(matricula);
        if (alumno.isPresent()){
            return convertToDto(alumno.get());
        }
        return null;
    }

    @Override
    public AlumnoDto updateAlumno(AlumnoDto alumnoDto) throws ParseException, EstadoNoExisteException {
        Alumno alumno = alumnoRepository.save(this.converToEntity(alumnoDto));
        return convertToDto(alumno);
    }

    @Override
    public List<AlumnoDto> findAlumnosByEstado(String estado) {
        if (estadoRepository.findByEstado(estado) == null){
            return  null;
        }
        List<Alumno> alumnos = alumnoRepository.findByEstadoEstado(estado);
        return alumnos.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public AlumnoDto createAlumno(AlumnoDto alumnoDto) throws ParseException, EstadoNoExisteException {
        Alumno alumno = alumnoRepository.save(converToEntity(alumnoDto));
        return convertToDto(alumno);
    }

    @Override
    public boolean deleteAlumno(String matricula) {
        Optional<Alumno> alumnoOptional = alumnoRepository.findById(matricula);
        if (alumnoOptional.isPresent()){
            alumnoRepository.deleteById(matricula);
            return true;
        }

        return false;
    }

    private AlumnoDto convertToDto(Alumno alumno){
        AlumnoDto alumnoDto = modelMapper.map(alumno, AlumnoDto.class);
        if (alumno.getEstado() != null) alumnoDto.setEstado(alumno.getEstado().getEstado());

        if (alumno.getFnac() != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fnacStr = dateFormat.format(alumno.getFnac());
            alumnoDto.setFnac(fnacStr);
        }

        return alumnoDto;
    }

    private Alumno converToEntity(AlumnoDto alumnoDto) throws ParseException, EstadoNoExisteException {
        Alumno alumno = modelMapper.map(alumnoDto, Alumno.class);
        if (alumnoDto.getEstado() != null && !alumnoDto.getEstado().isEmpty()
                && !alumnoDto.getEstado().isBlank()){

            Estado estado = estadoRepository.findByEstado(alumnoDto.getEstado());

            if (estado == null){
                throw new EstadoNoExisteException("El estado no existe");
            } else {
                alumno.setEstado(estado);
            }
        }
        if (alumnoDto.getFnac() != null && !alumnoDto.getFnac().isEmpty()
                && !alumnoDto.getFnac().isBlank()){
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fnacDate = dateFormat.parse(alumnoDto.getFnac());
            alumno.setFnac(fnacDate);

        } else {
            alumno.setFnac(new SimpleDateFormat("yyyy-MM-dd").parse("1900-01-01"));
        }
        return alumno;

    }
}
