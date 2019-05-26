package board.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.board.entity.BoardEntity;
import board.board.repository.BoardRepository;
import board.board.repository.PageRepository;
import board.common.FileUtils;
import board.board.entity.BoardFileEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardRepository BoardRepository;
	
	@Autowired
	PageRepository PageRepository;
	
	@Autowired
	FileUtils fileUtils;
	
	// ���� ������ ����Ʈ ���, List / Paging / Searching ��� ����
	// Searching�� ������ �������� �˻� ����
    @Override
	public Page<BoardEntity> findByTitle(String title, Pageable pageable) throws Exception{
    	//int paging = pageable.getPageNumber();
        return BoardRepository.findByTitleLike("%"+title+"%", pageable);
    }

	
	
    // ���� ������ ����Ʈ ���, Page �� Search ����� �߰��ϸ鼭 ������ ���� ���
	@Override
	public List<BoardEntity> selectBoardList() throws Exception {
		return BoardRepository.findAllByOrderByBoardIdxDesc();
	}	
	
	@Override
	public void saveBoard(BoardEntity board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		board.setCreatorId("admin");
		List<BoardFileEntity> list = fileUtils.parseFileInfo(multipartHttpServletRequest);
		if(CollectionUtils.isEmpty(list) == false){
			board.setFileList(list);
		}
		BoardRepository.save(board);
	}
	
	@Override
	public BoardEntity selectBoardDetail(int boardIdx) throws Exception{
		Optional<BoardEntity> optional = BoardRepository.findById(boardIdx);
		if(optional.isPresent()){
			BoardEntity board = optional.get();
			board.setHitCnt(board.getHitCnt() + 1);
			BoardRepository.save(board);
			
			return board;
		}
		else {
			throw new NullPointerException();
		}
	}
	
	@Override
	public void deleteBoard(int boardIdx) {
		BoardRepository.deleteById(boardIdx);
	}

	@Override
	public BoardFileEntity selectBoardFileInformation(int boardIdx, int idx) throws Exception {
		BoardFileEntity boardFile = BoardRepository.findBoardFile(boardIdx, idx);
		return boardFile;
	}
}
