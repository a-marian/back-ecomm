/**

package com.back.ecomm.service;

import com.back.ecomm.record.FileUploadRequest;
import com.back.ecomm.util.RequestFactory;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


class FileServiceImplTest {

    FileServiceImpl fileService = new FileServiceImpl();

    @Test
    public void test_upload() throws IOException {

        InputStream inputStream = mock(InputStream.class);
        MultipartFile fileMock = mock(MultipartFile.class);
        FileUploadRequest request = new FileUploadRequest(fileMock, "mockedFile");
        String fileName = "fileTesting";
        try(MockedStatic<RequestFactory> mockedStatic = mockStatic(RequestFactory.class)){
            mockedStatic.when(RequestFactory::createFileUploadRequest).thenReturn(request);
            when(request.file().getInputStream()).thenReturn(inputStream);
            String uploadPath = fileService.upload();

            assertNotNull(uploadPath);
        }
    }
}

*/