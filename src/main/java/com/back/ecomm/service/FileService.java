package com.back.ecomm.service;


import com.back.ecomm.record.FileUploadRequest;

import java.io.IOException;

public interface FileService {

    String upload(FileUploadRequest fileUploadRequest) throws IOException;
}
