package pl.cyber.trainess.demo.endpoint;

import org.springframework.web.bind.annotation.*;
import pl.cyber.trainess.demo.dto.ImieDTO;
import pl.cyber.trainess.demo.dto.Person;
import pl.cyber.trainess.demo.dto.PersonDTO;
import pl.cyber.trainess.demo.dto.PersonRequest;

@RestController//adnotacja mówi dla servera Spring że w tym miejscu bedą funkcialnośći REST API
@RequestMapping("/v1/first")
public class PierwszyController {
    //HTTP metoda GET-metoda do pobierania informacji z servera oraz wyslania ich do zewnętrznego systemu
    @GetMapping("/{imie}")
    public ImieDTO getImie(@PathVariable final String imie){
        return ImieDTO.builder()
                .imie(imie)
                .build();
    }

}
