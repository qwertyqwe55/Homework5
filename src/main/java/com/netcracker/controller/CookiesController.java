package com.netcracker.controller;


import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
public class CookiesController {

    @GetMapping("/read-cookie")
    public String readCookie(@CookieValue(value = "username", defaultValue = "Gleb") String username) {
        return "Hey! My username is " + username;
    }

    @GetMapping("/all-cookies")
    public String readAllCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {


                try(FileWriter fileWriter = new FileWriter(new File("users.txt"),true)){
                    for (Cookie cookie:cookies){
                    fileWriter.write(cookie.getValue() + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


            return Arrays.stream(cookies)
                    .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
        }
        return "No cookies";
    }

}

