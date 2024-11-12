package mx.unam.dgtic;

import mx.unam.dgtic.repository.CalificacionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCalificaciones {
    @Autowired
    private CalificacionRepository calificacionRepository;

    @Test
    public void findAllCals(){
        System.out.println("Test de Calificaciones");
        calificacionRepository.findAll().forEach(System.out::println);


    }
}
