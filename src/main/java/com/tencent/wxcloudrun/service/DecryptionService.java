package com.tencent.wxcloudrun.service;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Service
public class DecryptionService {

    private static final String ALGORITHM = "AES";
    private static final String KEY = "your-secret-key-here";

    public String decrypt(String encryptedData) {
        try {
            // Base64解码
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);

            // 初始化解密器
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // 解密
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes, StandardCharsets.UTF_8);

        } catch (Exception e) {
            throw new RuntimeException("解密失败", e);
        }
    }

    // RSA解密示例
    public String decryptWithRSA(String encryptedData, String privateKey) {
        try {
            byte[] data = Base64.getDecoder().decode(encryptedData);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey rsaPrivateKey = keyFactory.generatePrivate(keySpec);

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);

            byte[] decryptedData = cipher.doFinal(data);
            return new String(decryptedData, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("RSA解密失败", e);
        }
    }
}
