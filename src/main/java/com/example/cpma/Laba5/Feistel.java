package com.example.cpma.Laba5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Feistel {
    public static String[] generateKeys(int rounds, String alphabet){
        List<String> keys = new ArrayList<>();
        Random random = new Random();
        int length = random.nextInt(6) + 5; // Генерация случайной длины от 5 до 10

        for (int i = 0; i < rounds; i++) {
            StringBuilder key = new StringBuilder();
            while (key.length() < length) {
                key.append(alphabet.charAt(random.nextInt(alphabet.length())));
            }

            keys.add(key.toString());
        }
        String[] result = new String[keys.size()];
        keys.toArray(result);
        return result;
    }

    public static String encrypt(String text, String[] keys, int rounds) {
        if (text.length() % 2 == 1) {
            text += " ";
        }

        int blockSize = text.length() / 2;
        String left = text.substring(0, blockSize);
        String right = text.substring(blockSize);

        for (int i = 0; i < rounds; i++) {
            String newLeft = right;
            String newRight = xor(left, function(right, keys[i]));
            left = newLeft;
            right = newRight;
        }

        return left + right;
    }

    public static String decrypt(String cypherText, String[] keys, int rounds) {
        int blockSize = cypherText.length() / 2;
        String left = cypherText.substring(0, blockSize);
        String right = cypherText.substring(blockSize);

        for (int i = rounds - 1; i >= 0; i--) {
            String newRight = left;
            String newLeft = xor(right, function(left, keys[i]));
            left = newLeft;
            right = newRight;
        }

        return left + right;
    }

    public static String xor(String a, String b) {
        int minLength = Math.min(a.length(), b.length());

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < minLength; i++) {
            result.append((char) (a.charAt(i) ^ b.charAt(i)));
        }

        return result.toString();
    }

    public static String function(String data, String key) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            output.append((char) (data.charAt(i) + key.charAt(i % key.length()) % 256));
        }

        return output.toString();
    }

}
