package com.example.cpma.Laba5;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
public class Laba5Controller {
    boolean loadCipher = false;
    Task task = new Task();
    @GetMapping("/laba5")
    public ModelAndView viewLaba4() throws IOException {
        System.out.println("Запуск страницы с пятой лабой");
        ModelAndView view = new ModelAndView("laba5");
        view.addObject("task", task);
        return getPage(task);
    }
    @PostMapping("/buttonForm")
    public ModelAndView handleFormSubmit(@RequestParam("textInput") String textInput, @RequestParam("inputKey") String inputKey, @RequestParam String btn) throws IOException {
        int n = (3 % 4) + 2;
        switch (btn) {
            case "loadMessage":
                if(textInput.isEmpty()){
                    task.setInfo();
                }else{
                    task.setText(textInput);
                }
                task.setAlphabet();
                break;
            case "encryptMessage":
                task.setEncrypted(Feistel.encrypt( task.getText(), task.getKeyMatrix(), task.getRound()));
                task.setResult(task.getEncrypted());
                break;
            case "decryptMessage":
                task.setDecrypted(Feistel.decrypt(task.getEncrypted(), task.getKeyMatrix(), task.getRound()));
                task.setResult(task.getDecrypted());
                break;
            case "loadMatrix":
                task.setRound(Integer.parseInt(inputKey));
                task.setKeyMatrix(Feistel.generateKeys(task.getRound(), task.getAlphabet()));
                break;
            default:
                return getPage(task);
        }
        return getPage(task);
    }
    public ModelAndView getPage(Task task){
        ModelAndView modelAndView = new ModelAndView("laba5");
        modelAndView.addObject("task", task);
        return modelAndView;
    }
}
