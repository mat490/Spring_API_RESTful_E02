package mx.unam.dgtic;

import mx.unam.dgtic.repository.AlumnoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestAlumnos {

    @Autowired
    private AlumnoRepository repositorioAlumno;


    @Test
    public void testAlumnos() {
        System.out.println("Test de alumnos");
        repositorioAlumno.findAll().forEach(System.out::println);
    }

    @Test
    public void testAlumnosPorNombre() {
        System.out.println("Test de alumnos por nombre");
        repositorioAlumno.findByNombre("Juan").forEach(System.out::println);
    }


}
