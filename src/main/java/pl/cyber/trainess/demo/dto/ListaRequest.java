package pl.cyber.trainess.demo.dto;

import groovyjarjarantlr4.v4.runtime.misc.IntegerList;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.beans.ConstructorProperties;


@Slf4j
@Getter
public class ListaRequest {

    private String Liczby;
    @ConstructorProperties({"liczby"})

    public ListaRequest(final String liczby) {
        Liczby = liczby;
    }
}