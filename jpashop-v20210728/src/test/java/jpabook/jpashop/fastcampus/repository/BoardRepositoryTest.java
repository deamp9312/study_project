package jpabook.jpashop.fastcampus.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.jpashop.fastcampus.MyRuleException;
import jpabook.jpashop.fastcampus.connumber.ExConst;
import jpabook.jpashop.fastcampus.domain.Board;
import jpabook.jpashop.fastcampus.domain.QBoard;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static jpabook.jpashop.fastcampus.domain.QBoard.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardRepositoryTest {
    @Autowired
    BoardRepository repository;

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    BoardRepositoryTest(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }
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

//    @BeforeEach
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
    @Test
    void testBooleanBuilder(){

        String searchBy = null;
        String keyword = "T";
        keyword = "%" + keyword + "%";

        BooleanBuilder builder = new BooleanBuilder();
        if ("T".equalsIgnoreCase(searchBy)) {
            builder.and(board.title.like(keyword));
        } else if ("C".equalsIgnoreCase(searchBy)) {
            builder.and(board.content.like(keyword));
        } else if ("TC".equalsIgnoreCase(searchBy)) {
            builder.and(board.title.like(keyword).or(board.content.like(keyword)));
        }

        List<Board> fetch = queryFactory.selectFrom(board)
                .where( board.bno.goe(190L),
                        builder)
                .fetch();
        fetch.forEach(System.out::println);

    }

}