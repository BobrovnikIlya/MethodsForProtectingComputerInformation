package com.example.cpma.Laba1;

import com.example.cpma.Laba1.Task;
import com.example.cpma.Laba1.VerticalPermutationCipher;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import static com.example.cpma.Laba1.VerticalPermutationCipher.writeToFile;

@RestController
public class Laba1Controller {
    Task task = new Task();
    String inputFile = "src/main/resources/input.txt"; // Имя файла с вводной строкой
    String encryptedFile = "src/main/resources/encrypted.txt"; // Файл для зашифрованной строки
    String decryptedFile = "src/main/resources/decrypted.txt"; // Файл для расшифрованной строки
    String result = "";
    @GetMapping("/laba1")
    public ModelAndView viewLaba1() {
        System.out.println("Запуск страницы с первой лабой");
        System.out.println("Чтение предложения из файла");
        try {
            task.setText(VerticalPermutationCipher.readFromFile(inputFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ModelAndView view = new ModelAndView("laba1");
        view.addObject("textInput", task.getText());
        view.addObject("numbers", task.getPermutation());
        view.addObject("inputKey", task.getKey());
        view.addObject("textOutput", result);
        view.addObject("charArray", task.getTextSpell());

        System.out.println("Отображение страницы с первой лабой");
        return view;
    }
    @PostMapping("/inputKey")
    public ModelAndView inputKey(@RequestParam("inputKey") String textInput) {
        System.out.println("Полученные данные: " + textInput);
        int key = Integer.parseInt(textInput);

        task.setKey(key);
        task.setPermutation(VerticalPermutationCipher.Permutation(key));

        return new ModelAndView("redirect:/laba1");
    }
    @PostMapping("/inputTable")
    public ModelAndView inputTable() {
        System.out.println("Заполнение таблицы");

        task.setTextSpell(VerticalPermutationCipher.textSpell(task.getText(), task.getKey()));

        return new ModelAndView("redirect:/laba1");
    }
    @PostMapping("/encryptedText")
    public ModelAndView encryptedText() throws IOException {
        System.out.println("Шифрование");

        task.setEncrypted(VerticalPermutationCipher.encrypt(task.getText(), task.getPermutation()));
        writeToFile(encryptedFile, task.getEncrypted());
        System.out.println("Зашифрованная строка сохранена в файл " + encryptedFile);
        result = task.getEncrypted();
        return new ModelAndView("redirect:/laba1");
    }
    @PostMapping("/decryptedText")
    public ModelAndView decryptedText() throws IOException {
        System.out.println("Дешифрование");

        task.setDecrypted(VerticalPermutationCipher.decrypt(task.getEncrypted(), task.getPermutation()));
        writeToFile(decryptedFile, task.getDecrypted());
        System.out.println("Расшифрованная строка сохранена в файл " + decryptedFile);
        result = task.getDecrypted();
        return new ModelAndView("redirect:/laba1");
    }
}
