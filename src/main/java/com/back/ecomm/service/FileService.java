package com.back.ecomm.service;


import com.back.ecomm.record.FileUploadRequest;
import com.back.ecomm.record.FileUploadResponse;

public interface FileService {

    FileUploadResponse upload(FileUploadRequest fileUploadRequest);
}
