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
    public static String encrypt(String input, ArrayList<Integer> permutation) {
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
    public static String decrypt(String input, ArrayList<Integer> permutation) {
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
