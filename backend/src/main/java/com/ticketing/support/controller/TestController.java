package com.ticketing.support.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping
    public Map<String, String> testApi() {
        return Map.of(
                "status", "success",
                "message", "Backend automatically updated"
        );
    }
}