package com.netcracker.controller;

import com.netcracker.model.Greeting;
import com.netcracker.services.SearchService;
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

    SearchService searchService = new SearchService();

    @GetMapping("/search")
    public String search(Model model) {

       return searchService.search(model);
    }

    @PostMapping("/search-get")
    public String searchGet(@ModelAttribute Greeting greeting, Model model, HttpServletRequest request,@RequestHeader(value="User-Agent", required=false) String userAgent) {

        return searchService.getSearch(greeting, model, request);
    }
}
