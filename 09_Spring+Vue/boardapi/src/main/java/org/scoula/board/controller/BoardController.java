package org.scoula.board.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.scoula.docs.BoardControllerDocs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/*
 * @Controller
 * @ResponseBody -> body영역에 값을 직접 작성하겠다.
 * */
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor // -> final / not null로 된 필드를 포함하는 생성자를 만들어줌
public class BoardController implements BoardControllerDocs {

    private final BoardService service;


    // 전체조회
    @ApiOperation(value = "게시글 목록", notes = "게시글 목록을 얻는 API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공적으로 요청이 처리되었습니다.", response = BoardDTO.class),
            @ApiResponse(code = 400, message = "잘못된 요청입니다."),
            @ApiResponse(code = 500, message = "서버에서 오류가 발생했습니다.")
    })
    @GetMapping("/list")
    public List<BoardDTO> getList() {

        return service.getList();
    }


    // ResponseEntity : Header, Body에 원하는 내용을 작성하고 싶을때
    @GetMapping("")
    public ResponseEntity<List<BoardDTO>> getList2() {

        return ResponseEntity.ok(service.getList());
    }

    // 단건조회 api/board/{no}
    @GetMapping("/{no}")
    public ResponseEntity<BoardDTO> getById(@PathVariable Long no) {

        return ResponseEntity.ok(service.get(no));
    }


    // [POST] /api/board  -> 생성
    @PostMapping("")
    public ResponseEntity<BoardDTO> create(
            @ApiParam(value = "🍣 생성할 게시글 정보", required = true)
            @RequestBody BoardDTO boardDTO
    ) {

        // 201 -> 새로운데이터가 입력되었다
//        return ResponseEntity.ok(service.create(boardDTO));
//        return ResponseEntity
//                .created(URI.create("/board/" + boardDTO.getNo()))
//                .body(service.create(boardDTO));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(boardDTO));
    }


    // 수정요청 [PUT] /api/board/{no}
    @PutMapping("/{no}")
    public ResponseEntity<BoardDTO> update(
            @PathVariable Long no, @RequestBody BoardDTO boardDTO) {

        boardDTO.setNo(no);

        return ResponseEntity.ok(service.update(boardDTO));
    }

    @DeleteMapping("/{no}")
    public ResponseEntity<BoardDTO> delete(@PathVariable Long no) {

        return ResponseEntity.ok(service.delete(no));
    }

}
