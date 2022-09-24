package pl.cyber.trainess.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.cyber.trainess.demo.dto.BankomatDTO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Service
public class FileReaderService {
    public List<BankomatDTO>readATMFile(MultipartFile file){
        Scanner scanner=null;
        try{
            scanner=new Scanner(convert(file));
        }catch
            (Exception e){
                throw new RuntimeException("Read file failed"+e);
            }
        //parse file to BankomatDTO
        return null;
    }

    private File convert(final MultipartFile file) throws IOException {
        File convertFile=new File(file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fos=new FileOutputStream(convertFile);
        fos.write(file.getBytes());
        fos.close();
        return convertFile;
    }
}
