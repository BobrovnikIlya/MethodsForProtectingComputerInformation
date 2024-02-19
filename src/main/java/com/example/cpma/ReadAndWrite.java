package com.example.cpma;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReadAndWrite {
    public List<Character> readStringFromFileToList(String filePath) {
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
    public void writeMapToFile(Map<Character, List<Character>> map, String filePath) {
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
