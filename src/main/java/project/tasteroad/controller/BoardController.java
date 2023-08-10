package project.tasteroad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.tasteroad.dto.BoardDTO;
import project.tasteroad.dto.BoardInterface;
import project.tasteroad.service.BoardService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    //메인으로 이동
    @GetMapping("/main/paging")
    public String MainForm(Model model, @PageableDefault(page = 1) Pageable pageable) {
        pageable.getPageNumber();
        Page<BoardDTO> pageList = boardService.paging(pageable);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber()/blockLimit)))-1) * blockLimit+1;
        int endPage = ((startPage + blockLimit -1) < pageList.getTotalPages()) ? startPage + blockLimit -1 : pageList.getTotalPages();
        model.addAttribute("pageList", pageList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "main";
    }

    //리뷰작성 폼으로 이동
    @GetMapping("/board/review")
    public String reviewForm() {
        return "review";
    }
    
    //리뷰 작성되면 리뷰 글 목록으로 이동
    @PostMapping("/board/review")
    public String review(@ModelAttribute BoardDTO boardDTO) {
        boardService.save(boardDTO);
        return "redirect:/main/paging";
    }
    
    //리뷰 상세 페이지로 이동
    @GetMapping("board/{num}")
    public String findByNum(@PathVariable Long num, Model model, @PageableDefault(page=1) Pageable pageable) {
        BoardDTO boardDTO = boardService.findByNum(num);
        model.addAttribute("board", boardDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return "detail";
    }
    
    //리뷰 수정 폼으로 이동
    @GetMapping("/board/update/{num}")
    public String updateForm(@PathVariable Long num, Model model) {
        BoardDTO boardDTO = boardService.findByNum(num);
        model.addAttribute("update", boardDTO);
        return "updateReview";
    }
    
    //리뷰 수정이 끝나면 상세페이지로 이동
    @PostMapping("/board/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board", board);
        return "redirect:/board/"+boardDTO.getNum();
    }
    
    //리뷰 삭제 폼
    @GetMapping("/board/delete")
    public String delete(Long num) {
        boardService.delete(num);
        return "redirect:/main/paging";
    }

    //한식 카테고리
    @GetMapping("/board/korean-food")
    public String categoryKorea(Model model) {
        List<BoardInterface> kFood = boardService.findByCategory("한식");
        model.addAttribute("list", kFood);
        return "categoryKorea";
    }

    //일식 카테고리
    @GetMapping("/board/japanese-food")
    public String categoryJapan(Model model) {
        List<BoardInterface> jFood = boardService.findByCategory("일식");
        model.addAttribute("list", jFood);
        return "categoryJapan";
    }

    //양식 카테고리
    @GetMapping("/board/western-food")
    public String categoryWestern(Model model) {
        List<BoardInterface> wFood = boardService.findByCategory("양식");
        model.addAttribute("list", wFood);
        return "categoryWestern";
    }

    //중식 카테고리
    @GetMapping("/board/chinese-food")
    public String categoryChina(Model model) {
        List<BoardInterface> cFood = boardService.findByCategory("중식");
        model.addAttribute("list", cFood);
        return "categoryChina";
    }
}
