package com.netcracker.controller;

import com.netcracker.model.Greeting;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    @PostMapping("/greeting-get")
    public String greetingSubmit(@ModelAttribute Greeting greeting, HttpServletResponse response) {

         Pattern pattern;
         Matcher matcher;

         final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


         final String NAME_PATTER =
                 "[A-Za-z]+$";

         pattern = Pattern.compile(EMAIL_PATTERN);



         matcher = pattern.matcher(greeting.getEmail());
            if(!matcher.matches()){
                return "error";
            }

            pattern = Pattern.compile(NAME_PATTER);
            matcher = pattern.matcher(greeting.getFirstName());

            if(!matcher.matches()){
                return "error";
            }
        matcher = pattern.matcher(greeting.getSecondName());

        if(!matcher.matches()){
            return "error";
        }
        matcher = pattern.matcher(greeting.getThirdName());

        if(!matcher.matches()){
            return "error";
        }



        try(FileWriter fileWriter = new FileWriter(new File("users.txt"),true)){
                fileWriter.write(greeting.getFirstName() + "\n");
                fileWriter.write(greeting.getSecondName() + "\n");
                fileWriter.write(greeting.getThirdName() + "\n");
                fileWriter.write(greeting.getAge() + "\n");
                fileWriter.write(greeting.getSalary() + "\n");
                fileWriter.write(greeting.getEmail() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "result";
    }

}
