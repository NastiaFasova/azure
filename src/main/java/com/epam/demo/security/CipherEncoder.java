package com.epam.demo.security;

public interface CipherEncoder {
    String decrypt(String str, int offset);

    String encrypt(String str, int offset);
}
