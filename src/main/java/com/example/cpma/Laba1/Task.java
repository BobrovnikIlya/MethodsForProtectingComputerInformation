package com.example.cpma.Laba1;

import java.util.ArrayList;

public class Task {
    private int key = 1;
    private String text = "";
    private char[][] TextSpell  = {};
    private ArrayList<Integer> permutation = new ArrayList<>();
    private String encrypted = "";
    private String decrypted = "";

    public char[][] getTextSpell() {
        return TextSpell;
    }

    public void setTextSpell(char[][] TextSpell) {
        this.TextSpell = TextSpell;
    }

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
