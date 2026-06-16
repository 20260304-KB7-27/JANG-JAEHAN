package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
@RequestMapping("/exception")
public class ExceptionTestController2 {

    @GetMapping("/test2")
    public String test(@RequestParam int age) {
        if ( age < 0 ) {
            throw new IllegalArgumentException("나이가 0보다 커야합니다.");
        }
        return "ok";
    }

}
