package pl.cyber.trainess.demo.service;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class KalkulatorService {

    //metody
    public Integer getDodawanie(final Integer a, final Integer b) {

        return a + b;
    }

    public Integer getOdejmowanie(final Integer a, final Integer b) {
        return a - b;
    }

    public Integer getMnozenie(final Integer a, final Integer b) {
        return a * b;
    }

    public Integer getDzielenie(final Integer a, final Integer b) throws RuntimeException {
        if (b == 0) {
            throw new RuntimeException("Nie może być 0");
        } else {

            return a / b;
        }
    }

    public Integer getReszteDzielenia(final Integer a, final Integer b) {
        if (b == 0) {
            throw new RuntimeException("LIczba musze byc inną niż 0");
        }
        return a % b;
    }

    public String getLiczbaPierwsza(final Integer a) {
        if (a < 2) {
            return "To nie jest liczba pierwsza";
        }
        for (int i = 2; i <= a / 2; i++) {
            if (a % i == 0) {
                return "To nie jest liczba pierwsza";
            }
        }
        return "To jest liczba pierwsza";
    }
/*
  public Integer getLiczbaPierwsza(final Integer liczbaA) {

        if (liczbaA == 0) {
            throw new RuntimeException("Liczba nie może być 0 ");
        } else if (liczbaA == +-1) {
            throw new RuntimeException("Liczba nie może być  +-1 ");
        } else if (liczbaA % 2 == 0) {
            throw new RuntimeException("Liczba nie może być parzysta ");
        } else if (liczbaA % liczbaA != 0 && liczbaA % 1 != 0) {
            throw new RuntimeException("Liczba nie jest pierwszą");
        }
        return liczbaA;

    }

*/
}