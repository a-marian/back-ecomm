package com.back.ecomm.record;

public record FileUploadResponse (

    String id,
    String name,
    String url,
    Long size
    ){ }
