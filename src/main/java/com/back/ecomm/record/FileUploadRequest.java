package com.back.ecomm.record;

import org.springframework.web.multipart.MultipartFile;

public record FileUploadRequest (MultipartFile file, String name)
{ }
