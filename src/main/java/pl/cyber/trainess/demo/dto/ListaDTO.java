package pl.cyber.trainess.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class ListaDTO {
    private Integer liczba;
    private Integer sumaInt;
}
