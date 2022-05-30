package com.kpi.demo.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CipherEncoderImpl implements PasswordEncoder {
    private static final char LETTER_A = 'a';
    private static final char LETTER_Z = 'z';
    private static final int ALPHABET_SIZE = LETTER_Z - LETTER_A + 1;
    private static final int OFFSET = 13;

    @Override
    public String encode(CharSequence charSequence) {
        StringBuilder result = new StringBuilder();

        for (char character : charSequence.toString().toCharArray()) {
            if (character != ' ') {
                int originalAlphabetPosition = character - LETTER_A;
                int newAlphabetPosition = (originalAlphabetPosition + OFFSET) % ALPHABET_SIZE;
                char newCharacter = (char) (LETTER_A + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }

        return result.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        String decrypted = encode(charSequence);
        return decrypted.equals(s);
    }
}
