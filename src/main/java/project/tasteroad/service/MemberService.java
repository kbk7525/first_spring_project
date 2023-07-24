package project.tasteroad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.tasteroad.dto.MemberDTO;
import project.tasteroad.entity.MemberEntity;
import project.tasteroad.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /* save 메소드
        dto를 entity로 변환
        repository의 save메소드로 entity객체 저장
        db에 입력됨
    */
    public void save(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    /* login 메소드
        입력한 아이디로 db에서 조회
        db에서 조회한 비밀번호와 입력한 비밀번호가 같은지 판단
     */
    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> byId = memberRepository.findById(memberDTO.getId());
        //조회 결과가 있으면
        if(byId.isPresent()) {
            MemberEntity memberEntity = byId.get();

            //비밀번호가 일치하는 경우
            //entity를 dto로 변환
            if(memberEntity.getPw().equals(memberDTO.getPw())) {
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            }
            //비밀번호가 틀린 경우
            else {
                return null;
            }
        }
        //없으면
        else {
            return null;
        }
    }

    public String idCheck(String memberId) {
        Optional<MemberEntity> byId = memberRepository.findById(memberId);
        //조회결과 아이디가 있으면 사용X
        if(byId.isPresent()) {
            return null;
        }
        //사용 가능
        else {
            return "ok";
        }
    }
}
