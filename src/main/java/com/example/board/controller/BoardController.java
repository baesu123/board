package com.example.board.controller;

import com.example.board.dto.BoardCreateRequest;
import com.example.board.dto.BoardResponse;
import com.example.board.dto.BoardUpdateRequest;
import com.example.board.dto.PageResponse;
import com.example.board.security.UserPrincipal;
import com.example.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시글 목록 조회
     * GET /api/boards?keyword=검색어&page=1&size=10
     */
    @GetMapping
    public ResponseEntity<PageResponse<BoardResponse>> getBoards(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(boardService.getBoards(keyword, page, size));
    }

    /**
     * 게시글 단건 조회
     * GET /api/boards/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponse> getBoard(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.getBoard(id));
    }

    /**
     * 게시글 등록 (로그인 필요)
     * POST /api/boards
     * Header: Authorization: Bearer {accessToken}
     *
     * @AuthenticationPrincipal 은 JwtAuthenticationFilter가 SecurityContext에 심어둔
     * 로그인 사용자 정보(UserPrincipal)를 자동으로 주입해준다.
     */
    @PostMapping
    public ResponseEntity<BoardResponse> createBoard(
            @Valid @RequestBody BoardCreateRequest request,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        BoardResponse response = boardService.createBoard(request, principal.getNickname());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * 게시글 수정
     * PUT /api/boards/{id}
     */
    @PutMapping("/{id}")
    /**
     * 게시글 수정 (로그인 필요, 본인 글만 수정 가능)
     * PUT /api/boards/{id}
     */
// 변경 후
    public ResponseEntity<BoardResponse> updateBoard(
            @PathVariable Long id,
            @Valid @RequestBody BoardUpdateRequest request,
            @AuthenticationPrincipal UserPrincipal principal
    ){
        return ResponseEntity.ok(boardService.updateBoard(id, request, principal.getNickname()));
    }

    /**
     * 게시글 삭제
     * DELETE /api/boards/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(
            @PathVariable Long id,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        boardService.deleteBoard(id, principal.getNickname());
        return ResponseEntity.noContent().build(); // 204
    }
}
