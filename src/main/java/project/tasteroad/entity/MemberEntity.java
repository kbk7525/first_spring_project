package project.tasteroad.entity;

import lombok.Getter;
import lombok.Setter;
import project.tasteroad.dto.MemberDTO;

import javax.persistence.*;

//테이블 생성
@Entity
@Getter @Setter
@Table(name="member")
public class MemberEntity {

    @Id //primary key 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment 지정
    private Long num;

    @Column(unique = true) //unique 제약조건
    private String id;

    @Column
    private String pw;

    @Column
    private String name;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setNum(memberDTO.getNum());
        memberEntity.setId(memberDTO.getId());
        memberEntity.setPw(memberDTO.getPw());
        memberEntity.setName(memberDTO.getName());
        return memberEntity;
    }

    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setNum(memberDTO.getNum());
        memberEntity.setId(memberDTO.getId());
        memberEntity.setPw(memberDTO.getPw());
        memberEntity.setName(memberDTO.getName());
        return memberEntity;
    }
}
