package pl.cyber.trainess.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.cyber.trainess.demo.domain.BankomatEntry;
import pl.cyber.trainess.demo.dto.BankomatDTO;
import pl.cyber.trainess.demo.repository.BankomatRepository;

import javax.transaction.Transactional;
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
        /*
        połączenie do DB oraz pobranie odpowiednych informacji
        przygotowanie Listy wynikowej
        petla konwertująca obiekt DB na obiekt dla Użytkownika
        komentarz dla siebie)
         */

        log.info("Wyszukanie wszystkich bankomatów");//adnotacja lombokowa
        log.warn("Coś poszło nie tak:)");
        log.error("Rest comunication failed");

        var allAtms = bankomatRepository.findAll();//(1)
        List<BankomatDTO> result = new ArrayList<>();
        for (BankomatEntry entry : allAtms) {
            result.add(entry.convertToDTO());
        }
        return result;

    }

    public void create(final BankomatDTO bankomatDTO) {//metoda do zapisywania informacji w bankomacie
        bankomatRepository.save(BankomatEntry.builder()
                .miasto(bankomatDTO.getMiasto())
                .name(bankomatDTO.getName())
                .saldo(bankomatDTO.getSaldo())
                .czyAktywny(bankomatDTO.getCzyAktywny())
                .ulica(bankomatDTO.getUlica())
                .build());
    }
@Transactional
    public void updateName(final String id, final String name) {


//        nie zalecana aktualizacja recordów
//        var AllAtms:List<BankomatEntry>=bankomatRepository.findAll();
//        for (BankomatEntry entry:AllAtms
//             ) {
//            if(entry.get().equals(id)){
//            entry.setName(name);
//            bankomatRepository.save(entry);
//
//        }
//        }
//    }
//      //  var atm=bankomatRepository.findById(id).orElseThrow((new RuntimeException("Brak recordu!!!")));
//        var atm=bankomatRepository.findById(id).ifPresent(entry -> {entry.setName(name);bankomatRepository.save(entry);},
//
//                () ->{throw  new RuntimeException("Brak rekordu");
//        });

//        var atm=bankomatRepository.findById(id).orElse(null);
//        if(Objects.nonNull(atm)){
//            atm.setName(name);
//            bankomatRepository.save(atm);
//
//        }

     bankomatRepository.updateName(id,name);

    }
    //jak odczytac pliki od użytkownika
    //MultipartFile

    public void create(final MultipartFile file) {

    }




}
