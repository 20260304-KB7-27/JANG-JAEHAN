package org.scoula.board.controller;

import lombok.RequiredArgsConstructor;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.scoula.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/board")
@RequiredArgsConstructor
public class APIResponseController {

    private final BoardService service;

    @GetMapping("/{no}")
    public ApiResponse<BoardDTO> getById(@PathVariable Long no) {
        BoardDTO board = service.get(no);
        return ApiResponse.ok(board);
    }
}
