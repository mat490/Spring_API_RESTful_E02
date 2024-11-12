package mx.unam.dgtic.controller;

import com.ctc.wstx.shaded.msv_core.util.Uri;
import mx.unam.dgtic.model.Alumno;
import mx.unam.dgtic.repository.AlumnoRepository;
import mx.unam.dgtic.service.IAlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/alumnos", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlumnoController {
    @Autowired
    private IAlumnoService alumnoService;

    @GetMapping(path = "/")
    public ResponseEntity<List<Alumno>> getAll(){
        return ResponseEntity.ok(alumnoService.getAlumnoList());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Alumno> getById(@PathVariable String id){
        Alumno alumno = alumnoService.getAlumnoById(id);
        if (alumno != null)
        {
            return ResponseEntity.ok(alumno);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable String id){
        if (alumnoService.deleteAlumno(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/")
    public ResponseEntity<Alumno> createAlumno(@RequestBody Alumno alumno) throws URISyntaxException {
        Alumno alumnoNuevo = alumnoService.createAlumno(alumno);
        URI location = new URI("/api/alumnos/"+alumnoNuevo.getMatricula());
        return ResponseEntity.created(location).body(alumnoNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alumno> updateAlumno(@PathVariable String id, @RequestBody Alumno alumno)
            throws URISyntaxException {
        Alumno alumnoDb = alumnoService.getAlumnoById(id);

        if (alumnoDb != null){
            return ResponseEntity.ok(alumnoService.updateAlumno(alumno));
        }
        Alumno alumnoNuevo = alumnoService.createAlumno(alumno);
        URI location = new URI("/api/alumnos/"+alumnoNuevo.getMatricula());
        return ResponseEntity.created(location).body(alumnoNuevo);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Alumno> actualizacionParcial(@PathVariable String id,
                                                       @RequestBody Alumno alumno){
        Alumno alumnoToUpdate = alumnoService.getAlumnoById(id);
        if (alumnoToUpdate == null){
            return ResponseEntity.notFound().build();
        }
        if (alumno != null){
            if (alumno.getNombre() != null) alumnoToUpdate.setNombre(alumno.getNombre());
            if (alumno.getPaterno() != null) alumnoToUpdate.setPaterno(alumno.getPaterno());
            if (alumno.getFnac() != null) alumnoToUpdate.setFnac(alumno.getFnac());
            if (alumno.getEstatura() != 0.0 ) alumnoToUpdate.setEstatura(alumno.getEstatura());
            return ResponseEntity.ok(alumnoService.updateAlumno(alumnoToUpdate));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping(path = "/estados/{edo}")
    public ResponseEntity<List<Alumno>> getByEstado(@PathVariable String edo){

        return ResponseEntity.ok(alumnoService.findAlumnosByEstado(edo));
    }

}
