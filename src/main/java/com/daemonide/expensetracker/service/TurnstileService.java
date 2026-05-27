package com.daemonide.expensetracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class TurnstileService {

    @Value("${turnstile.secret}")
    private String secret;

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean verify(String token) {

        String url = "https://challenges.cloudflare.com/turnstile/v0/siteverify";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = Map.of(
                "secret", secret,
                "response", token
        );

        HttpEntity<Map<String, String>> request =
                new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
                url,
                request,
                Map.class
        );

        if (response.getBody() == null) {
            return false;
        }

        Object success = response.getBody().get("success");

        return Boolean.TRUE.equals(success);
    }
}