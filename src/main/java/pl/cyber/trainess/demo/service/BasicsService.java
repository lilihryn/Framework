package pl.cyber.trainess.demo.service;

import org.springframework.stereotype.Service;
import pl.cyber.trainess.demo.dto.OneStringRequest;
import pl.cyber.trainess.demo.dto.StringRequest;
import pl.cyber.trainess.demo.dto.LiteryDTO;

import java.util.*;

@Service
public class BasicsService {
    public String getSklejenie(final StringRequest request) {
        return request.getStringPierwszy() + request.getStringDrugi();
        //stringy sie konkatynują +
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
                            if (element.getLitera().equals(litera.toLowerCase())){
                                element.setIlosc(element.getIlosc()+1);
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
        for (LiteryDTO element:wystapienia){
            wynik.add(element.getLitera()+"-"+element.getIlosc());

        }
        wynik.sort(String::compareTo);//sortowanie listy wynik alfabetycznie(bo operujemy na stringach)
        return wynik;
    }
}
