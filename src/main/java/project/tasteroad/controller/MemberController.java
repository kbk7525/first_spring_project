package project.tasteroad.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.tasteroad.dto.BoardDTO;
import project.tasteroad.dto.BoardInterface;
import project.tasteroad.dto.MemberDTO;
import project.tasteroad.service.BoardService;
import project.tasteroad.service.MemberService;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {


    private final MemberService memberService;
    private final BoardService boardService;

    //회원가입으로 들어감
    @GetMapping("/member/save")
    public String saveMember() {
        return "save";
    }

    //회원가입 이후
    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        memberService.save(memberDTO);
        return "redirect:/member/login";
    }

    //로그인 버튼 누르면
    @GetMapping("/member/login")
    public String loginForm() {
        return "login";
    }

    //로그인 창에서 로그인데이터 전송
    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        //로그인 성공
        if(loginResult != null) {
            session.setAttribute("loginId", loginResult.getId());
            return "redirect:/main/paging";
        }
        //실패
        else {
            return "redirect:/member/login";
        }
    }

    //로그아웃하면 index페이지로 이동
    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    //아이디 중복체크
    @PostMapping("/member/id-check")
    public @ResponseBody String idCheck(@RequestParam("memberId") String memberId) {
        String checkResult = memberService.idCheck(memberId);
        return checkResult;
    }

    //마이페이지 이동
    @GetMapping("/member/mypage/paging")
    public String infoView(HttpSession session, Model model, @PageableDefault(page = 1) Pageable pageable) {
        String id = (String) session.getAttribute("loginId");
        pageable.getPageNumber();
        Page<BoardDTO> myList = boardService.findByMember(id, pageable);
        model.addAttribute("myList", myList);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber()/blockLimit)))-1) * blockLimit+1;
        int endPage = ((startPage + blockLimit -1) < myList.getTotalPages()) ? startPage + blockLimit -1 : myList.getTotalPages();
        model.addAttribute("pageList", myList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "mypage";
    }

    //비밀번호 변경 폼
    @GetMapping("/member/update")
    public String memberUpdate(HttpSession session, Model model) {
        String id = (String) session.getAttribute("loginId");
        MemberDTO memberDTO = memberService.updateMember(id);
        model.addAttribute("updateMember", memberDTO);
        return "updateMember";
    }

    //비밀번호 변경 후
    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
        memberService.update(memberDTO);
        return "redirect:/member/mypage/paging";
    }

    //회원 탈퇴
    @GetMapping("/member/delete")
    public String deleteMember(HttpSession session, Long num) {
        memberService.deleteById(num);
        session.invalidate();
        return "redirect:/";
    }
}
