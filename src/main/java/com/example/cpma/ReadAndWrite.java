package com.example.cpma;

import java.io.*;
import java.util.List;

public class ReadAndWrite {
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
