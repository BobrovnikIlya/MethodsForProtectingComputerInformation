package com.example.cpma.Laba2;

import java.util.*;

public class SingleСipher {
    public static void generateAndSaveCipherAlphabet(String originalAlphabet, String cipherAlphabet, Map<Character, Character> decryptionMap,  Map<Character, Character> encryptionMap) {
        for (int i = 0; i < originalAlphabet.length(); i++) {
            char originalChar = originalAlphabet.charAt(i);
            char cipherChar = cipherAlphabet.charAt(i);
            encryptionMap.put(originalChar, cipherChar);
            decryptionMap.put(cipherChar, originalChar);
        }
    }
    public static String shuffleString(String input) {
        List<Character> characters = new ArrayList<>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }

        Collections.shuffle(characters, new Random());

        StringBuilder shuffledString = new StringBuilder();
        for (char c : characters) {
            shuffledString.append(c);
        }

        return shuffledString.toString();
    }
    public static String encrypt(String text, Map<Character, Character> encryptionMap) {
        StringBuilder encryptedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            char encryptedChar = encryptionMap.getOrDefault(c, c);
            encryptedText.append(encryptedChar);
        }
        return encryptedText.toString();
    }
    // Метод для расшифровки строки
    public static String decrypt(String encryptedText, Map<Character, Character> decryptionMap) {
        StringBuilder decryptedText = new StringBuilder();
        for (char c : encryptedText.toCharArray()) {
            char decryptedChar = decryptionMap.getOrDefault(c, c);
            decryptedText.append(decryptedChar);
        }
        return decryptedText.toString();
    }
}
