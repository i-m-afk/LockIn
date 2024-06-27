package com.imafk.tokenizer.util;

import com.imafk.jedis.RedisConnection;
import com.imafk.jedis.TokenStorage;
import com.imafk.tokenizer.model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.util.LinkedHashMap;


@Component
public class TokenGenerator {
    private Data data;
    private final LinkedHashMap<String, String> tokenizedData = new LinkedHashMap<>(); // this will be used to tokenize the data
    private final TokenStorage store;
    private final EncryptionUtil encryptionUtil;

   @Autowired
    public TokenGenerator(TokenStorage store, EncryptionUtil encryptionUtil) {
        this.store = store;
        this.encryptionUtil = encryptionUtil;
    }

    public void setData(Data data) {
       this.data = data;
    }

    public Data tokenizeAndStoreData() {
        data.getData().forEach((k, v) -> {
            try {
                String encryptedToken = makeEncryptedToken(v);
                store.storeToken(k, encryptedToken);
                tokenizedData.put(k, encryptedToken);
            } catch (IllegalBlockSizeException | BadPaddingException e) {
                throw new RuntimeException(e);
            }
        });
        return new Data(tokenizedData);
    }

    private String makeEncryptedToken(String plainText) throws IllegalBlockSizeException, BadPaddingException {
        return encryptionUtil.encrypt(plainText);
    }

}
