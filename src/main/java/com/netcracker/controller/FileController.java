package com.netcracker.controller;

import com.netcracker.model.Greeting;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

@Controller
public class FileController {


    @GetMapping("/addfile")
    public String setNameFile() {

        return "addFile";
    }

    @PostMapping("/addfile-get")
    public String getUser(@RequestParam("name") String fileName, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Greeting greeting = new Greeting();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            greeting.setFirstName(scanner.nextLine());
            greeting.setSecondName(scanner.nextLine());
            greeting.setThirdName(scanner.nextLine());
            greeting.setAge(Integer.parseInt(scanner.nextLine()));
            greeting.setSalary(Integer.parseInt(scanner.nextLine()));
            greeting.setEmail(scanner.nextLine());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
        Date creationTime = new Date(session.getCreationTime());
        model.addAttribute("browser",request.getHeader("User-Agent"));
        model.addAttribute("time",creationTime);
        model.addAttribute("greeting", greeting);
        return "user";
    }
}





