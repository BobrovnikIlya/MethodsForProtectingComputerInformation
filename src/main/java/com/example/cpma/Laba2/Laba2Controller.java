package com.example.cpma.Laba2;

import com.example.cpma.Laba1.VerticalPermutationCipher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
@RestController
public class Laba2Controller {
    @GetMapping("/laba1")
    public ModelAndView viewLaba1() {
        System.out.println("Запуск страницы с первой лабой");
        System.out.println("Чтение предложения из файла");
        ModelAndView view = new ModelAndView("laba2");

        System.out.println("Отображение страницы с первой лабой");
        return view;
    }
}
