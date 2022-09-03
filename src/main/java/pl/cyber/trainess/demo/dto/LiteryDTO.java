package pl.cyber.trainess.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder//adnotacja do zbudowania objektu w oparciu o konstruktoe klasy
@AllArgsConstructor//adnotacja dzięki której w naszej klasie pojawi się konstruktor ze wszystkimi parametrami
public class LiteryDTO {
    private String litera;
    private Integer ilosc;
}
