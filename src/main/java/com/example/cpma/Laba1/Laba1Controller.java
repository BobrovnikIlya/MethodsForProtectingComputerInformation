package com.example.cpma.Laba1;

import com.example.cpma.Laba1.Task;
import com.example.cpma.Laba1.VerticalPermutationCipher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
public class Laba1Controller {
    Task task;
    @GetMapping("/laba1")
    public ModelAndView viewLaba1() {
        System.out.println("Запуск страницы с первой лабой");
        System.out.println("Чтение предложения из файла");
        task = new Task();
        try {
            task.setText(VerticalPermutationCipher.readFromFile("src/main/resources/input.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ModelAndView view = new ModelAndView("laba1");
        view.addObject("textInput", task.getText());
        System.out.println("Отображение страницы с первой лабой");
        return view;
    }

    @PostMapping("/inputKey")
    public ModelAndView inputKey(@RequestParam("inputKey") String textInput) {
        System.out.println("Полученные данные: " + textInput);
        int key = Integer.parseInt(textInput);
        task.setKey(key);
        task.setPermutation(VerticalPermutationCipher.Permutation(key));

        ModelAndView laba = new ModelAndView("/laba1");
        laba.addObject("numbers", task.getPermutation());
        laba.addObject("inputKey", task.getKey());
        laba.addObject("textInput", task.getText());
        return laba;
    }

/*    @PostMapping("/redirect")
    public ModelAndView redirect(@RequestParam String option, @ModelAttribute Task task, HttpSession session) {
        System.out.println("Произведение расчетов...");
        task.setListWrapper();
        System.out.println("Передача данных с клиента на сервер");
        if (option.equals("Simplex")) {
            System.out.println("Запуск решения симплекс-методом");
            CompleteSimplex completeSimplex = SimplexMethod.getCompleteTask(task);
            System.out.println("Сохранение результатов...");
            session.setAttribute("task",task);
            session.setAttribute("simplex",completeSimplex);
            System.out.println("Результаты сохранены. Переход к отображению результатов");
            return new ModelAndView("redirect:/Simplex");
        } else if (option.equals("Hook")) {
            System.out.println("Запуск решения методом Хука-Дживса");
            CompleteHook completeHook =  HookMethods.solveResult(task);
            System.out.println("Сохранение результатов...");
            session.setAttribute("task",task);
            session.setAttribute("hook",completeHook);
            System.out.println("Результаты сохранены. Переход к отображению результатов");
            return new ModelAndView("redirect:/Hook");
        }
        System.out.println("Не выбран метод...");
        return new ModelAndView("Home");
    }*/
    @GetMapping("/ForUser")
    public ModelAndView userPage() {
        System.out.println("Отображение руководства пользователю");
        ModelAndView user = new ModelAndView("ForUser");
        return user;
    }
    @GetMapping("/About")
    public ModelAndView aboutPage() {
        System.out.println("Отображение информации об авторе");
        ModelAndView about = new ModelAndView("About");
        return about;
    }
}
