package project.tasteroad.dto;

//BoardRepository에서 @Query를 사용하기 위해 만든 인터페이스
public interface BoardInterface {
    Long getNum(); //게시글 번호
    String getId(); //작성자
    String getTitle(); //게시글 제목
    String getContent(); //게시글 내용
    String getCategory(); //음식점 카테고리
    String getRate(); //별점
    String getRestaurant(); //식당 이름
    String getLatitude(); //위도
    String getLongitude(); //경도
}
