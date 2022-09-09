package pl.cyber.trainess.demo.service;

import org.springframework.stereotype.Service;
import pl.cyber.trainess.demo.dto.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BasicsService {

    private static String SPACJA = " ";//globalna zmianna

    public String getSklejenie(final StringRequest request) {
        //return request.getStringPierwszy() + request.getStringDrugi();
        //stringy sie konkatynują +

        var string = new StringBuilder();
        return string.append(request.getStringPierwszy()).append(SPACJA).append(request.getStringDrugi()).toString();//dodajemy spacje
    }

    public List<String> getWystapienieLiterWZdaniu(final OneStringRequest request) {
        List<LiteryDTO> wystapienia = new ArrayList<>();
        Set<String> litery = new HashSet<>();
        List<String> wynik = new ArrayList<>();
        String zdanie = request.getValue();//metoda get,zdanie z requestu
        for (int i = 0; i < zdanie.length(); i++) {//zdanie.length()-przechodi po dlugośći zdania
            String litera = String.valueOf(zdanie.charAt(i));
            if (litera.matches("[a-zA-Z]+")) {//sprawdzanie czy litera jest w ramach alfabetu
                if (wystapienia.size() == 0) {//sprawdzenie czy lista jest na zero
                    litery.add(litera.toLowerCase());
                    wystapienia.add(LiteryDTO.builder()
                            .litera(litera.toLowerCase())
                            .ilosc(1)
                            .build());
                    //wystapienia.add(new LiteryDTO(litera.toLowerCase(),ilosc:1));
                } else {
                    if (litery.contains(litera.toLowerCase())) {//czy lista litery zawiera juz dany element
                        for (LiteryDTO element : wystapienia
                        ) {
                            if (element.getLitera().equals(litera.toLowerCase())) {
                                element.setIlosc(element.getIlosc() + 1);
                            }
                        }
                    } else {
                        litery.add(litera.toLowerCase());
                        wystapienia.add(LiteryDTO.builder()
                                .litera(litera.toLowerCase())
                                .ilosc(1)
                                .build());

                    }
                }
            }
        }
        for (LiteryDTO element : wystapienia) {
            wynik.add(element.getLitera() + "-" + element.getIlosc());

        }
        wynik.sort(String::compareTo);//sortowanie listy wynik alfabetycznie(bo operujemy na stringach)
        return wynik;
    }

    public List<String> getWystapienieLiterWZdaniuMap(final OneStringRequest request) {
        Map<String, Integer> wystapienia = new HashMap<>();
        List<String> wynik = new ArrayList<>();
        String zdanie = request.getValue().toLowerCase();
        for (int i = 0; i < zdanie.length(); i++) {
            String litera = String.valueOf(zdanie.charAt(i));
            if (litera.matches("[a-zA-Z]")) {
                if (wystapienia.containsKey(litera)) {
                    wystapienia.put(litera, wystapienia.get(litera) + 1);//zwiększenie licznyka na 1
                } else {
                    wystapienia.put(litera, 1);
                }
            }
        }
        for (String element : wystapienia.keySet()//zwraca lista kluczy(litera i pozycja
        ) {
            wynik.add(element + "" + wystapienia.get(element));

        }
        return wynik;
    }

    public String getListeUjemnychSumaInt(final ListaRequest request) {

        List<Integer> listaUjemnych = new ArrayList<>();

        List<Integer> podaneLiczby = Stream.of(request.getLiczby().split(",")).map(Integer::valueOf).collect(Collectors.toList());
        Integer sumaInt = 0;
        for (Integer liczba : podaneLiczby) {
            if (liczba > 0) {
                sumaInt = sumaInt + liczba;
            } else {
                listaUjemnych.add(liczba);
            }
        }
        return listaUjemnych + "Suma liczb dodatnich" + sumaInt;
    }

    public String sumaLiczbPomiedzy(final Integer a, final Integer b) {
        if (a > b) {
            throw new RuntimeException("a musze być mniejsze b");
        }
        Integer wynik = 0;
        for (int i = a; i <= b; i++) {
            wynik += i;
        }
        return "Wynik dodawania liczb pomiedzy a:" + a + "oraz b: " + b + "to: " + wynik;
    }

    public String zadanie8(final IntegerListRequest request) {
        List<Integer> listaUjemnych = new ArrayList<>();
        Integer sumaDodatnich = 0;
        for (Integer element : request.getIntList()
        ) {
            if (element < 0) {
                listaUjemnych.add(element);
            } else {
                sumaDodatnich += element;
            }
        }
        return listaUjemnych+"oraz suma dodatnich"+sumaDodatnich;
    }
}
