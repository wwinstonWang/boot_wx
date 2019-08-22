package com.winston.wx.util;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

public class Net {
    static RestTemplate restTemplate=new RestTemplate(){{
        this.getMessageConverters().remove(1);
        StringHttpMessageConverter stringHttpMessageConverter= new StringHttpMessageConverter(Charset.forName("utf-8"));
        this.getMessageConverters().add(1,stringHttpMessageConverter);
    }};

    public static RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
