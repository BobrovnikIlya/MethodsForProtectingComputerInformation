package com.example.cpma.Laba4;

public class MatrixUtils {
    public static boolean isMatrixInvertible(int[][] matrix) {
        // Проверяем, что матрица квадратная
        if (matrix.length != matrix[0].length) {
            return false; // Неквадратная матрица не обратима
        }
        // Вычисляем определитель матрицы
        int det = calculateDeterminant(matrix);
        // Матрица обратима, если её определитель не равен нулю
        return det != 0;
    }

    // Метод для вычисления определителя матрицы (рекурсивный алгоритм)
    public static int calculateDeterminant(int[][] matrix) {
        int n = matrix.length;

        if (n == 1) {
            return matrix[0][0];
        }

        int det = 0;
        int sign = 1;

        for (int i = 0; i < n; i++) {
            int[][] minor = getMinor(matrix, 0, i);
            det += sign * matrix[0][i] * calculateDeterminant(minor);
            sign = -sign;
        }

        return det;
    }

    // Метод для получения минора матрицы
    public static int[][] getMinor(int[][] matrix, int row, int col) {
        int n = matrix.length;
        int[][] minor = new int[n - 1][n - 1];

        for (int i = 0, p = 0; i < n; i++) {
            if (i == row) {
                continue;
            }

            for (int j = 0, q = 0; j < n; j++) {
                if (j == col) {
                    continue;
                }

                minor[p][q] = matrix[i][j];
                q++;
            }

            p++;
        }

        return minor;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {0, 1, 4},
                {5, 6, 0}
        };

        if (isMatrixInvertible(matrix)) {
            System.out.println("Матрица обратима");
        } else {
            System.out.println("Матрица необратима");
        }
    }
}