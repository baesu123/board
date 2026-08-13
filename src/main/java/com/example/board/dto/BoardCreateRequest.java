package com.example.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardCreateRequest {

    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 200, message = "제목은 200자를 넘을 수 없습니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    // writer는 클라이언트가 보내지 않는다. 로그인한 사용자 정보(JWT)에서 서버가 직접 채운다.
    // 그렇지 않으면 남의 이름으로 글을 작성하는 것처럼 위장할 수 있기 때문이다.
}

