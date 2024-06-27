package com.imafk.tokenizer.service;

import com.imafk.jedis.TokenStorage;
import com.imafk.tokenizer.model.*;
import com.imafk.tokenizer.util.EncryptionUtil;
import com.imafk.tokenizer.util.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class TokenizationService {
    private final TokenValidator tokenValidator;
    private final EncryptionUtil encryptionUtil;
    private final TokenStorage store;
    private  final TokenGenerator tokenGenerator;

    @Autowired
    public TokenizationService(TokenValidator tokenValidator, EncryptionUtil encryptionUtil, TokenStorage store, TokenGenerator tokenGenerator){
        this.tokenValidator = tokenValidator;
        this.encryptionUtil = encryptionUtil;
        this.store = store;
        this.tokenGenerator = tokenGenerator;
    }

    public TokenResponse tokenize(Map<String, Object> reqBody) {
        LinkedHashMap data = (LinkedHashMap) reqBody.get("data");
        TokenRequest tokenRequest = new TokenRequest((String) reqBody.get("id"), new Data(data));

        if (tokenRequest == null || tokenRequest.getData() == null) {
            throw new IllegalArgumentException("Invalid request or missing data");
        }
        // token generation step
        tokenGenerator.setData(new Data(data));
        Data tokenizedResponseData = tokenGenerator.tokenizeAndStoreData();
        return new TokenResponse(tokenRequest.getId(), tokenizedResponseData);
    }


    public DetokenResponse detokenize(Map<String, Object> reqBody) {
        LinkedHashMap data = (LinkedHashMap) reqBody.get("data");
        if (data == null) {
            throw new IllegalArgumentException("Invalid request or missing data");
        }
        tokenValidator.setReqData(data);
        Fields fields = tokenValidator.validateTokens();

        return new DetokenResponse((String) reqBody.get("id"), fields);
    }

}
