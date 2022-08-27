package pl.cyber.trainess.demo.dto;

import lombok.Builder;
import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter//dodawanie do klasy metod getter
public class ImieDTO {
    private final String imie;
    @Builder//pomaga utworzyć objekt bez wypelniania konstruktora
    @ConstructorProperties({"imie"})//pomaga w budowaniu struktury pliku wynikowego JSON

    public ImieDTO(final String imie) {
        this.imie = imie;
    }
}
