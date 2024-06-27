package com.imafk.tokenizer.service;


import com.imafk.jedis.TokenStorage;
import com.imafk.tokenizer.model.Field;
import com.imafk.tokenizer.model.Fields;
import com.imafk.tokenizer.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.util.LinkedHashMap;
import java.util.Objects;

@Service
public class TokenValidator {
    private LinkedHashMap<String, String> reqData;
    private final TokenStorage store;
    private final EncryptionUtil encryptionUtil;

    @Autowired
    public TokenValidator(TokenStorage store, EncryptionUtil encryptionUtil) {
        this.store = store;
        this.encryptionUtil = encryptionUtil;
    }

    public void setReqData(LinkedHashMap<String, String> reqData) {
        this.reqData = reqData;
    }

    public Fields validateTokens() {
        LinkedHashMap<String, Field> fields = new LinkedHashMap<>();
        reqData.forEach((k, v) -> {
            String storedEncryptedValue, reqEncryptedValue;
            storedEncryptedValue = store.retrieveToken(k);
            reqEncryptedValue = reqData.get(k);
            try {
                if (!Objects.equals(storedEncryptedValue, reqEncryptedValue) || storedEncryptedValue == null) {
                    fields.put(k, new Field(false, ""));
                    return;
                }
                // decrypt token
                String decryptedValue = encryptionUtil.decrypt(storedEncryptedValue);
                boolean found = reqData.get(k) != null;
                if (found) fields.put(k, new Field(true, decryptedValue));
                else fields.put(k, new Field(false, ""));
            } catch (IllegalBlockSizeException | BadPaddingException e) {
                throw new RuntimeException(e);
            }
        });
        return new Fields(fields);
    }
}
