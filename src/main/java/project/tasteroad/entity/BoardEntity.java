package project.tasteroad.entity;

import lombok.Getter;
import lombok.Setter;
import project.tasteroad.dto.BoardDTO;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "board")
public class BoardEntity {
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    private Long num; //게시글 번호

    @Column
    private String id; //작성자

    @Column(nullable = false)
    private String title; //게시글 제목

    @Column(nullable = false)
    private String content; //게시글 내용

    @Column
    private String category; //음식점 카테고리

    @Column
    private String rate; //별점

    @Column
    private String restaurant; //식당 이름

    @Column
    private String latitude; //위도

    @Column
    private String longitude; //경도

    public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setNum(boardDTO.getNum());
        boardEntity.setId(boardDTO.getId());
        boardEntity.setTitle(boardDTO.getTitle());
        boardEntity.setContent(boardDTO.getContent());
        boardEntity.setCategory(boardDTO.getCategory());
        boardEntity.setRate(boardDTO.getRate());
        boardEntity.setRestaurant(boardDTO.getRestaurant());
        boardEntity.setLatitude(boardDTO.getLatitude());
        boardEntity.setLongitude(boardDTO.getLongitude());
        return boardEntity;
    }

    public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setNum(boardDTO.getNum());
        boardEntity.setId(boardDTO.getId());
        boardEntity.setTitle(boardDTO.getTitle());
        boardEntity.setContent(boardDTO.getContent());
        boardEntity.setCategory(boardDTO.getCategory());
        boardEntity.setRate(boardDTO.getRate());
        boardEntity.setRestaurant(boardDTO.getRestaurant());
        boardEntity.setLatitude(boardDTO.getLatitude());
        boardEntity.setLongitude(boardDTO.getLongitude());
        return boardEntity;
    }
}
