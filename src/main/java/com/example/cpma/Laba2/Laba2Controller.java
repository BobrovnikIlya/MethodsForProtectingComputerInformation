package com.example.cpma.Laba2;

import com.example.cpma.ReadAndWrite;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
@RestController
public class Laba2Controller {
    Task task = new Task();
    boolean enc = true;

    String option = "1";
    boolean check = false;
    String inputFile = "src/main/resources/files/laba2/input"; // Имя файла с вводной строкой
    String alphabetFile = "src/main/resources/files/laba2/originalAlphabet.txt"; //
    String monoAlphabetFile = "src/main/resources/files/laba2/monoAlphabet.txt"; //
    String poliAlphabetFile = "src/main/resources/files/laba2/poliAlphabet.txt";


    @GetMapping("/laba2")
    public ModelAndView viewLaba2() {
        System.out.println("Запуск страницы со второй лабой");

        ModelAndView view = new ModelAndView("laba2");

        view.addObject("textInput", task.getText());
        if(enc)
            view.addObject("textOutput", task.getEncrypted());
        else  view.addObject("textOutput", task.getDecrypted());
        view.addObject("slogan", task.getSlogan());
        view.addObject("isChecked", check);
        view.addObject("selectedOption", option);

        return view;
    }
    @PostMapping("/loadData")
    public ModelAndView loadData(@RequestParam("textInput") String textInput) throws IOException {
        if(textInput.isEmpty()){
            task.setText(ReadAndWrite.readFromFile(inputFile));
        }else{
            task.setText(textInput);
        }
        return new ModelAndView("redirect:/laba2");
    }
    @PostMapping("/loadCipher")
    public ModelAndView loadCipher(@RequestParam("selectedOption") String selectedOption, @RequestParam("slogan") String slogan, @RequestParam(value = "isChecked", required = false) boolean isChecked) throws IOException {
        option = selectedOption;
        check = isChecked;
        if (option.equals("mono")) {
            System.out.println("Вошел в моно");
            task.setOriginalAlphabet(ReadAndWrite.readFromFile(alphabetFile));
            if(check){
                task.setSlogan(slogan);
                task.setCipherAlphabet(SingleСipher.shuffleSloganString(slogan,task.getOriginalAlphabet()));
            }
            else {
                task.setCipherAlphabet(SingleСipher.shuffleString(ReadAndWrite.readFromFile(alphabetFile)));
            }
            SingleСipher.generateAndSaveCipherAlphabet(task.getOriginalAlphabet(), task.getCipherAlphabet(), task.getDecryptionMap(), task.getEncryptionMap());
            ReadAndWrite.writeToFile(monoAlphabetFile, task.getCipherAlphabet());
            System.out.println(task.getCipherAlphabet());
        } else if (option.equals("poli")) {
            System.out.println("Вошел в поли");
            task.setOriginalAlphabet(ReadAndWrite.readFromFile(alphabetFile));

            if(check){
                task.setSlogan(slogan);
                task.setPoliAlphabet(PoliCipher.shuffleSloganString(slogan, task.getOriginalAlphabet()));
            }
            else {
                task.setPoliAlphabet(PoliCipher.shuffleString(task.getOriginalAlphabet()));
            }
            ReadAndWrite.writeListToFile(poliAlphabetFile, task.getPoliAlphabet());
            System.out.println(task.getPoliAlphabet());
        }
        return new ModelAndView("redirect:/laba2");
    }
    @PostMapping("/encrypt")
    public ModelAndView encrypt() {
        if (option.equals("mono")) {
            task.setEncrypted(SingleСipher.encrypt(task.getText(), task.getEncryptionMap()));
        }else{
            task.setEncrypted(PoliCipher.encrypt(task.getOriginalAlphabet(), task.getPoliAlphabet(), task.getText()));
        }
        enc = true;
        System.out.println(task.getEncrypted());
        return new ModelAndView("redirect:/laba2");
    }
    @PostMapping("/decrypt")
    public ModelAndView decrypt() {
        if (option.equals("mono")) {
            task.setDecrypted(SingleСipher.decrypt(task.getEncrypted(), task.getDecryptionMap()));
        }else{
            task.setDecrypted(PoliCipher.decrypt(task.getOriginalAlphabet(), task.getPoliAlphabet(), task.getEncrypted()));
        }
        System.out.println(task.getDecrypted());
        enc = false;
        return new ModelAndView("redirect:/laba2");
    }
}
