package com.example.cpma.Laba2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Task {
    private String text = "";
    private String encrypted = "";
    private String decrypted = "";
    private String originalAlphabet = "";
    private String slogan = "";
    private String cipherAlphabet = "";
    public Map<Character, Character> encryptionMap = new HashMap<>();
    public Map<Character, Character> decryptionMap = new HashMap<>();

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public Map<Character, Character> getEncryptionMap() {
        return encryptionMap;
    }

    public void setEncryptionMap(Map<Character, Character> encryptionMap) {
        this.encryptionMap = encryptionMap;
    }

    public Map<Character, Character> getDecryptionMap() {
        return decryptionMap;
    }

    public void setDecryptionMap(Map<Character, Character> decryptionMap) {
        this.decryptionMap = decryptionMap;
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

    public String getOriginalAlphabet() {
        return originalAlphabet;
    }

    public void setOriginalAlphabet(String originalAlphabet) {
        this.originalAlphabet = originalAlphabet;
    }

    public String getCipherAlphabet() {
        return cipherAlphabet;
    }

    public void setCipherAlphabet(String cipherAlphabet) {
        this.cipherAlphabet = cipherAlphabet;
    }
}
