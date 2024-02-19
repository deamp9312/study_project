package jpabook.jpashop.domain3.domain2;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.stream.IntStream;

import static jpabook.jpashop.domain3.domain2.QMemberA.*;
import static jpabook.jpashop.domain3.domain2.QMemberB.*;
import static jpabook.jpashop.domain3.domain2.QMemberC.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberATest {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    MemberARepository memberARepository;
    @Autowired
    MemberBRepository memberBRepository;
    @Autowired
    MemberCRepository memberCRepository;

    @Autowired
    MemberATest(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

//    @Test
    void memberInitData(){
        IntStream.rangeClosed(1,100)
                .forEach(i->{
//                    MemberA memberA = new MemberA();
                    MemberB memberB = new MemberB();
                    MemberC memberC = new MemberC();
                    memberB.setUsername("user" + i);
                    memberC.setUsername("user" + i);
                    memberBRepository.save(memberB);
                    memberCRepository.save(memberC);
                });

    }

    @Test
    void JoinSelect(){
        List<Tuple> fetch = queryFactory
                .select(memberA.username, memberB.username, memberC.username)
//                .from(memberA, memberB, memberC)
                .from(memberA)
                .innerJoin(memberB).on(memberA.username.eq(memberB.username))
                .innerJoin(memberC).on(memberA.username.eq(memberC.username))
                .where(memberA.username.eq(memberB.username),
                        memberA.username.eq(memberC.username))
                .fetch();

        for (Tuple tuple : fetch) {
            System.out.println("tuple.get(memberA.username) = " + tuple.get(memberA.username));
            System.out.println("tuple.get(memberB.username) = " + tuple.get(memberB.username));
            System.out.println("tuple.get(memberC.username) = " + tuple.get(memberC.username));
        }



    }


}