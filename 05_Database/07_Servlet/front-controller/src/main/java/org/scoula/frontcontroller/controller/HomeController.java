package org.scoula.frontcontroller.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//
public class HomeController {
    // Service
    public String getIndex(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // service.###()
        // 비즈니스 로직 작성되는 곳
        return "index";
    }
}
