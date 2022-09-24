package pl.cyber.trainess.demo.dto;

import lombok.Builder;
import lombok.Getter;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
public class BankomatDTO {
    private final String id;
    private final String name;
    private final BigDecimal saldo;
    private final String miasto;
    private final String ulica;
    private final Boolean czyAktywny;
    @Builder
    @ConstructorProperties({"id","name","saldo","miasto","ulica","czyAktywny"})

    public BankomatDTO(final String id, final String name, final BigDecimal saldo, final String miasto, final String ulica, final Boolean czyAktywny) {
        this.id = id;
        this.name = name;
        this.saldo = saldo;
        this.miasto = miasto;
        this.ulica = ulica;
        this.czyAktywny = Objects.isNull(czyAktywny)?false:czyAktywny;
    }
}
