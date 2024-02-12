package com.example.cpma.Laba1;

import java.util.ArrayList;

public class Task {
    private int key;
    private String text;
    private ArrayList<Integer> permutation;
    private String encrypted;
    private String decrypted;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public ArrayList<Integer> getPermutation() {
        return permutation;
    }

    public void setPermutation(ArrayList<Integer> permutation) {
        this.permutation = permutation;
    }

    public String getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(String encrypted) {
        this.encrypted = encrypted;
    }

    public String getDecrypted() {
        return decrypted;
    }

    public void setDecrypted(String decrypted) {
        this.decrypted = decrypted;
    }
}
