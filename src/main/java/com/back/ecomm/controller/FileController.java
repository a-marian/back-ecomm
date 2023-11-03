package com.back.ecomm.controller;

import com.back.ecomm.record.FileUploadRequest;
import com.back.ecomm.record.FileUploadResponse;
import com.back.ecomm.service.FileService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
        @Validated @RequestParam("file")MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        long size = file.getSize();

        FileUploadRequest fileUploadRequest = new FileUploadRequest(file, fileName);
        String uploadedFileName = service.upload(fileUploadRequest);
        String url = "/downloadFile".concat(uploadedFileName);
        FileUploadResponse response = new FileUploadResponse(fileName, url, size);
        return ResponseEntity.ok(response);
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
