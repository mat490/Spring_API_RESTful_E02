package mx.unam.dgtic.clientweb.services;

import mx.unam.dgtic.dto.AlumnoDto;
import mx.unam.dgtic.model.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class AlumnoWebClientService {
    @Autowired
    private WebClient webClient;

    public List<AlumnoDto> getAll(){
        Mono<List<AlumnoDto>> alumnosMono = webClient.get()
                                                        .retrieve()
                                                        .bodyToFlux(AlumnoDto.class)
                                                        .collectList();
        return alumnosMono.block();
    }

    public AlumnoDto getAlumnoByMatricula(String matricula){
        Mono<AlumnoDto> alumnoDtoMono = webClient.get()
                .uri("/{matricula}", matricula)
                .retrieve()
                .bodyToMono(AlumnoDto.class);
        AlumnoDto alumno = alumnoDtoMono.block();
        return alumno;
    }

    public AlumnoDto actualizar(AlumnoDto alumnoDto){

        return webClient.put()
                .uri("/{matricula}", alumnoDto.getMatricula())
                .bodyValue(alumnoDto)
                .retrieve()
                .bodyToMono(AlumnoDto.class)
                .block();
    }
}
