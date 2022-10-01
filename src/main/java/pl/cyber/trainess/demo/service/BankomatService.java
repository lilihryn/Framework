package pl.cyber.trainess.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.cyber.trainess.demo.domain.BankomatEntry;
import pl.cyber.trainess.demo.dto.BankomatDTO;
import pl.cyber.trainess.demo.repository.BankomatRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
public class BankomatService {

    private final BankomatRepository bankomatRepository;
    private final FileReaderService fileReaderService;

    public List<BankomatDTO> getAllAtms() {
        log.info("Wyszukanie wszystkich bankomatów");
        log.warn("Coś poszło nie tak!! :)");
        log.error("Rest communication failed!!");

        var allAtms = bankomatRepository.findAll();
        List<BankomatDTO> result = new ArrayList<>();

        for (BankomatEntry entry : allAtms) {
            result.add(entry.convertToDTO());
        }

        return result;
    }

    public void create(final BankomatDTO bankomatDTO) {

        bankomatRepository.save(BankomatEntry.builder()
                .miasto(bankomatDTO.getMiasto())
                .czyAktywny(bankomatDTO.getCzyAktywny())
                .name(bankomatDTO.getName())
                .saldo(bankomatDTO.getSaldo())
                .ulica(bankomatDTO.getUlica())
                .build());
    }

    public void create(final MultipartFile file) {
        List<BankomatDTO> bankomatDTOs = fileReaderService.readATMFile(file);

        for (BankomatDTO element : bankomatDTOs) {
            bankomatRepository.save(BankomatEntry.builder()
                    .name(element.getName())
                    .saldo(element.getSaldo())
                    .miasto(element.getMiasto())
                    .ulica(element.getUlica())
                    .czyAktywny(element.getCzyAktywny())
                    .build());
        }
    }

    @Transactional
    public void updateName(final String id, final String name) {
//    1)
    /*var allAtms = bankomatRepository.findAll();
    for (BankomatEntry entry : allAtms) {
        if (entry.getId().equals(id)) {
          entry.setName(name);
          bankomatRepository.save(entry);
        }
      }*/
//    2)
//    var atm = bankomatRepository.findById(id).orElseThrow(() -> new RuntimeException("Brak rekordu!!!!") );
//    3)
//    bankomatRepository.findById(id)
//        .ifPresent(entry -> {
//          entry.setName(name);
//          bankomatRepository.save(entry);
//        });
//4)
        bankomatRepository.findById(id)
                .ifPresentOrElse(entry -> {
                            entry.setName(name);
                            bankomatRepository.save(entry);
                        },
                        () -> {
                            throw new RuntimeException("Brak rekordu!!!!");
                        });
//5)
//   var atm = bankomatRepository.findById(id).orElse(null);
//   if(Objects.nonNull(atm)) {
//     atm.setName(name);
//     bankomatRepository.save(atm);
//   }

//    bankomatRepository.updateName(id, name);

    }


    public void wplata(final String id, final Integer cash) {
        if (cash <= 0) {
            throw new RuntimeException("Niedozwolona kwota wyplaty!");
        }

        var atm = bankomatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie odnaleziono bankomatu"));

        atm.setSaldo(BigDecimal.valueOf(cash));
        if (atm.getSaldo().compareTo(BigDecimal.ZERO) > 0) {
            atm.setCzyAktywny(true);
        }
        bankomatRepository.save(atm);
    }

    public void wyplata(final String id, final Integer cash) {
        if (cash <= 0) {
            throw new RuntimeException("Prosze podac kwotę >0");
        }

        var atm = bankomatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie odnaleziona bankomatu"));

        if (atm.getSaldo().subtract(BigDecimal.valueOf(cash)).compareTo(BigDecimal.ZERO) < 0) {
            //pobieranie salda przez getsaldo, metoda substract jest metoda odejmowania dla BigDecimal,
            //compareTo porówniujemy wynik z domiślienoj wartościu 0 i zwraca (-1,0,1) i sprawdzamy wartość
            throw new RuntimeException("Kwota wyplaty jest wieksza niż gotówka w bankomacie");
        }
        atm.setSaldo(atm.getSaldo().subtract(BigDecimal.valueOf(cash)));
        if (atm.getSaldo().compareTo(BigDecimal.ZERO) > 0) {
            atm.setCzyAktywny(false);
        }
        bankomatRepository.save(atm);
    }
}
