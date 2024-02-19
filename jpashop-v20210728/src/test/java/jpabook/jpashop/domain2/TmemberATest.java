package jpabook.jpashop.domain2;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.jpashop.fastcampus.domain.QBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import java.util.List;

import static jpabook.jpashop.domain2.QTmemberA.tmemberA;
import static jpabook.jpashop.domain2.QTmemberB.tmemberB;
import static jpabook.jpashop.domain2.QTmemberC.*;
import static jpabook.jpashop.fastcampus.domain.QBoard.*;

@SpringBootTest
class TmemberATest {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    TmemberATest(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }
//    @BeforeEach
//    @PostConstruct
    @Test
    void initData(){
        queryFactory
                .insert(tmemberA)
                .set(tmemberA.username, "username")
                .set(tmemberA.tmemberB, tmemberB)
                .execute();

    }

    @Test
    void testMembers() {
//        queryFactory.insert()

        QTmemberA qTmemberA = tmemberA;
        QTmemberB qTmemberB = tmemberB;
        QTmemberC qTmemberC = tmemberC;

        List<Tuple> tuples = queryFactory.select(qTmemberA.username, qTmemberB.username, qTmemberC.username)
                .from(qTmemberA)
                .leftJoin(qTmemberB).on(qTmemberA.id.eq(qTmemberB.id))
                .leftJoin(qTmemberC).on(qTmemberA.id.eq(qTmemberC.id))
                .fetch();
        for (Tuple tuple : tuples) {
            String username = tuple.get(tmemberA.username);
            System.out.println("username = " + username);
        }

    }

    @Test
    void fetchTest(){
//        queryFactory.select(tmemberA.username,tmemberB.username)
//                .from(tmemberA.tmemberB,tmemberB).fetchJoin()

    }

    /**
     * private String title;
     *     private String writer;
     *     private String content;
     *
     *     private Long viewCnt;
     *     @Temporal(value = TemporalType.TIMESTAMP)
     *     private Date inDate;
     *     @Temporal(value = TemporalType.TIMESTAMP)
     *     private Date upDate;
     */

    @Test
    void boardInsertTest(){

    }
}