package jpabook.jpashop.domain2;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import java.util.List;

import static jpabook.jpashop.domain2.QTmemberA.tmemberA;
import static jpabook.jpashop.domain2.QTmemberB.tmemberB;
import static jpabook.jpashop.domain2.QTmemberC.*;

@SpringBootTest
class TmemberATest {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    TmemberATest(EntityManager em, JPAQueryFactory queryFactory) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
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

}