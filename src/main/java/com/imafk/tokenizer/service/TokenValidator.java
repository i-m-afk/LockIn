package com.imafk.tokenizer.service;


import com.imafk.tokenizer.model.Field;
import com.imafk.tokenizer.model.Fields;
import com.imafk.tokenizer.util.EncryptionUtil;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.util.LinkedHashMap;
import java.util.Objects;

public class TokenValidator {
    // TODO: replace with redis
    private LinkedHashMap<String, String> store;
    private LinkedHashMap<String, String> reqData;
    private EncryptionUtil encryptionUtil = new EncryptionUtil();

    public TokenValidator(LinkedHashMap<String, String> store, LinkedHashMap<String, String> reqData) {
        this.store = store;
        this.reqData = reqData;
    }

    public Fields validateTokens() {
        LinkedHashMap<String, Field> fields = new LinkedHashMap<>();
        reqData.forEach((k, v) -> {
            String storedEncryptedValue, reqEncryptedValue;
            storedEncryptedValue = store.get(k);
            reqEncryptedValue = reqData.get(k);
            try {
                if(!Objects.equals(storedEncryptedValue, reqEncryptedValue) || storedEncryptedValue == null || reqEncryptedValue == null){
                    fields.put(k, new Field(false, ""));
                    return;
                }
                String decryptedValue =  encryptionUtil.decrypt(storedEncryptedValue);
                boolean found = storedEncryptedValue != null && reqData.get(k) != null;
                if (found) fields.put(k, new Field(true, decryptedValue));
                else fields.put(k, new Field(false, ""));
            } catch (IllegalBlockSizeException | BadPaddingException e) {
                throw new RuntimeException(e);
            }
        });
        return new Fields(fields);
    }
}
