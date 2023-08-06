package project.tasteroad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.tasteroad.dto.BoardDTO;
import project.tasteroad.entity.BoardEntity;
import project.tasteroad.repository.BoardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }

    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList= boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }

    public BoardDTO findByNum(Long num) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(num);
        if(optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        }
        else {
            return null;
        }
    }
    public void delete(Long num) {
        boardRepository.deleteById(num);
    }

    public BoardDTO update(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);
        return findByNum(boardDTO.getNum());
    }
}
