package pl.cyber.trainess.demo.dto;


import lombok.Getter;

@Getter
public class IntegerRequest {

    final Integer a;

    public IntegerRequest(final Integer a) {
        this.a = a;
    }
}
