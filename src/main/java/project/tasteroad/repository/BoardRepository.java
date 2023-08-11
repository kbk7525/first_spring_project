package project.tasteroad.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.tasteroad.dto.BoardInterface;
import project.tasteroad.entity.BoardEntity;

import java.util.List;


public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    //id 값으로 자신이 쓴글을 찾아 출력하기 위한 메소드
    //@Query(value = "select * from board where id = :id", nativeQuery = true)
    //List<BoardInterface> findByMember(@Param("id") String id);

    //카테고리별로 출력하기 위한 메소드
    @Query(value = "select * from board where category = :category", nativeQuery = true)
    Page<BoardInterface> findByCategory(Pageable pageable, @Param("category") String category);

    @Query(value = "select * from board where id = :id", nativeQuery = true)
    Page<BoardInterface> findByMember(Pageable pageable, @Param("id") String id);
}
