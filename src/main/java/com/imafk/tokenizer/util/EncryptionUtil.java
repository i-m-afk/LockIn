package com.imafk.tokenizer.util;

import com.privacylogistics.FF3Cipher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

// TODO: Fix application.properties integration, remove hardcoded values

@Configuration
@PropertySource("classpath:application.properties")
@Component("encryptionUtil")
public class EncryptionUtil {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz012345679";

    @Value("${encryption.key}")
    private String key = "2DE79D232DF5585D68CE47882AE256D6";

    @Value("${encryption.tweak}")
    private String tweak = "CBD09280979564";

    private FF3Cipher getCipher() {
//        System.out.println("This is the key: " + key + " " + key.length());
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
}
