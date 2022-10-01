package pl.cyber.trainess.demo.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import pl.cyber.trainess.demo.dto.KontoBankoweDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "KONTO_BANKOWE")
public class KontoBankoweEntry {

    @Id
    @GeneratedValue(generator = "UUID")//085432ca-18f3-46a3-8f9d-3dea24485fef
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID", updatable = false, nullable = false)
    private String id;

    @Setter
    @Column(name = "IMIE")
    private String imie;

    @Setter
    @Column(name = "NAZWISKO")
    private String nazwisko;

    @Setter
    @Column(name = "SALDO")
    private BigDecimal saldo;


    @Column(name = "DATA_UR")
    private LocalDateTime dataUr;

    @Column(name = "MIASTO")
    private String miasto;
    @Column(name = "NR_KARTY")
    private Integer numerKarty;

    public KontoBankoweDTO convertToDTO(){
        return KontoBankoweDTO.builder()
                .id(this.id)
                .imie(this.imie)
                .nazwisko(this.nazwisko)
                .dataUr(this.dataUr)
                .miasto(this.miasto)
                .nrKarty(this.numerKarty)
                .build();
    }



}
