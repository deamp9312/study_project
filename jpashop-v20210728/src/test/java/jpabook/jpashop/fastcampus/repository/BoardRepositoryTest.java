package jpabook.jpashop.fastcampus.repository;

import jpabook.jpashop.fastcampus.domain.Board;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardRepositoryTest {
    @Autowired
    BoardRepository repository;

    @Test
    @Order(1)
    void BoardRepositoryTest(){
        Board board = new Board();
        board.setBno(1L);
        board.setTitle("title");
        board.setContent("content");
        board.setWriter("aaa");
        board.setInDate(new Date());
        board.setUpDate(new Date());

        repository.save(board);
    }

    @Test
    @Order(2)
    void findBoard(){
        Board board = repository.findById(2L).orElse(new Board());
        if(board.getBno() ==null){
            System.out.println("board = " + board);
        }

    }

}