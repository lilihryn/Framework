package pl.cyber.trainess.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cyber.trainess.demo.domain.KontoBankoweEntry;
import pl.cyber.trainess.demo.dto.KontoBankoweDTO;
import pl.cyber.trainess.demo.repository.KontoBankoweRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KontoBankoweService {

private final KontoBankoweRepository kontoBankoweRepository;
    public List<KontoBankoweDTO> getAllRecords() {

        return kontoBankoweRepository.findAll()
                .stream()
                .map(KontoBankoweEntry::convertToDTO)
                .collect(Collectors.toList());
    }

    public void create(final KontoBankoweDTO kontoBankoweDTO) {

    }
}
