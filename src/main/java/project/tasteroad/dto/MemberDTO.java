package project.tasteroad.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import project.tasteroad.entity.MemberEntity;

@Getter @Setter
@NoArgsConstructor
@ToString
public class MemberDTO {
    private Long num;
    private String id;
    private String pw;
    private String name;

    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setNum(memberEntity.getNum());
        memberDTO.setId(memberEntity.getId());
        memberDTO.setPw(memberEntity.getPw());
        memberDTO.setName(memberEntity.getName());
        return memberDTO;
    }
}
