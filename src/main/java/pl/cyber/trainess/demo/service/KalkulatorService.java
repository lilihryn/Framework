package pl.cyber.trainess.demo.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cyber.trainess.demo.dto.IntegerRequest;
import pl.cyber.trainess.demo.dto.RownanieKwadratoweRequest;
import pl.cyber.trainess.demo.dto.StringRequest;
import pl.cyber.trainess.demo.dto.Zadanie10PDRequest;

import java.text.DecimalFormat;
import java.util.*;


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
        if (a != 0) {
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

    public String rownanieKwadratowe(final Integer a, final Integer b, final Integer c) {
        Double delta = 0.0;
        Double x1 = 0.0;
        Double x2 = 0.0;
        if (a == 0) {
            return "To nie jest rownianie kwadratowe";
        }
        delta = Double.valueOf(b * b - 4 * a * c);
        if (delta < 0) {
            return "Niema pierwiastkow";
        } else {
            if (delta == 0) {
                x1 = Double.valueOf(-b / (2 * a));
                return "Jeden pierwiastek: " + x1;
            } else {
                x1 = (-b - Math.sqrt(delta)) / (2 * a);
                x2 = (-b + Math.sqrt(delta)) / (2 * a);
                return "Dwa pierwiastki. Wynik -> x1: " + x1 + "x2" + x2;
            }
        }
    }

    public String rownanieKwadratowe(final RownanieKwadratoweRequest request) {
        Double delta = 0.0;
        Double x1 = 0.0;
        Double x2 = 0.0;
        Double a = request.getA();
        Double b = request.getB();
        Double c = request.getC();
        delta = b * b - 4 * a * c;
        if (delta == 0) {
            x1 = -b / (2 * a);
            return "Jeden pierwiastek: " + x1;
        } else if (delta < 0) {
            return "Niema pierwiastkow";
        } else {
            x1 = (-b - Math.sqrt(delta)) / (2 * a);
            x2 = (-b + Math.sqrt(delta)) / (2 * a);
            return "Dwa pierwiastki. Wynik -> x1: " + x1 + "x2" + x2;
        }

    }

    public String rownanieKwadratowe2miejsca(final RownanieKwadratoweRequest request) {
        Locale englishLocale = Locale.ENGLISH;//reprezentacja liczb, liter etc
        Locale polishLocale = Locale.forLanguageTag("pl-PL");//oczekujemy języka polskiego
        Locale.setDefault(polishLocale);
        DecimalFormat df = new DecimalFormat("#,###.00");
        //gdzie##liczby niewiadome, przecinek w zapisu jest spacijem w 1 000
        // ,zmiania kropke na przecinek, pokazuje dwie liczby po przecinku
        // moe być #,##.0# albo #.# albo
        Double delta = 0.0;
        Double x1 = 0.0;
        Double x2 = 0.0;
        Double a = request.getA();
        Double b = request.getB();
        Double c = request.getC();
        delta = b * b - 4 * a * c;
        if (delta == 0) {
            x1 = -b / (2 * a);
            return "Jeden pierwiastek: " + df.format(x1);
        } else if (delta < 0) {
            return "Niema pierwiastkow";
        } else {
            x1 = (-b - Math.sqrt(delta)) / (2 * a);
            x2 = (-b + Math.sqrt(delta)) / (2 * a);
            return "Dwa pierwiastki. Wynik -> x1: " + df.format(x1) + "x2" + df.format(x2);
        }
    }

    public String zadanie10a() {
        /*Integer y = 0;
        String result = "Program oblicza wartość funkciji y=3x dla x od 0 do 10.\n";//\n z nowej linii
        for (int x = 0; x <= 10; x++) {
            y = 3 * x;
            result = result + ("x= " + x + "\t" + "y=" + y + "\n");//\t tabulator, wielkość odstęmpu
        }
        return result;*/
        //to samo przez StringBuilder'a

        Integer y = 0;
        StringBuilder result = new StringBuilder("Program oblicza wartość funkciji y=3x dla x od 0 do 10.\n");//\n z nowej linii
        for (int x = 0; x <= 10; x++) {
            y = 3 * x;
            result.append("x= ").append(x).append("\t").append("y= ").append(y).append("\n");//\t tabulator, wielkość odstęmpu
        }
        return result.toString();
    }


    public String zadanie10b() {
        Integer x = 0;
        Integer y = 0;
        String result = "Program oblicza wartość funkciji y=3x dla x od 0 do 10." + "Za pomoocm pętli do-while\n";
        do {
            y = 3 * x;
            result += "x= " + x + "\t" + "y=" + y + "\n";
            x++;
        } while (x <= 10);
        return result;
    }

    public String zadanie10c() {
        Integer y = 0;
        Integer x = 0;
        String result = "Program oblicza wartość funkciji y=3x dla x od 0 do 10." + "Za pomoocm pętli while\n";
        while (x <= 10) {
            y = 3 * x;
            result += "x= " + x + "\t" + "y=" + y + "\n";
            x++;

        }
        return result;
    }

    public String zadanie11a() {
        Integer y = 1;
        Integer x = 1;
        Integer wynik = 1;

        StringBuilder result = new StringBuilder("Tabliczka mnożenia od 1 do 100\n");
        for (x = 1; x <= 10; x++) {
            for (y = 1; y <= 10; y++) {
                wynik = y * x;
                result.append(wynik).append("\t");
            }
            result.append("\n");
        }
        return result.toString();
    }

    public String zadanie11aa() {
        Integer n = 10;
        String result = "Tabliczka mnożenia od 1 do 100\n\n";
        for (int wiersz = 1; wiersz <= n; wiersz++) {
            for (int kolumna = 1; kolumna <= n; kolumna++) {
                result += wiersz * kolumna;
                result += "\t";
            }
            result += "\n";
        }
        return result;
    }

    public String zadanie11ab() {
        Integer w = 20;
        Integer k = 15;
        String result = "Tabliczka mnożenia 15 na 20 \n\n";
        for (int wiersz = 1; wiersz <= w; wiersz++) {
            for (int kolumna = 1; kolumna <= k; kolumna++) {
                result += wiersz * kolumna;
                result += "\t";
            }
            result += "\n";
        }
        return result;
    }

    public String zadanie11b() {
        Integer kolumna = 1;
        Integer wiersz = 1;
        Integer k = 10;
        Integer w = 20;
        String result = "Tabliczka mnożenia z pomocą do while\n";

        do {
            kolumna = 1;
            do {
                result += kolumna * wiersz;
                result += "\t";
                kolumna++;
            } while (kolumna <= k);
            result += "\n";
            wiersz++;
        } while (wiersz <= w);
        return result;
    }

    public String zadanie11c() {
        Integer kolumna = 1;
        Integer wiersz = 1;
        Integer k = 10;
        Integer w = 20;
        StringBuilder result = new StringBuilder("Tabliczka mnozenia while\n");
        while (wiersz <= w) {
            kolumna = 1;
            while (kolumna <= k) {
                result.append(wiersz * kolumna).append("\t");
                kolumna++;
            }
            result.append("\n");
            wiersz++;
        }
        return result.toString();
    }

    public String wartoscRowniania(final double a, final double b, final double c) {
        Locale polishLocale = Locale.forLanguageTag("pl-PL");//oczekujemy języka polskiego
        Locale.setDefault(polishLocale);
        DecimalFormat df = new DecimalFormat("#,##.##");
        double x = 0.0;
        if (a == 0) {
            throw new RuntimeException("a nie może byc nulem");

        } else {
            x = (c - b) / a;
        }
        return "Przy a:" + a + "b: " + b + "c: " + c + "x= " + x;
    }

    public String zbiorN() {

        List<Integer> zbiorN = new ArrayList<>();
        Random random = new Random();
        Integer min = 100;
        Integer max = 0;
        Integer suma = 0;
        Double srednia = 0.0;
        for (int i = 0; i < 5; i++) {
            zbiorN.add(random.nextInt(100));
        }
        for (Integer ele : zbiorN) {

            suma += ele;

            if (ele < min) {
                min = ele;
            }

            if (ele > max) {
                max = ele;
            }
        }

        srednia = (double) suma / zbiorN.size();
        return "Dla przdzialu: " + zbiorN + "Maksymalna liczba jest: " + max + ", " +
                "minimalana liczba jest:  " + min + ",srednia liczba przedzialu jest: " + srednia;
    }


    public String wartoscRowniania2(final double a, final double b, final double c) {
        Locale polishLocale = Locale.forLanguageTag("pl-PL");//oczekujemy języka polskiego
        Locale.setDefault(polishLocale);
        DecimalFormat df = new DecimalFormat("#,##.##");
        double x = 0.0;
        if (a == 0) {
            throw new RuntimeException("a nie może byc nulem");

        } else {
            x = (c - b) / a;
        }
        return "Przy a:" + df.format(a) + "b: " + df.format(b) + "c: " + df.format(c) + "x= " + df.format(x);
    }

    public String zadanie10PD(Integer x, final Integer p) {
        Integer y = 0;
        String result = "";
        do {
            y = 3 * x;
            result += "x = " + x + "\t " + "y = " + y + "\n";
            x++;
        } while (x <= p);
        return result;
    }

    public String zadanie10PD1(final Zadanie10PDRequest request) {
        Integer y = 0;
        StringBuilder result = new StringBuilder();
        Integer x = request.getX();
        Integer p = request.getP();
        if (x < p) {
            throw new RuntimeException("x nie może byc mniejszy za p");
        } else {
            for (int x1 = x; x1 < p; x1++) {
                y = 3 * x1;
                result.append("x= ").append(x1).append("\t").append("y= ").append(y).append("\n");

            }
            return String.valueOf(result);
        }
    }

    public String zadanie12f(final IntegerRequest request) {
        Random random = new Random();
        Integer iloscLosowan = request.getA();
        List<Integer> listaLiczb = new ArrayList<>();
        Integer min = 100;
        Integer max = 0;
        Integer suma = 0;
        Double srednia = 0.0;

        for (int i = 0; i < iloscLosowan; i++) {
            listaLiczb.add(random.nextInt(100));
        }
       /* z pomocą for-a
        for (int i = 0; i < listaLiczb.size(); i++) {
            Integer element = listaLiczb.get(i);
            suma += element;
            if (element < min) {
                min = element;
            }
            if (element > max) {
                max = element;
            }
        }*/

        //z pomocą foreach
        for (Integer element :
                listaLiczb) {
            suma += element;
            if (element < min) {
                min = element;
            }
            if (element > max) {
                max = element;
            }
        }
        srednia = (double)suma / (listaLiczb.size());

        return "Dla listy: "+listaLiczb+"min: "+min+"max: "+max+",natomiast średnia: "+srednia;
    }
}