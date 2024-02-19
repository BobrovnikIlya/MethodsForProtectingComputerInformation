package com.example.cpma.Laba3;

import com.example.cpma.Laba2.PoliCipher;
import com.example.cpma.Laba2.SingleСipher;
import com.example.cpma.Laba3.Task;
import com.example.cpma.ReadAndWrite;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
public class Laba3Controller {
    Task task = new Task();
    boolean enc = true;
    boolean loadCipher = false;
    String inputFile = "src/main/resources/files/laba3/input"; // Имя файла с вводной строкой
    String alphabetFile = "src/main/resources/files/laba3/originalAlphabet.txt"; //
    String cipherFile = "src/main/resources/files/laba3/cipherAlphabet.txt"; //

    @GetMapping("/laba3")
    public ModelAndView viewLaba3() {
        System.out.println("Запуск страницы с третьей лабой");

        ModelAndView view = new ModelAndView("laba3");

        view.addObject("textInput", task.getText());
        if(loadCipher)
            view.addObject("cipherAlphabet", task.getCipherAlphabet());

        if(enc)
            view.addObject("textOutput", task.getEncrypted());
        else
            view.addObject("textOutput", task.getDecrypted());

        return view;
    }
    @PostMapping("/loadData3")
    public ModelAndView loadData(@RequestParam("textInput") String textInput) throws IOException {
        if(textInput.isEmpty()){
            task.setText(ReadAndWrite.readFromFile(inputFile));
        }else{
            task.setText(textInput);
        }
        return new ModelAndView("redirect:/laba3");
    }
    @PostMapping("/loadCipher3")
    public ModelAndView loadCipher() throws IOException {
        task.setOriginalAlphabet(ReadAndWrite.readStringFromFileToList(alphabetFile));
        task.setCipherAlphabet(MonophonicSubstitutionEncoder.getRandomTableForMessage(task.getText(), task.getOriginalAlphabet()));
        loadCipher = true;
        ReadAndWrite.writeMapToFile(task.getCipherAlphabet(), cipherFile);
        return new ModelAndView("redirect:/laba3");
    }
    @PostMapping("/encrypt3")
    public ModelAndView encrypt() {

        task.setEncrypted(MonophonicSubstitutionEncoder.encrypt(task.getText(), task.getCipherAlphabet()));

        enc = true;
        return new ModelAndView("redirect:/laba3");
    }
    @PostMapping("/decrypt3")
    public ModelAndView decrypt() {
        task.setDecrypted(MonophonicSubstitutionEncoder.decrypt(task.getEncrypted(), task.getCipherAlphabet()));
        enc = false;
        return new ModelAndView("redirect:/laba3");
    }
}
