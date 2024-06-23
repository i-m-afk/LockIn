package com.imafk.tokenizer.util;

import com.imafk.tokenizer.model.Data;
import org.springframework.security.crypto.keygen.KeyGenerators;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.util.LinkedHashMap;


public class TokenGenerator {
    private final Data data;
    private final LinkedHashMap<String, String> tokenizedData;
    private final String SALT = KeyGenerators.string().generateKey();

    public TokenGenerator(Data data) {
        this.data = data;
        this.tokenizedData = new LinkedHashMap<>(); // this will be used to tokenize the data
    }

    public Data dataTokenizer() {
        data.getData().forEach((k, v) -> {
            try {
                tokenizedData.put(k, makeEncryptedToken(v));
                System.out.println(k + " " + v + " " + " " + tokenizedData.get(k) + " " + makeDecryptedToken(tokenizedData.get(k)));
            } catch (IllegalBlockSizeException e) {
                throw new RuntimeException(e);
            } catch (BadPaddingException e) {
                throw new RuntimeException(e);
            }
        });
        return new Data(tokenizedData);
    }

    private String makeEncryptedToken(String plainText) throws IllegalBlockSizeException, BadPaddingException {
        return new EncryptionUtil().encrypt(plainText);
    }

    private String makeDecryptedToken(String encryptedToken) throws IllegalBlockSizeException, BadPaddingException {
        return new EncryptionUtil().decrypt(encryptedToken);
    }
}
