package ch06.serviceLoader.impl;

import ch06.serviceLoader.Cipher;

import java.util.Arrays;

public class NullCipher implements Cipher {
    @Override
    public byte[] encrypt(byte[] source, byte[] key) {
        byte[] result = Arrays.copyOf(source, source.length);
        return result;
    }

    @Override
    public byte[] decrypt(byte[] source, byte[] key) {
        byte[] result = Arrays.copyOf(source, source.length);
        return result;
    }

    @Override
    public int strength() {
        return 888;
    }
}
