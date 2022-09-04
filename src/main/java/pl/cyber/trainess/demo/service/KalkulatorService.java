package pl.cyber.trainess.demo.service;


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

    public boolean getPodzielnoscLiczb(final Integer a, final Integer b) {

        if (b == 0) {
            throw new RuntimeException("Nie może byc 0");
        } else return a % b == 0;
    }

    public boolean getCzyJestDzielnikiem(final Integer a, final Integer b) {//variant 2
        if (b == 0) {
            throw new RuntimeException("Nie może byc 0");
        } else return a % b == 0;
    }

    public Integer getSumaLiczbPrzedzialu(final Integer a, final Integer b) {
        Integer sumaLiczb = 0;
        for (int i = a; i <= b; i++) {
            sumaLiczb += i;
        }
        return sumaLiczb;
    }

    public String getPierwiastekRownianiaKwadratowego(final Double a, final Double b, final Double c) {

        var delta = 0;
        var x1 = 0;
        var x2 = 0;
        if (a > 0) {
            delta = (int) (Math.pow(b, 2) - 4 * a * c);
            if (delta > 0) {
                x1 = (int) ((-b - Math.sqrt(delta)) / (2 * a));
                x2 = (int) ((-b + Math.sqrt(delta)) / (2 * a));
                return "Dwa pierwiastki. Wynik -> x1: " + String.valueOf(x1) + "x2: " + String.valueOf(x2);

            } else if (delta == 0) {
                x1 = (int) (-b / (2 * a));
                return "Jeden pierwiastek: " + String.valueOf(x1);
            } else if (delta < 0) {
                return "Niema pierwiastkow";
            }
        }
        return "To nie jest rownianie kwadratowe";
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