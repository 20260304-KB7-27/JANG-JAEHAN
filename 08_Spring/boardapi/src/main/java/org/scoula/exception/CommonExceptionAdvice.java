package org.scoula.exception;


import lombok.extern.log4j.Log4j2;
import org.scoula.board.controller.BoardController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

// assignableTypes : 예외 처리 대상 컨트롤러를 클래스 단위로 지정
@RestControllerAdvice(assignableTypes = BoardController.class)
@Log4j2
public class CommonExceptionAdvice {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handle404(HttpServletRequest request, Model model, NoHandlerFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND) // 404
                .header("Content-Type", "text/plain;charset=UTF-8")
                .body("해당 ID의 요소가 없습니다.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> except(Exception ex, Model model) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR) // 500 에러 -> 개발자 잘못
                .header("Content-Type", "text/plain;charset=UTF-8")
                .body(ex.getMessage());
    }



}