package project.tasteroad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.tasteroad.dto.BoardDTO;
import project.tasteroad.service.BoardService;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/review")
    public String reviewForm() {
        return "review";
    }

    @PostMapping("/board/review")
    public String review(@ModelAttribute BoardDTO boardDTO) {
        boardService.save(boardDTO);
        return "main";
    }
}
