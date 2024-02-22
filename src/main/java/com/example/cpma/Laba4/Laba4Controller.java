package com.example.cpma.Laba4;

import com.example.cpma.ReadAndWrite;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
public class Laba4Controller {
    boolean loadCipher = false;
    Task task = new Task();
    @GetMapping("/laba4")
    public ModelAndView viewLaba4() throws IOException {
        System.out.println("Запуск страницы с четвертой лабой");
        ModelAndView view = new ModelAndView("laba4");

        view.addObject("task", task);

        return getPage(task);
    }
    @PostMapping("/handleForm")
    public ModelAndView handleFormSubmit(@RequestParam("textInput") String textInput, @RequestParam String btn) throws IOException {
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
                task.setEncrypted(AnalyticalTransformations.encryptString(task.getKeyMatrix(), task.getText(), task.getAlphabet(), n));
                task.setResult(task.getEncrypted());
                break;
            case "decryptMessage":
                task.setDecrypted(AnalyticalTransformations.encryptString(task.getKeyMatrix(), task.getEncrypted(), task.getAlphabet(), n));
                task.setResult(task.getDecrypted());
                break;
            case "loadMatrix":
                task.setKeyMatrix(AnalyticalTransformations.generateKeyMatrix(n));
                break;
            default:
                return getPage(task);
        }
        return getPage(task);
    }
    public ModelAndView getPage(Task task){
        ModelAndView modelAndView = new ModelAndView("laba4");
        modelAndView.addObject("task", task);
        return modelAndView;
    }
}
