package pl.cyber.trainess.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.cyber.trainess.demo.dto.BankomatDTO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class FileReaderService {

    public List<BankomatDTO> readATMFile(MultipartFile file) {
        Scanner scanner = null;

        try {
            scanner = new Scanner(convert(file));
        } catch (Exception e) {
            throw new RuntimeException("Read file failed!!!! " + e);
        }

        // parse file to BankomatDTO
        //dla chętnych można spróbować rozwiązać, dokończymy za tydzień

        List<BankomatDTO> result = new ArrayList<>();

        while (scanner.hasNext()) {
            String rowInFile = scanner.nextLine();
            String[] tabRowInFile = rowInFile.split(",");

            result.add(BankomatDTO.builder()
                    .name(tabRowInFile[0])
                    .saldo(new BigDecimal(tabRowInFile[1]))
                    .miasto(tabRowInFile[2])
                    .ulica(tabRowInFile[3])
                    .czyAktywny(Boolean.valueOf(tabRowInFile[4]))
                    .build());
        }

        return result;
    }

    private File convert(final MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convertFile);
        fos.write(file.getBytes());
        fos.close();
        return convertFile;
    }
}