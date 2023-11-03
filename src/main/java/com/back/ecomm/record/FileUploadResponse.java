package com.back.ecomm.record;

public record FileUploadResponse (
        String name, String url, long size
    ){ }
