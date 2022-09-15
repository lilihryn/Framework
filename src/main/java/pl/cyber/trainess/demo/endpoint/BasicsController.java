package pl.cyber.trainess.demo.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.cyber.trainess.demo.dto.*;
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
    public boolean podzielnoscLiczb(@PathVariable("a") final Integer a, @PathVariable("b") final Integer b) {
        return kalkulatorService.getPodzielnoscLiczb(a, b);
    }

    @GetMapping("/czy-jest-dzielnikiem/{a}/{b}")
    public boolean getCzyJestDzielnikiem(@PathVariable final Integer a, @PathVariable final Integer b) {
        return kalkulatorService.getCzyJestDzielnikiem(a, b);
    }
    //region Zadanie6 /* Napisz program, który wygeneruje liczbę Random z przedziału od 10 - 1000.
    // Naszym zadaniem będzie odnalezienie wygenerowanej liczby.
    // W tym celu należy utworzyć zapytanie restowe, które będzie przyjmowało liczbę i porównywało ją z wygenerowaną przez system.
    // Jeśli wprowadzona liczba będzie tą wygenerowaną zostanie zwrócony napis "Udało się!!
    // " Jeśli wprowadzona liczba będzie mniejsza od wygenerowanej zostanie zwrócony napis "Wygenerowana liczba jest większa"
    // Jeśli wprowadzona liczba będzie większa od wygenerowanej zostanie zwrócony napis "Wygenerowana liczba jest mniejsza"
    // Uwaga aby generowana liczba powinna być parametrem klasy aby przy każdym zapytaniu restowym nie doszło do jej modyfikacji. */

    @GetMapping("/liczbaRandom/{a}")
    public String jakaLiczbaRandom(@PathVariable("a") final Integer a) {
        return znajdzService.jakaLiczbaRandom(a);
    }


    //region Zadanie7 /* Napisz program, w którym zostaną przekazane liczby a i b (całkowite)
    // następnie zostaną zsumowane wszystkie liczby pomiędzy od a do b (jako przedział zamknięty dwustronnie).
    // Przykład podajemy: 1 do 10 czego wynikiem będzie 55 */
    // besicsService //endregion petla for
    //GET PathVariable lub RequestParam
    //dwa warianty,pierwszy mój
    @GetMapping("/suma-liczb-przedzialu/{a}/{b}")
    public Integer sumaLiczbPrzedzialu(@PathVariable("a") final Integer a, @PathVariable("b") final Integer b) {
        return kalkulatorService.getSumaLiczbPrzedzialu(a, b);
    }

    @GetMapping("/suma-liczb")
    public String sumaLicbPomiedzy(@RequestParam("a") final Integer a, @RequestParam("b") final Integer b) {
        return basicsService.sumaLiczbPomiedzy(a, b);
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
    dwa warianty,pierwszy mój

     */
    @PostMapping("/lista-wartostej-suma")
    public String czyUjemneCzyDodatnie(@RequestBody final ListaRequest request) {
        return basicsService.getListeUjemnychSumaInt(request);
    }

    @PostMapping("/liczby")
    public String zadanie8(@RequestBody final IntegerListRequest request) {
        return basicsService.zadanie8(request);
    }


    // zadanie9 /* Napisz zapytanie restowe, którego zadaniem będzie obliczał pierwiastek równania kwadratowego ax2 + bx + c = 0.
    // (Do wykorzystania instrukcja if). Pamiętać należy że zmienne a, b i c to liczby rzeczywiste.
    // Zadanie powinno zwrócić Napis:
    // a) To nie jest równanie kwadratowe
    // b) Brak pierwiastków
    // c) Jeden pierwiastek.
    // Wynik: xxxx d) Dwa pierwiastki. Wynik -> x1: xxxx, x2: xxxx */ //KalkulatorService //endregion
    //na if-ach bez for-u
    //GetMapping,trzy parametry w środku PathVariable
    //GetMapping RequestParam
    //PostMapping
    //dwa rozwiazania,pierwsze moje

    @GetMapping("/pierwiastek-rownania-kwadratowego/{a}/{b}/{c}")
    public String pierwistekRownianiaKwadratowego(@PathVariable("a") final Double a,
                                                  @PathVariable("b") final Double b,
                                                  @PathVariable("c") final Double c) {
        return kalkulatorService.getPierwiastekRownianiaKwadratowego(a, b, c);
    }

    @GetMapping("/rownanie-kwadratowe/{a}/{b}/{c}")
    public String rownanieKwadratowe(@PathVariable("a") final Integer a,
                                     @PathVariable("b") final Integer b,
                                     @PathVariable("c") final Integer c) {
        return kalkulatorService.rownanieKwadratowe(a, b, c);
    }
    @PostMapping("/rownanie-kwadratowe2")
    public String rownanieKwadratowe2(@RequestBody final RownanieKwadratoweRequest request){
        return kalkulatorService.rownanieKwadratowe(request);
    }
    @PostMapping("/rownanie-kwadratowe-2miejsca")
    public String rownanieKwadratowe2miejsca(@RequestBody final RownanieKwadratoweRequest request){
        return kalkulatorService.rownanieKwadratowe2miejsca(request);
    }
    /*
    Zadanie10:
    Za pomocą instrukciji pętli for dla danych wartośći x zmięniających o 0 do 10 odliczymy wartość funkciji y=3x
     */
    @GetMapping("/zadanie10a")
    public String zadanie10a(){
        return kalkulatorService.zadanie10a();
    }
    /*
    Zadanie10b
    Za pomocą instrukciji pętli do while dla danych wartośći x zmięniających o 0 do 10 odliczymy wartość funkciji y=3x
     */
    @GetMapping("/zadanie10b")
    public String zadanie10b(){
        return kalkulatorService.zadanie10b();
    }
    /*
    zadanie10c
    z pomocą pętli while
     */
    @GetMapping("/zadanie10c")
    public String zadanie10c(){
       return kalkulatorService.zadanie10c();
    }
    /*
    Praca domowa zrobić z pomocą @RequestParam
    @RequestBody zadania 10a-c
     */


    /*
    Zadanie11a
    Program,który przy pomocy pętli for przekaże nam wyniki tabliczki mnżenia od1 do 100
    potrzebujemy 2 pętli for wiersz i kolumna
    Parametr 1-10
     */
    @GetMapping("/zadanie11a")
    public String zadanie11a(){
        return kalkulatorService.zadanie11a();
    }
    @GetMapping("/zadanie11aa")
    public String zadanie11aa(){
        return kalkulatorService.zadanie11aa();
    }
    @GetMapping("/zadanie11ab")
    public String zadanie11ab(){
        return kalkulatorService.zadanie11ab();
    }
/*
    Zadanie11b
    Program,który przy pomocy pętli do while  przekaże nam wyniki tabliczki mnżenia od1 do 100
    potrzebujemy 2 pętli for wiersz i kolumna
    Parametr 1-10
     */

   @GetMapping("/zadanie11b")
    public String zadanie11b(){
       return kalkulatorService.zadanie11b();
   }
   /*
   Zadanie11c z petlą while
   while(){wiersze
   pierwotne ustawienie dla kolumny
   while(){kolumny
   mnozenie
   inkrementacja kolumny
   }}
    */
    @GetMapping("/zadanie11c")
    public String zadanie11c(){
        return kalkulatorService.zadanie11c();
    }
    /*
 //region ZADANIE DOMOWE
  /*
zadanie
  Proszę zadanie 10 (a, b, c) przerobić tak, aby można było z zewnątrz podać parametr x oraz parametr do kiedy nasza pętla ma się wykonywać
  Proszę aby nie wzorować się na dostępnych metodach.
Zadania dla chętnych:
1)
Napisz program, który oblicza wartość x z równania ax+b = c. Wartości a, b i c należy podać poprzez PathVariable, RequestParam lub RequestBody.
Należy zabezpieczyć program na wypadek sytuacji, kiedy wprowadzona wartość 'a' będzie równa zero. Dla zmiennych a, b, c oraz x należy
przyjąć format wyświetlania ich na ekranie z dokładnością do dwóch miejsc
po przecinku
2)
Napisz program, który za pomocą instrukcji (for, do ... while oraz while, tzn trzy różne rozwiązania)
znajduje największą i najmniejszą liczbę ze zbioru 'n' wylosowanych liczb całkowitych od 0 do 100
oraz oblicza średnią ze wszystkich wylosowanych liczb
  */
    //endregion

    @GetMapping("/wartosc-rowniania/{a}/{b}/{c}")
    public String wartoscRowniania(@PathVariable("a") final double a, @PathVariable("b") final double b, @PathVariable("c") final double c){
      return   kalkulatorService.wartoscRowniania(a,b,c);
    }
    @PostMapping("/wartosc-rowniania2/{a}/{b}/{c}")
    public String wartoscRowniania2(@RequestParam("a")final double a,
                                    @RequestParam("b")final double b,
                                    @RequestParam("c")final double c){
        return kalkulatorService.wartoscRowniania2(a,b,c);
    }

    @GetMapping("/zbior-n")
    public String zbiorN(){
        return kalkulatorService.zbiorN();
    }

 /*
    Praca domowa zrobić z pomocą @RequestParam
    @RequestBody zadania 10a-c
    Za pomocą instrukciji pętli for dla danych wartośći x zmięniających o 0 do 10 odliczymy wartość funkciji y=3x
    Proszę zadanie 10 (a, b, c) przerobić tak, aby można było z zewnątrz podać parametr x oraz parametr do kiedy nasza pętla ma się wykonywać
    Proszę aby nie wzorować się na dostępnych metodach.
     */
    @GetMapping("/zadanie10-PD")
    public String zadanie10PD(@RequestParam("x") final Integer x,@RequestParam("p") final Integer p){
        return  kalkulatorService.zadanie10PD(x,p);
    }

}
