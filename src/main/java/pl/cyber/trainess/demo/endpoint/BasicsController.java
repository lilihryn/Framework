package pl.cyber.trainess.demo.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.cyber.trainess.demo.dto.StringRequest;
import pl.cyber.trainess.demo.service.BasicsService;
import pl.cyber.trainess.demo.service.KalkulatorService;

@RestController
@RequestMapping("/v1/basics")
@RequiredArgsConstructor
public class BasicsController {
    private final KalkulatorService kalkulatorService;
    private final BasicsService basicsService;

    /*
    Zadanie 1
    Napisać prostego kalkulatora, który będzie obslugowywać 5 operacij,
    każda z nich powinna być oddzielnym zapytaniem restowym:
    dodawanie
    odejmwanie
    mnożenie
    dzielenie
    obliczenie resztyz dzielenia liczb
    Zwrócenie wyników operacij
     */

    @GetMapping("/dodawanie/{a}/{b}")
    public Integer getDodawanie(@PathVariable("a") final Integer liczbaA, @PathVariable("b") Integer liczbaB) {
        return kalkulatorService.getDodawanie(liczbaA, liczbaB);
    }

    @GetMapping("/odejmowanie/{a}/{b}")
    public Integer getOdejmowanie(@PathVariable("a") final Integer liczbaA, @PathVariable("b") Integer liczbaB) {
        return kalkulatorService.getOdejmowanie(liczbaA, liczbaB);
    }

    @GetMapping("/mnozenie/{a}/{b}")
    public Integer getMnozenie(@PathVariable("a") final Integer liczbaA, @PathVariable("b") Integer liczbaB) {
        return kalkulatorService.getMnozenie(liczbaA, liczbaB);
    }

    @GetMapping("/dzielenie/{a}/{b}")
    public Integer getDzielenie(@PathVariable("a") final Integer liczbaA, @PathVariable("b") Integer liczbaB) {
        return kalkulatorService.getDzielenie(liczbaA, liczbaB);
    }

    @GetMapping("/obliczenie rezty/{a}/{b}")
    public Integer getReszteDzielenia(@PathVariable("a") final Integer liczbaA, @PathVariable("b") Integer liczbaB) {
        return kalkulatorService.getReszteDzielenia(liczbaA, liczbaB);
    }

    @GetMapping("/dodawanieParams")
    public Integer getDodawanieParams(@RequestParam("a") final Integer liczbaA, @RequestParam("b") final Integer liczbaB) {
        return kalkulatorService.getDodawanie(liczbaA, liczbaB);
    }//http://localhost:8150/demo/v1/basics/dodawanieParams?a=10&b=10

   /*
   Zadanie2
   Napisz zapytanie restowe, którego zadaniem bedzie wykonania
   sprawdzenie czy przekazana liczba jest liczbą pierwszą
   */

    @GetMapping("/liczbaPierwsza/{a}")
    public String getLiczbaPierwsza(@PathVariable("a") final Integer liczbaA) {
        return kalkulatorService.getLiczbaPierwsza(liczbaA);
    }

    /*Zadanie3
    Napisz zapytanie restowe, które skleja 2 stringi(przekazanych jako RequestBody, a następnie zwrócić wynik.

    POST/PUT
     */
    @PostMapping("/sklejenie-stringow")
    public String getSklejenie(@RequestBody final StringRequest request) {

        return basicsService.getSklejenie(request);
    }
    // region Zadanie4 /*Napisz zapytanie restowe,
    // którego zadaniem będzie przyjęcie napisu jako zdania (przekazanych jako RequestBody)
    // Program powinien policzyć ilość wystąpień poszczególnych liter zdania i zwrócić odpowiednio przygotowane dane.
    // Uwaga należy pominąć litery,
    // których w zdaniu nie ma oraz wszystkie znaki puste. Przykład. Ala ma kota a - 4 k - 1 l - 1 m - 1 o - 1 t - 1 */



}
