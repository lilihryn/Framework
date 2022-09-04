package pl.cyber.trainess.demo.dto;

import groovyjarjarantlr4.v4.runtime.misc.IntegerList;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.beans.ConstructorProperties;


@Slf4j
@Getter
public class ListaRequest {
  private IntegerList Liczby;
  @ConstructorProperties("{liczby}")

    public ListaRequest(final IntegerList liczby) {
        Liczby = liczby;
    }
}
