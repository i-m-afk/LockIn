package com.imafk.tokenizer.util;

import com.privacylogistics.FF3Cipher;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

@Configuration
@Component("encryptionUtil")
public class EncryptionUtil {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz012345679";

    @Value("${tokenizer.encryption.key}")
    private String key;

    @Value("${tokenizer.encryption.tweak}")
    private String tweak;

    private FF3Cipher getCipher() {
        return new FF3Cipher(key, tweak, ALPHABET);
    }

    public String encrypt(String plainText) throws IllegalBlockSizeException, BadPaddingException {
        FF3Cipher cipher = getCipher();
        return cipher.encrypt(plainText);
    }

    public String decrypt(String cipherText) throws IllegalBlockSizeException, BadPaddingException {
        FF3Cipher cipher = getCipher();
        return cipher.decrypt(cipherText);
    }

    @PostConstruct
    public void printProperty() {
        System.out.println("Cipher Config: " + tweak + " " + key);
    }
}
