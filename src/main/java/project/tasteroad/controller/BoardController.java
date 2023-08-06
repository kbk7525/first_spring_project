package project.tasteroad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.tasteroad.dto.BoardDTO;
import project.tasteroad.service.BoardService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/main")
    public String MainForm() {
        return "main";
    }

    @GetMapping("/board/review")
    public String reviewForm() {
        return "review";
    }

    @PostMapping("/board/review")
    public String review(@ModelAttribute BoardDTO boardDTO) {
        boardService.save(boardDTO);
        return "redirect:/board/list";
    }

    @GetMapping("/board/list")
    public String findAll(Model model) {
        List<BoardDTO> boardList = boardService.findAll();
        model.addAttribute("boardList", boardList);
        return "list";
    }

    @GetMapping("board/{num}")
    public String findByNum(@PathVariable Long num, Model model) {
        BoardDTO boardDTO = boardService.findByNum(num);
        model.addAttribute("board", boardDTO);
        return "detail";
    }

    @GetMapping("/board/update/{num}")
    public String updateForm(@PathVariable Long num, Model model) {
        BoardDTO boardDTO = boardService.findByNum(num);
        model.addAttribute("update", boardDTO);
        return "updateReview";
    }

    @PostMapping("/board/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board", board);
        return "redirect:/board/"+boardDTO.getNum();
    }

    @GetMapping("/board/delete")
    public String delete(Long num) {
        boardService.delete(num);
        return "redirect:/board/list";
    }
}
