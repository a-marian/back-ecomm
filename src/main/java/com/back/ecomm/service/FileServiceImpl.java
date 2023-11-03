package com.back.ecomm.service;

import com.back.ecomm.record.FileUploadRequest;
import com.back.ecomm.record.FileUploadResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {


    @Override
    public  String upload(FileUploadRequest fileUploadRequest) throws IOException {

        Path uploadPath = Paths.get("upload-files");
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        String fileCode = RandomStringUtils.randomAlphanumeric(8);
        try(InputStream inputStream = fileUploadRequest.file().getInputStream()){
            Path filePath = uploadPath.resolve(fileCode+"-"+fileUploadRequest.name());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex){
            throw new IOException("Could not save file"+ fileUploadRequest.name(), ex);
        }
        return fileCode ;
    }
}
