package project.tasteroad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import project.tasteroad.dto.BoardDTO;
import project.tasteroad.dto.BoardInterface;
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

    public Page<BoardDTO> findByMember(String id, Pageable pageable) {
        int page = pageable.getPageNumber()-1;
        int pageLimit = 10; //한페이지에 보여줄 글 개수
        Page<BoardInterface> boardInterfaces = boardRepository.findByMember(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "num")), id);
        Page<BoardDTO> boardDTOS = boardInterfaces.map
                (board-> new BoardDTO(board.getNum(), board.getId(), board.getTitle(), board.getCategory(), board.getRate(), board.getRestaurant()));
        return boardDTOS;
    }

    public Page<BoardDTO> findByCategory(String category, Pageable pageable) {
        int page = pageable.getPageNumber()-1;
        int pageLimit = 10; //한페이지에 보여줄 글 개수
        Page<BoardInterface> boardInterfaces = boardRepository.findByCategory(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "num")), category);
        Page<BoardDTO> boardDTOS = boardInterfaces.map
                (board-> new BoardDTO(board.getNum(), board.getId(), board.getTitle(), board.getCategory(), board.getRate(), board.getRestaurant()));
        return boardDTOS;
    }

    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber()-1;
        int pageLimit = 10; //한페이지에 보여줄 글 개수
        Page<BoardEntity> boardEntities = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "num")));
        Page<BoardDTO> boardDTOS = boardEntities.map
        (board-> new BoardDTO(board.getNum(), board.getId(), board.getTitle(), board.getCategory(), board.getRate(), board.getRestaurant()));
        return boardDTOS;
    }
}
