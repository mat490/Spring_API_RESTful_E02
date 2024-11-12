package mx.unam.dgtic.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EstadoNoExisteException.class)
    public ResponseEntity<DetailsError> errorDeRestriccion(EstadoNoExisteException ex,
                                                           HttpServletRequest request ){
        DetailsError detailsError = new DetailsError();
        detailsError.setMensaje(ex.getMessage());
        detailsError.setDetalle("Error de catalogo Estado");
        detailsError.setStatusCode(HttpStatus.CONFLICT.toString());
        detailsError.setTimeStamp(LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(detailsError);


    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> errorFormatoCliente(HttpMessageNotReadableException ex){

        HashMap<String, String> detalles = new HashMap<>();
        detalles.put("mensaje", "El formato de los datos es incorrecto");
        detalles.put("detalle", ex.getMessage());
        detalles.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(detalles);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratamientoValidacion(MethodArgumentNotValidException ex){
        HashMap<String, Object> detalles = new HashMap<>();
        detalles.put("mensaje", "Error de validación de campos, favor de revisar");
        detalles.put("statusCode", ex.getStatusCode());
        detalles.put("timestamp", LocalDateTime.now().toString());

        HashMap<String, String> detalleCampos = new HashMap<>();
        int i = 0;
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()){
            detalleCampos.put(fieldError.getField()+ i++, fieldError.getDefaultMessage());
        }

        detalles.put("errores", detalleCampos);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(detalles);

    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<DetailsError> recursoNoExiste(NoResourceFoundException ex,
                                                        HttpServletRequest request){

        DetailsError detailsError = new DetailsError();
        detailsError.setMensaje("Ese recurso no existe "+request.getRequestURI());
        detailsError.setStatusCode(HttpStatus.NOT_FOUND.toString());
        detailsError.setTimeStamp(LocalDateTime.now());
        detailsError.setDetalle(request.getRequestURI() + " - "+ request.getContextPath());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(detailsError);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<DetailsError> errorConversion(MethodArgumentTypeMismatchException ex){

        DetailsError detailsError = new DetailsError();
        detailsError.setMensaje(ex.getMessage());
        detailsError.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        detailsError.setTimeStamp(LocalDateTime.now());
        detailsError.setDetalle("Propiedad: "+ ex.getPropertyName() +
                " Tipo requerido: "+ex.getRequiredType());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(detailsError);
    }
}
