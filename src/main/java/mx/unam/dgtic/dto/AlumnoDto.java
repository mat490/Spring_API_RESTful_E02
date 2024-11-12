package mx.unam.dgtic.dto;

import jakarta.validation.constraints.*;

import java.util.Objects;

public class AlumnoDto {
    @NotNull(message = "La matricula no debe ser nulo")
    @NotBlank(message = "La matricula no debe ser un texto en blanco")
    @NotEmpty(message = "La matricula no puede ser una cadena vacía")
    private String matricula;

    @NotBlank(message = "El nombre no debe ser un texto en blanco ")
    private String nombre;

    @NotBlank(message = "El apellido paterno no debe ser un texto en blanco ")
    private String paterno;


    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "El formato de la fecha debe ser yyyy-MM-dd ejemplo: 2000-05-25")
    @NotBlank(message = "La fecha de nacimiento no debe ser un texto en blanco")
    private String fnac;

    @Positive(message = "La estatura debe ser un valor positivo")
    @DecimalMin(value = "0.3", message = "La estatura no debe ser menor a 0.3 metros")
    @DecimalMax(value = "3.1", message = "La estatura no debe sobrepasar los 3.1 metros")
    private double estatura;

    @NotNull(message = "Debes proporcionar un estado")
    @NotBlank(message = "El estado no puede ser una cadena de texto vacía")
    private String estado;

    public AlumnoDto() {
    }

    public AlumnoDto(String matricula, String nombre, String paterno, String fnac, double estatura, String estado) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.paterno = paterno;
        this.fnac = fnac;
        this.estatura = estatura;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "AlumnoDto{" +
                "matricula='" + matricula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", paterno='" + paterno + '\'' +
                ", fnac='" + fnac + '\'' +
                ", estatura=" + estatura +
                ", estado='" + estado + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlumnoDto alumnoDto = (AlumnoDto) o;
        return Double.compare(getEstatura(), alumnoDto.getEstatura()) == 0 && Objects.equals(getMatricula(),
                alumnoDto.getMatricula()) && Objects.equals(getNombre(),
                alumnoDto.getNombre()) && Objects.equals(getPaterno(),
                alumnoDto.getPaterno()) && Objects.equals(getFnac(), alumnoDto.getFnac()) && Objects.equals(getEstado(),
                alumnoDto.getEstado());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMatricula(), getNombre(), getPaterno(), getFnac(), getEstatura(), getEstado());
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getFnac() {
        return fnac;
    }

    public void setFnac(String fnac) {
        this.fnac = fnac;
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
