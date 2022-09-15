package pl.cyber.trainess.demo.dto;

import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class Zadanie10PDRequest {
    private final Integer x;
    private final Integer p;
    @ConstructorProperties({"x","p"})

    public Zadanie10PDRequest(final Integer x, final Integer p) {
        this.x = x;
        this.p = p;
    }
}
