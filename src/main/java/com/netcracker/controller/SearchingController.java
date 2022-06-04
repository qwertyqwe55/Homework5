package com.netcracker.controller;

import com.netcracker.model.Greeting;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

@Controller
public class SearchingController {

    ArrayList<Greeting> users = new ArrayList<>();

    @GetMapping("/search")
    public String search(Model model) {

        model.addAttribute("greeting", new Greeting());
        try(Scanner scanner = new Scanner(new File("users.txt"))){
            while(scanner.hasNext()){
                String name = scanner.nextLine();
                String surname = scanner.nextLine();
                String thirdName = scanner.nextLine();
                int age = Integer.parseInt(scanner.nextLine());
                int salary = Integer.parseInt(scanner.nextLine());
                String email = scanner.nextLine();
                users.add(new Greeting(name,surname,thirdName,age,salary,email));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return "searching";
    }

    @PostMapping("/search-get")
    public String searchGet(@ModelAttribute Greeting greeting, Model model, HttpServletRequest request,@RequestHeader(value="User-Agent", required=false) String userAgent) {

        HttpSession session = request.getSession(true);

        for(Greeting user : users){
            if(user.getFirstName().equals(greeting.getFirstName()) && user.getSecondName().equals(greeting.getSecondName())){
                Date creationTime = new Date(session.getCreationTime());
                model.addAttribute("browser",request.getHeader("User-Agent"));
                model.addAttribute("greeting", user);
                model.addAttribute("time",creationTime);
                return "user";
            }
        }
        return "none";
    }
}
