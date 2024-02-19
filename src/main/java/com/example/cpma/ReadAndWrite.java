package com.example.cpma;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadAndWrite {
    public static List<Character> readStringFromFileToList(String filePath) {
        List<Character> charList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int charCode;
            while ((charCode = reader.read()) != -1) {
                char character = (char) charCode;
                charList.add(character);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Обработка ошибок чтения из файла
        }
        return charList;
    }
    public static Map<Character, List<Character>> readMapFromFile(String filePath) {
        Map<Character, List<Character>> map = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(": ");
                if (parts.length == 2) {
                    char key = parts[0].charAt(0);
                    List<Character> values = new ArrayList<>();
                    for (char c : parts[1].toCharArray()) {
                        if (c != '[' && c != ']' && c != ',') {
                            values.add(c);
                        }
                    }
                    map.put(key, values);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Обработка ошибок чтения из файла
        }
        return map;
    }
    public static void writeMapToFile(Map<Character, List<Character>> map, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<Character, List<Character>> entry : map.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace(); // Обработка ошибок записи в файл
        }
    }
    public static void writeListToFile(String filePath, List<String> strings) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String str : strings) {
                writer.write(str);
                writer.newLine();
            }
            System.out.println("Список строк успешно записан в файл: " + filePath);
        } catch (IOException e) {
            System.err.println("Ошибка при записи списка строк в файл: " + e.getMessage());
        }
    }
    public static String readFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        reader.close();
        return line;
    }
    public static void writeToFile(String filename, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(content);
        writer.close();
    }
}
