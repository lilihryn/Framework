package pl.cyber.trainess.demo.service;

import org.springframework.stereotype.Service;
import pl.cyber.trainess.demo.dto.StringRequest;

@Service
public class BasicsService {
    public String getSklejenie(final StringRequest request) {
        return request.getStringPierwszy()+request.getStringDrugi();
        //stringy sie konkatynują +
    }
}
