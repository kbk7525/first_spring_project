package project.tasteroad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.tasteroad.entity.MemberEntity;

import java.lang.reflect.Member;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //id로 회원조회 (select * from member where id=?)
    Optional<MemberEntity> findById(String id);
}
