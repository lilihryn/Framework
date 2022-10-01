package pl.cyber.trainess.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.SplitType;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.export.SimpleCsvExporterConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import org.apache.commons.lang3.CharEncoding;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.cyber.trainess.demo.config.Templates;
import pl.cyber.trainess.demo.domain.BankomatEntry;
import pl.cyber.trainess.demo.dto.BankomatDTO;
import pl.cyber.trainess.demo.repository.BankomatRepository;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

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

    public void getBankomatPDFFile(final HttpServletResponse response) {
        //objekt jasper report builder
        final JasperReportBuilder reportBuilder = getReportContextPrepare();
        response.setContentType("application/pdf");
        response.setCharacterEncoding(CharEncoding.UTF_8);
        setDefaultReportProperties(reportBuilder);
        try {
            response.getOutputStream().write(
                    JasperExportManager.exportReportToPdf(reportBuilder.toJasperPrint()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (DRException e) {
            throw new RuntimeException(e);
        }
    }

    private JasperReportBuilder getReportContextPrepare() {
//        List<BankomatEntry>bankomatList=bankomatRepository.findAll();
//        List<BankomatDTO>bankomatDTOList=new ArrayList<>();
//        for (BankomatEntry ele:bankomatList){
//            bankomatDTOList.add(toReport(ele));
//        }
        JasperReportBuilder jrBuilder;
        StyleBuilder textStyle = stl.style(Templates.columnStyle).setBorder(stl.pen1Point());
        //typy danych do jakich się odwolujemy
        TextColumnBuilder<String> idColumn = col.column("Db id", "id", type.stringType());
        TextColumnBuilder<String> nameColumn = col.column("Bankomat Name", "name", type.stringType());
        TextColumnBuilder<BigDecimal> saldoColumn = col.column("Saldo", "saldo", type.bigDecimalType());
        TextColumnBuilder<String> miastoColumn = col.column("Miasto", "miasto", type.stringType());
        TextColumnBuilder<String> ulicaColumn = col.column("Ulica", "ulica", type.stringType());
        TextColumnBuilder<Boolean> czyAktywnyColumn = col.column("Aktywny", "czyAktywny", type.booleanType());
        jrBuilder = report().setTemplate(Templates.reportTemplate).setColumnStyle(textStyle)
                .columns(idColumn, nameColumn, saldoColumn, miastoColumn, ulicaColumn, czyAktywnyColumn)
                .title(Templates.createTitleComponent("Raport bankomatow"))
                .setDataSource(bankomatRepository.findAll()
                        .stream()//do przechodzenia pomiędzy recordami(jak by byl for albo foreach)
                        .map(this::toReport)//wykonujemo metode toReport z klasy BankomatService
                        //.map(bankomatList)
                        //.map(BankomatEntry::convertToDTO)
                        .collect(Collectors.toList()));


        return jrBuilder;
    }

    private BankomatDTO toReport(final BankomatEntry bankomatEntry) {
        return BankomatDTO.builder()
                .id(bankomatEntry.getId())
                .name(bankomatEntry.getName())
                .saldo(bankomatEntry.getSaldo())
                .miasto(bankomatEntry.getMiasto())
                .ulica(bankomatEntry.getUlica())
                .czyAktywny(bankomatEntry.getCzyAktywny())
                .build();
    }

    //ustalenie wzoru raportu
    private void setDefaultReportProperties(final JasperReportBuilder reportBuilder) {
        reportBuilder.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE);
        reportBuilder.pageFooter(cmp.pageXofY());
        reportBuilder.setColumnTitleStyle(stl.style(stl.style(stl.style().bold())
                .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
                .setBorder(stl.pen1Point()).setBackgroundColor(Color.lightGray)));
        reportBuilder.setDetailSplitType(SplitType.PREVENT);
        reportBuilder.setColumnStyle(stl.style().setHorizontalTextAlignment(HorizontalTextAlignment.JUSTIFIED));
        reportBuilder.highlightDetailOddRows();
    }

    //tworzenie failu csv
    public void getBankomatCSVFile(final HttpServletResponse response) {
        try {
            JasperPrint reportCSV = getReportContext();
            response.setContentType("txt/csv");
            //internet servlet response documentacja
            response.setHeader("Content-Disposion", "attachment;filename=bankomat-file.csv");
            OutputStream out = response.getOutputStream();
            JRCsvExporter csvExporter = new JRCsvExporter();
            csvExporter.setExporterInput(SimpleExporterInput.getInstance(Arrays.asList(reportCSV)));
            csvExporter.setExporterOutput(new SimpleWriterExporterOutput(out));
            SimpleCsvExporterConfiguration simpleCsvExporterConfiguration = new SimpleCsvExporterConfiguration();
            simpleCsvExporterConfiguration.setFieldDelimiter("|");
            csvExporter.setConfiguration(simpleCsvExporterConfiguration);
                csvExporter.exportReport();
                out.flush();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private JasperPrint getReportContext() {
        try {
            return getReportContextPrepare().toJasperPrint();
        } catch (DRException e) {
            throw new RuntimeException();
        }
    }
}
