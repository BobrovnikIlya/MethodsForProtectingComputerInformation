package com.example.cpma.Laba1;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class VerticalPermutationCipher {
    public static void main(String[] args) {
        String inputFile = "src/main/resources/input.txt"; // Имя файла с вводной строкой
        String encryptedFile = "src/main/resources/encrypted.txt"; // Файл для зашифрованной строки
        String decryptedFile = "src/main/resources/decrypted.txt"; // Файл для расшифрованной строки

        int key = 4;

        ArrayList<Integer> permutation = new ArrayList<Integer>();// Порядок перестановки столбцов
        permutation.addAll(Permutation(key));

        try {
            String inputString = readFromFile(inputFile);

            // Шифрование
            String encryptedString = encrypt(inputString, permutation);
            writeToFile(encryptedFile, encryptedString);
            System.out.println("Зашифрованная строка сохранена в файл " + encryptedFile);

            // Расшифровка
            String decryptedString = decrypt(encryptedString, permutation);
            writeToFile(decryptedFile, decryptedString);
            System.out.println("Расшифрованная строка сохранена в файл " + decryptedFile);
        } catch (IOException e) {
            System.err.println("Ошибка ввода/вывода");
            e.printStackTrace();
        }
    }
    // Метод для генерации порядка столбцов
    public static ArrayList<Integer> Permutation(int maxNumber){
        ArrayList<Integer> randomNumbers = new ArrayList<>();
        ArrayList<Integer> allNumbers = new ArrayList<>();

        for (int i = 1; i <= maxNumber; i++) {
            allNumbers.add(i);
        }

        Random random = new Random();
        while (!allNumbers.isEmpty()) {
            int randomIndex = random.nextInt(allNumbers.size());
            int randomNumber = allNumbers.remove(randomIndex);
            randomNumbers.add(randomNumber);
        }
        return randomNumbers;
    }
    // Метод для шифрования строки по вертикальной перестановке
    private static String encrypt(String input, ArrayList<Integer> permutation) {
        System.out.println( permutation.size());
        char[][] grid = new char[permutation.size()][input.length() / permutation.size()];
        StringBuilder encrypted = new StringBuilder();

        int index = 0;
        for (int i = 0; i < permutation.size(); i++) {
            for (int j = 0; j < input.length() / permutation.size(); j++) {
                grid[permutation.get(i) - 1][j] = input.charAt(index++);
            }
        }

        for (int i = 0; i < input.length() / permutation.size(); i++) {
            for (int j = 0; j < permutation.size(); j++) {
                encrypted.append(grid[j][i]);
            }
        }

        return encrypted.toString();
    }

    // Метод для расшифровки строки по вертикальной перестановке
    private static String decrypt(String input, ArrayList<Integer> permutation) {
        char[][] grid = new char[permutation.size()][input.length() / permutation.size()];
        StringBuilder decrypted = new StringBuilder();

        int index = 0;
        for (int i = 0; i < input.length() / permutation.size(); i++) {
            for (int j = 0; j < permutation.size(); j++) {
                grid[j][i] = input.charAt(index++);
            }
        }

        for (int i = 0; i < permutation.size(); i++) {
            for (int j = 0; j < input.length() / permutation.size(); j++) {
                decrypted.append(grid[permutation.get(i) - 1][j]);
            }
        }

        return decrypted.toString();
    }
    // Метод для чтения строки из файла
    public static String readFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        reader.close();
        return line;
    }
    // Метод для записи строки в файл
    private static void writeToFile(String filename, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(content);
        writer.close();
    }
}
