package com.imafk.tokenizer.service;

import com.imafk.tokenizer.model.*;
import com.imafk.tokenizer.util.TokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@Service
public class TokenizationService {

    private static final Logger logger = LoggerFactory.getLogger(TokenizationService.class);

    public TokenResponse tokenize(Map<String, Object> reqBody) {
        logger.info("Received TokenRequest: {}", reqBody);
        LinkedHashMap data = (LinkedHashMap) reqBody.get("data");
        TokenRequest tokenRequest = new TokenRequest((String) reqBody.get("id"), new Data(data));

        if (tokenRequest == null || tokenRequest.getData() == null) {
            throw new IllegalArgumentException("Invalid request or missing data");
        }
        // token generation step
        Data tokenizedResponseData = new TokenGenerator(new Data(data)).dataTokenizer();
        return new TokenResponse(tokenRequest.getId(), tokenizedResponseData);
    }

    public DetokenResponse detokenize(Map<String, Object> reqBody) {
        LinkedHashMap data = (LinkedHashMap) reqBody.get("data");
        // fill store
        LinkedHashMap<String, String> store = new LinkedHashMap<>();

        // TODO: remove this store later on
        store.put("field1", "BQGC5s");
        store.put("field2", "dbnmfF");
        store.put("fieldn", "dSxzJU");

        if (data == null) {
            throw new IllegalArgumentException("Invalid request or missing data");
        }
        TokenValidator tokenValidator = new TokenValidator(store, data);
        Fields fields = tokenValidator.validateTokens();

        return new DetokenResponse((String) reqBody.get("id"), fields);
    }

}
