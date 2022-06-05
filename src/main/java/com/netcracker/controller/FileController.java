package com.netcracker.controller;

import com.netcracker.model.Greeting;
import com.netcracker.services.FileService;
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
    FileService fileService = new FileService();

    @GetMapping("/addfile")
    public String setNameFile() {

        return "addFile";
    }

    @PostMapping("/addfile-get")
    public String getUser(@RequestParam("name") String fileName, Model model, HttpServletRequest request) {

        return fileService.getUser(fileName, model, request);
    }
}





