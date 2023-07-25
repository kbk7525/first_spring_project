package project.tasteroad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.tasteroad.dto.BoardDTO;
import project.tasteroad.entity.BoardEntity;
import project.tasteroad.repository.BoardRepository;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }
}
