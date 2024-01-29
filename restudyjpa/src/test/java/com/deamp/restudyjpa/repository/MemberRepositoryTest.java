package com.deamp.restudyjpa.repository;

import com.deamp.restudyjpa.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    EntityManager em;
    @Autowired
    MemberRepository repository;
    JPAQueryFactory queryFactory ;
//    @BeforeEach
//    public void before(){
//        queryFactory = new JPAQueryFactory(em);
//        Team teamA = new Team("teamA");
//        Team teamB = new Team("teamB");
//        em.persist(teamA);
//        em.persist(teamB);
//        Member member1 = new Member("member1", 10, teamA);
//        Member member2 = new Member("member2", 20, teamA);
//        Member member3 = new Member("member3", 30, teamB);
//        Member member4 = new Member("member4", 40, teamB);
//        em.persist(member1);
//        em.persist(member2)0;
//        em.persist(member3);
//        em.persist(member4);
//    }


    @Test
    void basicTest() {
        Member member1 = new Member("member1", 10);
        repository.save(member1);

        Member findMember = repository.findById(member1.getId()).get();
        assertThat(findMember).isEqualTo(member1);

        List<Member> all = repository.findAll();
        assertThat(all).containsExactly(member1);

        List<Member> result2 = repository.findByUsername("member1");
        assertThat(result2).containsExactly(member1);
    }
}