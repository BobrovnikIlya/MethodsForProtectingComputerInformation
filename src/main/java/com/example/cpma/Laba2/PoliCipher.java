package com.example.cpma.Laba2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PoliCipher {
    public static String encrypt(String alphabet, List<String> charAlphabets, String input) {
        int charAlphabetsSize = charAlphabets.size();
        StringBuilder encryptedText = new StringBuilder();
        int charAlphabetsIndex = 0;
        for (char ch : input.toCharArray()) {
            if (alphabet.contains(String.valueOf(ch))) {
                int index = alphabet.indexOf(ch);
                String charAlphabet = charAlphabets.get(charAlphabetsIndex % charAlphabetsSize);
                encryptedText.append(charAlphabet.charAt(index % charAlphabet.length()));
                charAlphabetsIndex++;
            } else {
                encryptedText.append(ch);
            }
        }
        return encryptedText.toString();
    }
    public static String decrypt(String alphabet, List<String> charAlphabets, String encryptedText) {
        int charAlphabetsSize = charAlphabets.size();

        StringBuilder decryptedText = new StringBuilder();
        int charAlphabetsIndex = 0;

        for (char ch : encryptedText.toCharArray()) {
            if (alphabet.contains(String.valueOf(ch))) {
                int index = charAlphabets.get(charAlphabetsIndex % charAlphabetsSize).indexOf(ch);
                decryptedText.append(alphabet.charAt(index));
                charAlphabetsIndex++;
            } else {
                decryptedText.append(ch);
            }
        }
        return decryptedText.toString();
    }
    public static List<String> shuffleSloganString(String slogan, String input) {
        List<String> poliAlphabet = new ArrayList<>();
        poliAlphabet = shuffleString(input);
        List<String> modifiedAlphabet = new ArrayList<>();
        // Удаляем из каждой строки в str2 все символы из str1 и добавляем в modifiedStrings
        for (String s : poliAlphabet) {
            String modifiedString = s;
            for (int i = 0; i < slogan.length(); i++) {
                char currentChar = slogan.charAt(i);
                modifiedString = modifiedString.replaceAll(String.valueOf(currentChar), "");
            }
            modifiedAlphabet.add(slogan + modifiedString);
        }
        return modifiedAlphabet;
    }
    public static List<String> shuffleString(String input) {
        List<String> shuffledStrings = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            List<Character> chars = new ArrayList<>();
            for (char c : input.toCharArray()) {
                chars.add(c);
            }
            Collections.shuffle(chars, new Random(System.nanoTime()));  // Перемешиваем символы строки
            StringBuilder shuffled = new StringBuilder();
            for (char c : chars) {
                shuffled.append(c);
            }
            shuffledStrings.add(shuffled.toString());
        }
        return shuffledStrings;
    }
}
