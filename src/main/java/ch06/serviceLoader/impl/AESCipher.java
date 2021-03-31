package ch06.serviceLoader.impl;

import ch06.serviceLoader.Cipher;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AESCipher implements Cipher {

    javax.crypto.Cipher encrypter;
    javax.crypto.Cipher decrypter;

    public AESCipher() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        encrypter = javax.crypto.Cipher.getInstance("AES/ECB/PKCS5Padding");
        decrypter = javax.crypto.Cipher.getInstance("AES/ECB/PKCS5Padding");
    }

    byte[] padKey(byte[]  sourceKey) {
        byte[] result = new byte[16];

        for (int i = 0; i < result.length ; ++i ) {
            result[i] = sourceKey[i % sourceKey.length];
        }

        return result;
    }

    @Override
    public byte[] encrypt(byte[] source, byte[] key) {
        byte[] result = new byte[0];
        try {
            SecretKeySpec secretKey = new SecretKeySpec(padKey(key), "AES");
            encrypter.init(javax.crypto.Cipher.ENCRYPT_MODE, secretKey);
            result = encrypter.doFinal(source);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public byte[] decrypt(byte[] source, byte[] key) {
        byte[] result = new byte[0];
        try {
            SecretKeySpec secretKey = new SecretKeySpec(padKey(key), "AES");
            decrypter.init(javax.crypto.Cipher.DECRYPT_MODE, secretKey);
            result = decrypter.doFinal(source);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int strength() {
        return 10;
    }
}
