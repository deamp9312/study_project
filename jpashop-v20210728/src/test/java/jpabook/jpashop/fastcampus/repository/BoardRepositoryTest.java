package jpabook.jpashop.fastcampus.repository;

import jpabook.jpashop.fastcampus.MyRuleException;
import jpabook.jpashop.fastcampus.connumber.ExConst;
import jpabook.jpashop.fastcampus.domain.Board;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        try {
            repository.findById(3L)
                    .orElseThrow(()->
                            new MyRuleException(ExConst.USER_ERROR.name()));


        }catch (MyRuleException e){
            System.out.println("예외발생");

            System.out.println(ExConst.USER_ERROR.name());
            System.out.println(ExConst.USER_ERROR.getErrorNumber());
            e.logDetails();
        }
    }

    @BeforeEach
    public void initData(){
        for(int i=1;i<=100;i++){
            Board board = new Board();
//            board.setBno((long)i);
            board.setTitle("title"+i);
            board.setContent("content"+i);
            board.setWriter("writer" + (i % 5));
            board.setViewCnt((long)(Math.random()*100));
            board.setInDate(new Date());
            board.setUpDate(new Date());
            repository.save(board);
        }
    }

    @Test
    void BoardSizeCheck(){
        List<Board> all = repository.findAll();
        System.out.println("size : "+ all.size());
    }

    @Test
    void testPaging(){
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "viewCnt");

        List<Board> sorts = repository.findALlBoards(pageable);
        sorts.forEach(System.out::println);

//        List<Sort.Order> listSort = new ArrayList<>();
//        listSort.add(new Sort.Order(Sort.Direction.DESC, "view_cnt"));
//        listSort.add(new Sort.Order(Sort.Direction.ASC, "up_date"));
//
//        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(listSort));

    }

}