package project.tasteroad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.tasteroad.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
