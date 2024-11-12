package mx.unam.dgtic;

import mx.unam.dgtic.model.Alumno;
import mx.unam.dgtic.repository.AlumnoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;

@SpringBootTest
class M1000ApplicationTests {

	final String USER = "Jesús Hernández Cabrera";

	@Autowired
	AlumnoRepository repositorioAlumno;


	@Test
	void findAll(){
		ArrayList<Alumno>  als =(ArrayList<Alumno>) repositorioAlumno.findAll();
		//print all
		als.forEach(System.out::println);
	}





}
