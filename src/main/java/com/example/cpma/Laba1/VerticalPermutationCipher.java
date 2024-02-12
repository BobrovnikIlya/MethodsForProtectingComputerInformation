package com.example.cpma.Laba1;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class VerticalPermutationCipher {
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
    public static String encrypt(String text, ArrayList<Integer> permutation) {
        int[] keyArray = permutation.stream().mapToInt(Integer::intValue).toArray();
        int n = keyArray.length;
        int rows = (int) Math.ceil((double) text.length() / n);
        char[][] grid = new char[rows][n];
        int k = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < n; j++) {
                if (k < text.length()) {
                    grid[i][j] = text.charAt(k++);
                } else {
                    grid[i][j] = ' ';
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for (int key : keyArray) {
            for (int i = 0; i < rows; i++) {
                result.append(grid[i][key - 1]);
            }
        }
        return result.toString();
    }

    // Метод для расшифровки строки по вертикальной перестановке
    public static String decrypt(String encryptedText, ArrayList<Integer> permutation) {
        int[] keyArray = permutation.stream().mapToInt(Integer::intValue).toArray();
        int n = keyArray.length;
        int rows = (int) Math.ceil((double) encryptedText.length() / n);
        char[][] grid = new char[rows][n];
        int k = 0;

        for (int key : keyArray) {
            for (int i = 0; i < rows; i++) {
                grid[i][key - 1] = encryptedText.charAt(k++);
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    result.append(grid[i][j]);
                }
            }
        }
        return result.toString();
    }
    // Метод для чтения строки из файла
    public static String readFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        reader.close();
        return line;
    }
    // Метод для записи строки в файл
    public static void writeToFile(String filename, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(content);
        writer.close();
    }
    public static char[][] textSpell(String text, int columns){
        // Определение количества строк
        int rows = (int) Math.ceil((double) text.length() / columns);

        // Создание двумерного массива char
        char[][] twoDCharArray = new char[rows][columns];

        // Заполнение двумерного массива char
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (index < text.length()) {
                    twoDCharArray[i][j] = text.charAt(index++);
                } else {
                    twoDCharArray[i][j] = ' '; // Если символов не хватает, заполните другим символом
                }
            }
        }

        // Вывод двумерного массива char
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(twoDCharArray[i][j] + " ");
            }
            System.out.println();
        }
        return twoDCharArray;
    }
}
