package pl.cyber.trainess.demo.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.cyber.trainess.demo.dto.BankomatDTO;
import pl.cyber.trainess.demo.service.BankomatService;

import java.util.List;

@RestController
@RequiredArgsConstructor// konstruktor tylko z parametrów final
@RequestMapping("/v1/atms")
public class BankomatController {
    private final BankomatService bankomatService;

    @GetMapping
    public List<BankomatDTO> getAllAtms() {
        return bankomatService.getAllAtms();
    }

    @PutMapping
    public void create(@RequestBody final BankomatDTO bankomatDTO){
        bankomatService.create(bankomatDTO);
    }
    @PostMapping("/name")
    public void updateName(@RequestParam("name") final String name, @RequestParam("id") final String id){
        bankomatService.updateName(id,name);

    }
    //jak odczytac pliki od użytkownika
    //MultipartFile
    @PostMapping("/import-csv")
    public void createFromCSV(

            @RequestPart()MultipartFile file){
        bankomatService.create(file);
    }
}
