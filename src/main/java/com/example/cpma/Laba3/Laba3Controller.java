package com.example.cpma.Laba3;

import com.example.cpma.Laba2.PoliCipher;
import com.example.cpma.Laba2.SingleСipher;
import com.example.cpma.Laba2.Task;
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
    String inputFile = "src/main/resources/files/laba3/input"; // Имя файла с вводной строкой
    String alphabetFile = "src/main/resources/files/laba3/originalAlphabet.txt"; //
    String monoAlphabetFile = "src/main/resources/files/laba3/cipherAlphabet.txt"; //

    @GetMapping("/laba3")
    public ModelAndView viewLaba3() {
        System.out.println("Запуск страницы с третьей лабой");

        ModelAndView view = new ModelAndView("laba3");

        //view.addObject("textInput", task.getText());

        return view;
    }
    /*@PostMapping("/loadData")
    public ModelAndView loadData(@RequestParam("textInput") String textInput) throws IOException {
        if(textInput.isEmpty()){
            task.setText(ReadAndWrite.readFromFile(inputFile));
        }else{
            task.setText(textInput);
        }
        return new ModelAndView("redirect:/laba3");
    }
    @PostMapping("/loadCipher")
    public ModelAndView loadCipher() throws IOException {
        return new ModelAndView("redirect:/laba3");
    }
    @PostMapping("/encrypt")
    public ModelAndView encrypt() {
        return new ModelAndView("redirect:/laba3");
    }
    @PostMapping("/decrypt")
    public ModelAndView decrypt() {

        return new ModelAndView("redirect:/laba3");
    }*/
}
