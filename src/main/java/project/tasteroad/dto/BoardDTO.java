package project.tasteroad.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class BoardDTO {
    private Long num; //게시글 번호
    private String id; //작성자
    private String title; //게시글 제목
    private String content; //게시글 내용
    private String category; //음식점 카테고리
    private String rate; //별점
    private String restaurant; //식당 이름
}
