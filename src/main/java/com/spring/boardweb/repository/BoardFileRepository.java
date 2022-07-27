package com.spring.boardweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.boardweb.entity.Board;
import com.spring.boardweb.entity.BoardFile;
import com.spring.boardweb.entity.BoardFileId;

public interface BoardFileRepository extends JpaRepository<BoardFile, BoardFileId>{
	// 구현 가능한 메소드는 find, read, query, count, get 
	List<BoardFile> findByBoard(Board board);
	
	//@Query : 원하는 쿼리를 작성하여 사용할 수 있다. nativeQuery옵션을 true로 설정하면
	//Repository의 메소드 명도 원하는대로 지정가능
	@Query(value="select ifnull (max(f.file_seq),0 ) + 1 from t_board_file f where f.board_seq = :boardSeq", nativeQuery = true)
	// value옵션이 사용할 쿼리 작성
	int selectNextFileSeqByBoardSeq(@Param("boardSeq") int boardSeq);
}

