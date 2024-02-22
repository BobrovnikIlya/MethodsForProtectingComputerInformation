package com.example.cpma.Laba4;

import java.util.HashMap;
import java.util.Random;

public class AnalyticalTransformations {
    private  static int numberChair;
    // Метод генерации ключевой матрицы
    public static int[][] generateKeyMatrix(int n) {
        int[][] keyMatrix = new int[n][n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                keyMatrix[i][j] = random.nextInt(20) + 1; // Генерация чисел от 1 до 20
            }
        }
        if (MatrixUtils.isMatrixInvertible(keyMatrix)) {
            System.out.println("Матрица обратима");
            return keyMatrix;
        } else {
            System.out.println("Матрица необратима");
            keyMatrix = generateKeyMatrix(n);
        }
        System.out.println("Матрица:");
        printMatrix(keyMatrix);
        return keyMatrix;
    }
    // Метод для удаления n символов с конца строки
    public static String removeCharsFromEnd(String str, int n) {
        System.out.println(n);
        if (str == null || str.isEmpty() || n >= str.length()) {
            return ""; // Пустая строка, если входная строка пуста или n больше или равно длине строки
        }
        int endIndex = str.length() - n;
        return str.substring(0, endIndex); // Возвращаем подстроку без последних n символов
    }
    public static int[] getIndexArray(String text, String alphabet, int n) {
        HashMap<Character, Integer> alphabetMap = new HashMap<>();
        int arrayLength = ((text.length() + n - 1) / n) * n;
        numberChair = arrayLength - text.length();
        int[] indices = new int[arrayLength];
        // Создание HashMap сопоставляющего буквы алфавита и их индексы
        for (int i = 0; i < alphabet.length(); i++) {
            alphabetMap.put(alphabet.charAt(i), i);
        }
        // Заполнение массива индексов для каждой буквы в тексте
        for (int i = 0; i < text.length(); i++) {
            char currentChar = Character.valueOf(text.charAt(i));
            System.out.println(currentChar);
            if (alphabetMap.containsKey(currentChar)) {
                indices[i] = alphabetMap.get(currentChar);
            }
        }
        for (int i = text.length(); i < arrayLength; i++) {
            indices[i] = 63;
        }
        return indices;
    }
    // Метод для создания индексированной строки на основе массива чисел и алфавита
    public static String getIndexString(int[] numbers, String alphabet) {
        HashMap<Integer, Character> indexMap = new HashMap<>();
        for (int i = 0; i < alphabet.length(); i++) {
            indexMap.put(i, alphabet.charAt(i));
        }

        StringBuilder result = new StringBuilder();
        for (int number : numbers) {
            if (indexMap.containsKey(number)) {
                result.append(indexMap.get(number));
                System.out.println(number+" : "+ indexMap.get(number));
            } else {
                result.append("*"); // Заменяем символ "*" для чисел, для которых нет буквы в алфавите
            }
        }

        return result.toString();
    }

    // Метод для обратного умножения матриц
    private static int[] reverseMultiplyMatrix(double[][] matrix1, int[] matrix2Row) {
        matrix1 = inverseMatrix(matrix1);
        int[] result = new int[matrix1.length];

        for (int i = 0; i < matrix1.length; i++) {
            double sum = 0;
            for (int j = 0; j < matrix1[i].length; j++) {
                sum += matrix1[i][j] * matrix2Row[j];
            }
            result[i] = (int) Math.round(sum);
        }
        return result;
    }
    private static int[] multiplyMatrix(int[][] matrix1, int[] matrix2Row) {
        int[] result = new int[matrix1.length];

        for (int i = 0; i < matrix1.length; i++) {
            int sum = 0;
            for (int j = 0; j < matrix1[i].length; j++) {
                sum += matrix1[i][j] * matrix2Row[j];
            }
            result[i] = sum;
        }

        return result;
    }
    // Метод для нахождения обратной матрицы
    public static double[][] inverseMatrix(double[][] matrix) {
        int n = matrix.length;
        // Создание расширенной матрицы, добавляя к исходной единичную матрицу
        double[][] augmentedMatrix = new double[n][2 * n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, augmentedMatrix[i], 0, n);
            augmentedMatrix[i][i + n] = 1;
        }
        // Приведение к диагональному виду
        for (int i = 0; i < n; i++) {
            double[] row = augmentedMatrix[i];
            double leadingCoefficient = row[i];
            for (int j = 0; j < 2 * n; j++) {
                row[j] /= leadingCoefficient;
            }
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double[] otherRow = augmentedMatrix[k];
                    double factor = otherRow[i];
                    for (int j = 0; j < 2 * n; j++) {
                        otherRow[j] -= factor * row[j];
                    }
                }
            }
        }
        // Извлечение обратной матрицы из расширенной
        double[][] inverse = new double[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(augmentedMatrix[i], n, inverse[i], 0, n);
        }

        System.out.println("Инверсивная матрица");
        printMatrix(inverse);
        return inverse;
    }
    public static String convertArrayToString(int[][] encryptedString) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < encryptedString.length; i++) {
            for (int j = 0; j < encryptedString[i].length; j++) {
                result.append(encryptedString[i][j]).append(" ");
            }
        }

        return result.toString();
    }
    // Метод для шифрования строки с использованием ключевой матрицы
    public static String encryptString(int[][] keyMatrix, String inputString, String alphabet, int n) {
        int [] array = getIndexArray(inputString, alphabet, n);
        System.out.println("Индексы букв в тексте:");

        //Разбиение массива
        System.out.println("Преобразование массива:");
        int [][] bArray = convertToMultiArray(array, n);
        printMatrix(bArray);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < bArray.length; i++) {
            int[] row = bArray[i];
            // Перемножение матриц keyMatrix и текущей строки из bArray
            int[] encryptedRow = multiplyMatrix(keyMatrix, row);

            // Преобразование массива в строку и дополнение к результату
            for (int num : encryptedRow) {
                result.append(num).append(" ");
            }
        }
        return result.toString();
    }
    // Метод для расшифровки с использованием обратного преобразования матриц
    public static String decryptString(int[][] matrix1, String encryptString, String alphabet, int n) {


        double [][] keyMatrix = new double [matrix1.length][matrix1.length];
        for(int i = 0; i<matrix1.length; i++){
            for(int j = 0; j < matrix1[i].length; j++){
                keyMatrix[i][j] = (double) matrix1[i][j];
            }
        }

        int [] arrayEncr = convertStringToIntArray(encryptString);
        int [][]encryptedArray = convertToMultiArray(arrayEncr, n);
        printMatrix(encryptedArray);

        StringBuilder indexText = new StringBuilder();

        for (int i = 0; i < encryptedArray.length; i++) {
            int[] row = encryptedArray[i];
            // Обратное преобразование для расшифровки
            int[] decryptedRow = reverseMultiplyMatrix(keyMatrix, row);
            // Преобразование массива в строку и дополнение к результату
            for (int num : decryptedRow) {
                indexText.append(num).append(" ");
            }
        }

        System.out.println(indexText.toString());
        int[] resultArray;
        resultArray = convertStringToIntArray(indexText.toString());

        String result = getIndexString(resultArray, alphabet);

        result = removeCharsFromEnd(result, numberChair);

        return result;
    }

    public static int[][] convertToMultiArray(int[] oneDimArray, int n) {
        int size = (int) Math.ceil((double) oneDimArray.length / n); // Определение количества строк
        int[][] multiDimArray = new int[size][n];

        int index = 0;

        // Заполнение многомерного массива
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < n; j++) {
                if (index < oneDimArray.length) {
                    multiDimArray[i][j] = oneDimArray[index++];
                } else {
                    multiDimArray[i][j] = 0; // Если недостаточно элементов, заполняем нулями
                }
            }
        }
        return multiDimArray;
    }
    // Метод для преобразования строки в массив int
    public static int[] convertStringToIntArray(String numbersString) {
        String[] numberStrings = numbersString.split(" "); // Разделяем строку по пробелам
        int[] intArray = new int[numberStrings.length];
        for (int i = 0; i < numberStrings.length; i++) {
            intArray[i] = Integer.parseInt(numberStrings[i]); // Преобразование строки в int
        }
        return intArray;
    }

    // Метод для вывода матрицы в консоль
    public static void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
