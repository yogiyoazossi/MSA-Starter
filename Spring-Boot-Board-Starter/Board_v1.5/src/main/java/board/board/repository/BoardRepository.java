package board.board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import board.board.entity.BoardEntity;
import board.board.entity.BoardFileEntity;

public interface BoardRepository extends CrudRepository<BoardEntity, Integer> {
	List<BoardEntity> findAllByOrderByBoardIdxDesc();
	
	@Query("SELECT file FROM BoardFileEntity file WHERE board_idx = :boardIdx AND idx = :idx")
	BoardFileEntity findBoardFile(@Param("boardIdx") int boardIdx, @Param("idx") int idx);
	
	//Page<BoardEntity> findAll(Pageable pageable);
	//Page<BoardEntity> findByTitleLike(String title, Pageable pageable);
	Page<BoardEntity> findByTitleLikeAndContentsLike(String title, String contents, Pageable pageable);
}
