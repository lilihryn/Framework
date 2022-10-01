package pl.cyber.trainess.demo.dto;

import jdk.jshell.Snippet;
import lombok.Builder;
import lombok.Getter;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter

public class KontoBankoweDTO {
    private final String id;
    private final String imie;
    private final String nazwisko;
    private final BigDecimal saldo;
    private final LocalDateTime dataUr;
    private final String miasto;
    private final Integer nrKarty;

   @Builder
   @ConstructorProperties({"id","imie","nazwisko","saldo","dataUr","miasto","nrKarty"})
    public KontoBankoweDTO(final String id,
                           final String imie,
                           final String nazwisko,
                           final BigDecimal saldo,
                           final LocalDateTime dataUr,
                           final String miasto,
                           final Integer nrKarty) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.saldo = saldo;
        this.dataUr = dataUr;
        this.miasto = miasto;
        this.nrKarty = nrKarty;
    }
}
