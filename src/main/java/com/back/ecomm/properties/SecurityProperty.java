package com.back.ecomm.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix="security")
public class SecurityProperty {
    private String corsPermittedIncome;
    private List<String> permittedRequest;

    public String getCorsPermittedIncome() {
        return corsPermittedIncome;
    }
    public void setCorsPermittedIncome(String corsPermittedIncome) {
        this.corsPermittedIncome = corsPermittedIncome;
    }

    public List<String> getPermittedRequest() {
        return permittedRequest;
    }

    public void setPermittedRequest(List<String> permittedRequest) {
        this.permittedRequest = permittedRequest;
    }
}
