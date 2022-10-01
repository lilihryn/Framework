package pl.cyber.trainess.demo.endpoint;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import pl.cyber.trainess.demo.dto.KontoBankoweDTO;
import pl.cyber.trainess.demo.service.KontoBankoweService;

import java.util.List;

@RestController
@RequiredArgsConstructor// konstruktor tylko z parametrów final
@RequestMapping("/v1/konto")
public class KontoBankoweController {

    private final KontoBankoweService kontoBankoweService;
    @GetMapping
    public List<KontoBankoweDTO> getAllKB() {
        return kontoBankoweService.getAllRecords();
    }

    @PutMapping
    public void create(@RequestBody final KontoBankoweDTO kontoBankoweDTO) {
        kontoBankoweService.create(kontoBankoweDTO);
    }
}