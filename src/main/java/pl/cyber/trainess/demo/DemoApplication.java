package pl.cyber.trainess.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//adnotacja którem zadaniem jesturuchomienie aplikacji springowej
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class,args);
        //linii kodu które nie mogą być zmodefikowane
    }
}
