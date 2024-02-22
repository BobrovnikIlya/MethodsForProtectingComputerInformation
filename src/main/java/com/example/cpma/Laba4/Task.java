package com.example.cpma.Laba4;

import com.example.cpma.ReadAndWrite;
import lombok.Data;

import java.io.IOException;

@Data
public class Task {
    private String text = "";
    private String alphabet = "";
    private String encrypted = "";
    private String decrypted = "";
    private String result = "";
    private int[][] keyMatrix;

    public void setInfo() throws IOException {
        text = ReadAndWrite.readFromFile("src/main/resources/files/laba4/input");
    }

    public void setAlphabet() throws IOException {
        this.alphabet = ReadAndWrite.readFromFile("src/main/resources/files/laba4/originalAlphabet.txt");;
    }
}
