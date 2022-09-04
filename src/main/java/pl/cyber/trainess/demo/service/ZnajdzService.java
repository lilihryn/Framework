package pl.cyber.trainess.demo.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;
@Slf4j
@Service
public class ZnajdzService {
    private Integer losowaLiczba=0;

    public ZnajdzService() {
        Random r=new Random();
        this.losowaLiczba = r.nextInt(998)+10;//od 10do 1000
        log.info("Wylosowana Liczba to: "+this.losowaLiczba.toString());
    }

    public String jakaLiczbaRandom(final Integer a) {
        if(Objects.equals(a, losowaLiczba)){//metody equals and hashcode
            return "Udało się!!";
        }
        else if(a>losowaLiczba){
            return "Wygenerowana liczba jest większa";
        }else{
            return"Wygenerowana liczba jest mniejsza";
        }

    }
}
