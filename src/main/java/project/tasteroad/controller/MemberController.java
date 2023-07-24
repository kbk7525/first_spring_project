package project.tasteroad.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.tasteroad.dto.MemberDTO;
import project.tasteroad.service.MemberService;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    //회원가입으로 들어감
    @GetMapping("/member/save")
    public String saveMember() {
        return "save";
    }

    //회원가입 이후
    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        memberService.save(memberDTO);
        return "login";
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
            log.info("getId = {}", loginResult.getId());
            return "main";
        }
        //실패
        else {
            return "login";
        }
    }

    //로그아웃하면 index페이지로 이동
    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    //아이디 중복체크
    @PostMapping("/member/id-check")
    public @ResponseBody String idCheck(@RequestParam("memberId") String memberId) {
        System.out.println("memberId = " + memberId);
        String checkResult = memberService.idCheck(memberId);
        return checkResult;
    }
}
