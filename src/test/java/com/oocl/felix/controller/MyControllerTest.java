package com.oocl.felix.controller;

import java.net.URI;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

class MyControllerTest {

    private Logger logger = Logger.getLogger(MyControllerTest.class.getName());

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void showApiCallTime() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            System.out.println(callApi());
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time spent: " + (end - start) / 1000.0);
    }

    private String callApi() {
        try {
            return restTemplate.getForObject(URI.create("http://localhost:8080/api/test"), String.class);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return "error";
    }
}