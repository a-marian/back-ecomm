package com.back.ecomm.service;

import com.back.ecomm.record.FileUploadRequest;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



class FileServiceImplTest {

    FileServiceImpl fileService = new FileServiceImpl();

    @Test
    public void test_upload() throws IOException {
        MultipartFile file = mock(MultipartFile.class);
        InputStream inputStream = mock(InputStream.class);

        String fileName = "fileTesting";
        FileUploadRequest request = new FileUploadRequest(file, fileName);
        when(request.file().getInputStream()).thenReturn(inputStream);
        String uploadPath = fileService.upload(request);
        assertNotNull(uploadPath);
    }

}