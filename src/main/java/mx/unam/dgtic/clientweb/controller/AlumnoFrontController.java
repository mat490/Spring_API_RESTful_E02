package mx.unam.dgtic.clientweb.controller;

import mx.unam.dgtic.clientweb.services.AlumnoFrontService;
import mx.unam.dgtic.clientweb.services.AlumnoWebClientService;
import mx.unam.dgtic.dto.AlumnoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AlumnoFrontController {
    @Autowired
    AlumnoFrontService service;
    @Autowired
    AlumnoWebClientService alumnoWebClientService;

    @GetMapping(path = "/front/alumnos/{matricula}")
    public String getAlumno(@PathVariable String matricula, Model model){
        AlumnoDto alumnoDto = service.getAlumnoByMatricula(matricula);
        model.addAttribute("alumno", alumnoDto);
        return "alumnodetalle";

    }

    @GetMapping(path = "/front/alumnos/")
    public String getAllAlumnos(Model model){
        model.addAttribute("alumnos", alumnoWebClientService.getAll());
        return "alumnos";
    }

    @GetMapping(path = "/front/alumnos/{matricula}/editar")
    public String getFormEditar(@PathVariable String matricula,
                                Model model){
        AlumnoDto alumnoDto = alumnoWebClientService.getAlumnoByMatricula(matricula);
        model.addAttribute("alumno", alumnoDto);
        return "formEditar";
    }

    @PutMapping(path = "/front/alumnos/editar")
    public String actualizarAlumno(@RequestBody AlumnoDto alumnoDto,
                                   Model model){
        AlumnoDto alumnoActualizado = alumnoWebClientService.actualizar(alumnoDto);
        model.addAttribute("alumno", alumnoActualizado);
        return "formEditar";
    }

}
