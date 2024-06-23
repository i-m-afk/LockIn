package com.imafk.tokenizer.controller;

import com.imafk.tokenizer.model.DetokenResponse;
import com.imafk.tokenizer.model.TokenResponse;
import com.imafk.tokenizer.service.TokenizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TokenizationController {
    private final TokenizationService tokenizationService;

    @Autowired
    public TokenizationController(TokenizationService tokenizationService) {
        this.tokenizationService = tokenizationService;
    }

    @PostMapping(value = "/tokenize", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TokenResponse tokenize(@RequestBody Map<String, Object> reqBody) {

        return tokenizationService.tokenize(reqBody);
    }

    @PostMapping(value = "/detokenize", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DetokenResponse detokenize(@RequestBody Map<String, Object> reqBody) {
        return tokenizationService.detokenize(reqBody);
    }
}
