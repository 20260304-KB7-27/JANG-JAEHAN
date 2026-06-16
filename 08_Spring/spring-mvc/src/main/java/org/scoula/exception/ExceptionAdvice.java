package org.scoula.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
전역 처리를 하기 위한 어노테이션
 */
@ControllerAdvice
@Log4j2
public class ExceptionAdvice {

    // 모든 종류를 처리하는 핸들러 메서드
    @ExceptionHandler(Exception.class)
    public String exceptionHandle(Exception e, Model model) {

        model.addAttribute("errorMessage",e.getMessage());

        return "error_page";
    }
}
