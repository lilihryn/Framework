package pl.cyber.trainess.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.cyber.trainess.demo.domain.KontoBankoweEntry;

@Repository

public interface KontoBankoweRepository extends JpaRepository <KontoBankoweEntry, String>{
    //brakuje

}
