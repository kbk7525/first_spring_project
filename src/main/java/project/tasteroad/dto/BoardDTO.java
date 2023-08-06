package project.tasteroad.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.tasteroad.entity.BoardEntity;

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
    private String latitude; //위도
    private String longitude; //경도

    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setNum(boardEntity.getNum());
        boardDTO.setId(boardEntity.getId());
        boardDTO.setTitle(boardEntity.getTitle());
        boardDTO.setContent(boardEntity.getContent());
        boardDTO.setCategory(boardEntity.getCategory());
        boardDTO.setRate(boardEntity.getRate());
        boardDTO.setRestaurant(boardEntity.getRestaurant());
        boardDTO.setLatitude(boardEntity.getLatitude());
        boardDTO.setLongitude(boardEntity.getLongitude());
        return boardDTO;
    }
}
