package com.back.ecomm.util;

import org.springframework.web.multipart.MultipartFile;

public class RequestFactory {

    private static RequestFactory INSTANCE = new RequestFactory();
    private RequestFactory(){}
    public  static RequestFactory getInstance() { return  INSTANCE;}

    private MultipartFile file;


}
