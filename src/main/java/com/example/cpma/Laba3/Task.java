package com.example.cpma.Laba3;

import com.example.cpma.ReadAndWrite;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task {
    private String text = "";
    private String encrypted = "";
    private String decrypted = "";
    private List<Character> originalAlphabet;
    private Map<Character, List<Character>> cipherAlphabet = new HashMap<>();

    public List<Character> getOriginalAlphabet() {
        return originalAlphabet;
    }

    public void setOriginalAlphabet(List<Character> originalAlphabet) {
        this.originalAlphabet = originalAlphabet;
    }

    public Map<Character, List<Character>> getCipherAlphabet() {
        return cipherAlphabet;
    }

    public void setCipherAlphabet(Map<Character, List<Character>> cipherAlphabet) {
        this.cipherAlphabet = cipherAlphabet;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
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
