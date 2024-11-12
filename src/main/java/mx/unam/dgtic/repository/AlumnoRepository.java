package mx.unam.dgtic.repository;


import mx.unam.dgtic.model.Alumno;
import mx.unam.dgtic.model.Estado;
import mx.unam.dgtic.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AlumnoRepository extends JpaRepository<Alumno, String> {
	List<Alumno> findByNombre(String nombre);
	List<Alumno> findByNombreNot(String nombre);

	//Contar
	long countByNombre(String nombre);
	long countByNombreNot(String nombre);

	List<Alumno> getByPaterno(String paterno);
	List<Alumno> getByEstatura(double estatura);
	List<Alumno> getByFnac(Date fnac);

	//Usar NULL
	List<Alumno> streamByPaternoIsNull();
	List<Alumno> streamByPaternoIsNotNull();

	long countByPaternoIsNull();
	long countByPaternoIsNotNull();

	//Combinar campos con AND / OR
	List<Alumno> queryByNombreAndPaterno(String nombre, String paterno);
	List<Alumno> queryByNombreOrPaterno(String nombre, String paterno);
	List<Alumno> queryByNombreOrPaternoNull(String nombre);
	List<Alumno> queryByNombreOrPaternoAndEstatura(String nombre, String paterno, double estatura);

	long countByNombreAndPaterno(String nombre, String paterno);
	long countByNombreOrPaterno(String nombre, String paterno);
	long countByNombreOrPaternoNull(String nombre);
	long countByNombreOrPaternoAndEstatura(String nombre, String paterno, double estatura);

	boolean existsByNombreAndPaterno(String nombre, String paterno);

	//Mayor que, Menor que
	List<Alumno> findByFnacBefore(Date fecha);
	List<Alumno> findByFnacAfter(Date fecha);

	List<Alumno> findByEstaturaLessThan(double estatura);
	List<Alumno> findByEstaturaLessThanEqual(double estatura);

	List<Alumno> findByEstaturaGreaterThan(double estatura);
	List<Alumno> findByEstaturaGreaterThanEqual(double estatura);

	//Patrones
	List<Alumno> findByPaternoStartingWith(String prefijo);
	List<Alumno> findByPaternoContaining(String contiene);
	List<Alumno> findByPaternoEndingWith(String prefijo);

	List<Alumno> findByNombreStartingWith(String prefijo);
	List<Alumno> findByNombreContaining(String contiene);
	List<Alumno> findByNombreEndingWith(String prefijo);

	//Consuta derivada para listar alumnos por estado
	List<Alumno> findByEstado(Estado estado);
	long countByEstado(Estado estado);

	List<Alumno> findByEstadoEstado(String estado);

	List<Alumno> findByEstadoAbreviatura(String estado);

	@Query("SELECT g FROM Alumno a JOIN a.grupos g WHERE a.matricula = :matricula")
	List<Grupo> findGruposByMatricula(@Param("matricula") String matricula);

	List<Alumno> buscarTodosConCalificaciones();


}
