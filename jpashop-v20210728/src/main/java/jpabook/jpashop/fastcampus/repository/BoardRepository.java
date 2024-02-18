package jpabook.jpashop.fastcampus.repository;

import jpabook.jpashop.fastcampus.domain.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    int countAllByWriter(String writer);

    List<Board> findByWriter(String writer);

    List<Board> findByTitleAndWriter(String title, String writer);

    @Transactional
    int deleteByWriter(String writer);

    @Query("SELECT b FROM Board b")
    List<Board> findALlBoards(Pageable pageable);
}
