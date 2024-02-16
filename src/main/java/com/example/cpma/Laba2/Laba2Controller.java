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

        return view;
    }
    @PostMapping("/loadData")
    public ModelAndView loadData() throws IOException {
        task.setText(ReadAndWrite.readFromFile(inputFile));
        return new ModelAndView("redirect:/laba2");
    }
    @PostMapping("/loadCipher")
    public ModelAndView loadCipher(@RequestParam("selectedOption") String selectedOption, @RequestParam("slogan") String slogan, @RequestParam(value = "isChecked", required = false) boolean isChecked) throws IOException {
        option = selectedOption;
        check = isChecked;
        if (option.equals("mono")) {
            System.out.println("фыафыа");
            task.setOriginalAlphabet(ReadAndWrite.readFromFile(alphabetFile));
            if(check){
                task.setSlogan(slogan);
                task.setCipherAlphabet(SingleСipher.shuffleSloganString(slogan,task.getOriginalAlphabet()));
            }
            else {
                task.setCipherAlphabet(SingleСipher.shuffleString(ReadAndWrite.readFromFile(alphabetFile)));
            }
            SingleСipher.generateAndSaveCipherAlphabet(task.getOriginalAlphabet(), task.getCipherAlphabet(), task.decryptionMap, task.encryptionMap);
            ReadAndWrite.writeToFile(monoAlphabetFile, task.getCipherAlphabet());
            System.out.println(task.getCipherAlphabet());
        } else if (option.equals("poli")) {

        }

        return new ModelAndView("redirect:/laba2");
    }
    @PostMapping("/encrypt")
    public ModelAndView encrypt(@RequestParam("textInput") String textInput) {
        task.setText(textInput);
        if (option.equals("mono")) {
            task.setEncrypted(SingleСipher.encrypt(task.getText(), task.encryptionMap));
        }else{

        }
        enc = true;
        System.out.println(task.getEncrypted());
        return new ModelAndView("redirect:/laba2");
    }
    @PostMapping("/decrypt")
    public ModelAndView decrypt() {
        if (option.equals("mono")) {
            task.setDecrypted(SingleСipher.decrypt(task.getEncrypted(), task.decryptionMap));
        }else{

        }
        System.out.println(task.getDecrypted());
        enc = false;
        return new ModelAndView("redirect:/laba2");
    }
}
