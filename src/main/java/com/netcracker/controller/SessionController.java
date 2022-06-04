package com.netcracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
public class SessionController {

    @GetMapping("/search-get")
    public String testSessionListener(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);

        Date creationTime = new Date(session.getCreationTime());
        Date lastAccessedTime = new Date(session.getLastAccessedTime());
        String id = session.getId();

        return "Creation Time = " + creationTime + " , "
                + " Last access time = " + lastAccessedTime + " , "
                + " Session id = " + id;

    }
}

