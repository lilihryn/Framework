package pl.cyber.trainess.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.cyber.trainess.demo.domain.BankomatEntry;

@Repository
public interface BankomatRepository extends JpaRepository<BankomatEntry,
        String> {
    //metoda CRUD create read update delete
    @Modifying// sie zmienia
    @Query(value="UPDATE BANKOMAT set name=:name where id=:id", nativeQuery = true)
    void updateName(@Param("id") String id, @Param("name") String name);

}
