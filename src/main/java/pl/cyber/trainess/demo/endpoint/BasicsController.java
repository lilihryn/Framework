package pl.cyber.trainess.demo.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.cyber.trainess.demo.dto.ListaRequest;
import pl.cyber.trainess.demo.dto.OneStringRequest;
import pl.cyber.trainess.demo.dto.StringRequest;
import pl.cyber.trainess.demo.service.BasicsService;
import pl.cyber.trainess.demo.service.KalkulatorService;
import pl.cyber.trainess.demo.service.ZnajdzService;

import java.util.List;

@RestController
@RequestMapping("/v1/basics")
@RequiredArgsConstructor
public class BasicsController {
    private final KalkulatorService kalkulatorService;
    private final BasicsService basicsService;
    private final ZnajdzService znajdzService;

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
    //@PostMapping
    //@RequestBody
    //object String posiada metodę matches(//regexp//).matches("[a-zA-Z]+")
    //Object List posiada metodę sort(//Compartor//).sort(String::compareTo)

    @PostMapping("/zliczanie")//metoda http
    public List<String> getWystapienieLiterWZdaniu(@RequestBody final OneStringRequest request) {
        return basicsService.getWystapienieLiterWZdaniu(request);
    }

    //Kolekcje Map
    @PostMapping("/zliczanie1")//metoda http
    public List<String> getWystapienieLiterWZdaniuMap(@RequestBody final OneStringRequest request) {
        return basicsService.getWystapienieLiterWZdaniuMap(request);
    }
    /*
//region Zadanie5 /* Napisz zapytanie restowe, którego zadaniem będzie przekazanie liczb a i b (całkowite),
następnie wykona sprawdzenie czy liczba a jest dzielnikiem liczby b i zwróci informację true lub false */

    @PostMapping("/podzielnoscLiczb/{a}/{b}")
    public boolean podzielnoscLiczb(@PathVariable("a") final Integer a, @PathVariable("b")final Integer b){
        return kalkulatorService.getPodzielnoscLiczb(a,b);
    }
    @GetMapping("/czy-jest-dzielnikiem/{a}/{b}")
    public boolean getCzyJestDzielnikiem( @PathVariable final Integer a, @PathVariable final Integer b){
        return kalkulatorService.getCzyJestDzielnikiem(a,b);
    }
    //region Zadanie6 /* Napisz program, który wygeneruje liczbę Random z przedziału od 10 - 1000.
    // Naszym zadaniem będzie odnalezienie wygenerowanej liczby.
    // W tym celu należy utworzyć zapytanie restowe, które będzie przyjmowało liczbę i porównywało ją z wygenerowaną przez system.
    // Jeśli wprowadzona liczba będzie tą wygenerowaną zostanie zwrócony napis "Udało się!!
    // " Jeśli wprowadzona liczba będzie mniejsza od wygenerowanej zostanie zwrócony napis "Wygenerowana liczba jest większa"
    // Jeśli wprowadzona liczba będzie większa od wygenerowanej zostanie zwrócony napis "Wygenerowana liczba jest mniejsza"
    // Uwaga aby generowana liczba powinna być parametrem klasy aby przy każdym zapytaniu restowym nie doszło do jej modyfikacji. */

    @GetMapping ("/liczbaRandom/{a}")
    public String jakaLiczbaRandom(@PathVariable("a") final Integer a){
        return znajdzService.jakaLiczbaRandom(a);
    }
    //region Zadanie7 /* Napisz program, w którym zostaną przekazane liczby a i b (całkowite)
    // następnie zostaną zsumowane wszystkie liczby pomiędzy od a do b (jako przedział zamknięty dwustronnie).
    // Przykład podajemy: 1 do 10 czego wynikiem będzie 55 */
    // besicsService //endregion petla for
    //GET PathVariable lub RequestParam
    @GetMapping("/suma-liczb-przedzialu/{a}/{b}")
    public Integer sumaLiczbPrzedzialu(@PathVariable("a")final Integer a,@PathVariable("b")final Integer b){
        return kalkulatorService.getSumaLiczbPrzedzialu(a,b);
    }

    // region Zadanie8 /*Napisz program, w krótym przekażemy listę elementów liczb całkowitych
    // program powinien zwrócić listę elementów z wartościami ujemnymi oraz sumę liczb, które są dodatnie.
    // Np. [1, 2, 3, 4, 5, -3, -2, -1]
    //
    // wynik: [-3, -2, -1] oraz suma liczb dodatnich wynosi:
    // 15 */ //besicsService //endregion //region
    //POST RequestBody
    //IntegerListRequest>>List<Integer>
    //JSON
    /*
    if żeby sprawdzić czy lista jest ujemna czy dodatnia
    sortowanie listy przez sort
    musze być
    lista wartostej ujemnych
    lista sumy liczb dodatnich
    for,żeby przejsz po liscie i if dla rozdzielenia
    wynik jest w postacie stringa
    Lista ujemna+"opis"+sumaInt

     */
    @PostMapping("/lista-wartostej-suma")
    public String czyUjemneCzyDodatnie(@RequestBody final ListaRequest request){
        return basicsService.getListeUjemnychSumaInt(request);
    }

    // zadanie9 /* Napisz zapytanie restowe, którego zadaniem będzie obliczał pierwiastek równania kwadratowego ax2 + bx + c = 0.
    // (Do wykożystania instrukcja if). Pamiętać należy że zmienne a, b i c to liczby rzeczywiste.
    // Zadanie powinno zwrócić Napis:
    // a) To nie jest równanie kwadratowe
    // b) Brak pierwiastków
    // c) Jeden pierwiastek.
    // Wynik: xxxx d) Dwa pierwiastki. Wynik -> x1: xxxx, x2: xxxx */ //KalkulatorService //endregion
    //na if-ach bez for-u
    //GetMapping,trzy parametry w środku PathVariable
    //GetMapping RequestParam
    //PostMapping

    @GetMapping("/pierwiastek-rownania-kwadratowego/{a}/{b}/{c}")
    public String pierwistekRownianiaKwadratowego(@PathVariable("a") final Double a, @PathVariable("b") final Double b, @PathVariable("c") final Double c){
        return kalkulatorService.getPierwiastekRownianiaKwadratowego(a,b,c);
    }
}
