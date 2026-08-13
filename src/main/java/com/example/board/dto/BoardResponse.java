package com.example.board.dto;

import com.example.board.domain.Board;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BoardResponse {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private int viewCount;
//    private List<CommentResponse> comments; // 게시글 상세 조회 시 댓글 목록을 함께 담기 위한 필드
//    private List<AttachmentResponse> attachments; // 게시글 상세 조회 시 첨부파일 목록을 함께 담기 위한 필드
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static BoardResponse from(Board board) {
        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .viewCount(board.getViewCount())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }

//    public static BoardResponse from(Board board, List<CommentResponse> comments) {
//        return BoardResponse.builder()
//                .id(board.getId())
//                .title(board.getTitle())
//                .content(board.getContent())
//                .writer(board.getWriter())
//                .viewCount(board.getViewCount())
//                .comments(comments) // 댓글 목록 바인딩
//                .createdAt(board.getCreatedAt())
//                .updatedAt(board.getUpdatedAt())
//                .build();
//    }
//
//    public static BoardResponse from(Board board, List<CommentResponse> comments, List<AttachmentResponse> attachments) {
//        return BoardResponse.builder()
//                .id(board.getId())
//                .title(board.getTitle())
//                .content(board.getContent())
//                .writer(board.getWriter())
//                .viewCount(board.getViewCount())
//                .comments(comments) // 댓글 목록 바인딩
//                .attachments(attachments) // 첨부파일 목록 바인딩
//                .createdAt(board.getCreatedAt())
//                .updatedAt(board.getUpdatedAt())
//                .build();
//    }
}

