package com.back.ecomm.controller;

import com.back.ecomm.record.FileUploadRequest;
import com.back.ecomm.record.FileUploadResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.back.ecomm.service.FileService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Validated
@RestController
@RequestMapping("/v1/file")
public class FileController {


    private final FileService service;

    public FileController(FileService fileService){
        this.service = fileService;
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FileUploadResponse> upload(
        @Validated @RequestParam("file")MultipartFile file,
                @RequestParam("name")String name)
    {
        FileUploadRequest fileUploadRequest = new FileUploadRequest(file, name);
        return ResponseEntity.ok(service.upload(fileUploadRequest));
    }




    // Utility method
    private void writeFile(byte[] content, String filename) throws IOException {
        File file = new File(filename);

        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fop = new FileOutputStream(file);
        fop.write(content);
        fop.flush();
        fop.close();
    }
}
