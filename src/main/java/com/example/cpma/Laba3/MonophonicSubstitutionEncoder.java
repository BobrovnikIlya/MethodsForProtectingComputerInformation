package com.example.cpma.Laba3;

import java.util.*;

public class MonophonicSubstitutionEncoder {
    private List<Character> chars;
    private Map<Character, List<Character>> table;
    public Map<Character, List<Character>> getRandomTableForMessage(String message) {
        Map<Character, List<Character>> table = new HashMap<>();
        Map<Character, Integer> frequencies = getFrequencies(message);
        Collections.shuffle(chars);

        for (char key : frequencies.keySet()) {
            table.put(key, new ArrayList<>());
        }

        int index = 0;
        while (index < chars.size()) {
            for (char key : frequencies.keySet()) {
                if (table.get(key).size() >= frequencies.get(key)) {
                    continue;
                }

                if (index == chars.size()) {
                    break;
                }

                table.get(key).add(chars.get(index++));
            }
        }

        table.forEach((key, value) -> {
            int cycleLength = value.size();
            int difference = frequencies.get(key) - cycleLength;
            for (int i = 0; i < difference; i++) {
                value.add(value.get(i % cycleLength));
            }
        });

        table = new TreeMap<>(table);
        return table;
    }
    public String encrypt(String message) {
        if (table == null) {
            throw new NullPointerException("Encryption table has not been initialized.");
        }

        Map<Character, List<Character>> tableCopy = getTableCopy();
        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char character = message.charAt(i);
            if (!tableCopy.containsKey(character) || tableCopy.get(character).isEmpty()) {
                throw new IllegalArgumentException("Encryption table does not contain a mapping for the character: " + character);
            }

            encryptedMessage.append(tableCopy.get(character).remove(0));
        }

        return encryptedMessage.toString();
    }

    public String decrypt(String message) {
        if (table == null) {
            throw new NullPointerException("Encryption table has not been initialized.");
        }

        Map<Character, List<Character>> tableCopy = getTableCopy();
        StringBuilder decryptedMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char character = message.charAt(i);
            Map.Entry<Character, List<Character>> entry = tableCopy.entrySet().stream()
                    .filter(kv -> kv.getValue().contains(character))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Encryption table does not contain a mapping for the character: " + character));

            decryptedMessage.append(entry.getKey());
            entry.getValue().remove((Character) character);
            if (entry.getValue().isEmpty()) {
                tableCopy.remove(entry.getKey());
            }
        }

        return decryptedMessage.toString();
    }
    public Map<Character, Integer> getFrequencies(String message) {
        Map<Character, Integer> frequencies = new HashMap<>();
        for (int i = 0; i < message.length(); i++) {
            char character = message.charAt(i);
            frequencies.put(character, frequencies.getOrDefault(character, 0) + 1);
        }
        return frequencies;
    }

    private Map<Character, List<Character>> getTableCopy() {
        Map<Character, List<Character>> tableCopy = new HashMap<>();
        for (Map.Entry<Character, List<Character>> entry : table.entrySet()) {
            tableCopy.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        return tableCopy;
    }
}
