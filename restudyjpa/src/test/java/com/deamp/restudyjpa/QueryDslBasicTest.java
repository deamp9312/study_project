package com.deamp.restudyjpa;

import com.deamp.restudyjpa.entity.Member;
import com.deamp.restudyjpa.entity.Team;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.deamp.restudyjpa.entity.QMember.member;
import static com.deamp.restudyjpa.entity.QTeam.team;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class QueryDslBasicTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory ;
    @BeforeEach
    public void before(){
        queryFactory = new JPAQueryFactory(em);
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);
        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @Test
    public void startJPQL(){
        //findMember1
        String query = "select m from Member m where m.username = :username";
        Member findMember = em.createQuery(query, Member.class)
                .setParameter("username", "member1").getSingleResult();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void startQuerydsl(){

        Member findMember = queryFactory
                .select(member)
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }
    @Test
    public void searchAndParam(){
        Member findMember = queryFactory
                .selectFrom(member)
                .where(
                        member.username.eq("member1"),
                        member.age.eq(10)//,를 이용한 방식은 중간에 null이 존재하면 무시함.
                )
                .fetchOne();
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void resultParam(){
//        QueryResults<Member> results = queryFactory
//                .selectFrom(member)
//                .fetchResults();
//        long total = results.getTotal();
//        System.out.println("total = " + total);
//        List<Member> content = results.getResults();
//        for (Member member1 : content) {
//            System.out.println("member1.getUsername() = " + member1.getUsername());
//
//        }

        /**
         * select 문에 count를 이용해서 조회하는 방법
         */
        long total = queryFactory
                .selectFrom(member).fetchCount();
        assertThat(total).isEqualTo(4);

    }

    @Test
    public void sort(){
        em.persist(new Member(null,100));
        em.persist(new Member("member5",100));
        em.persist(new Member("member6",100));
        List<Member> fetch = queryFactory
                .selectFrom(member)
                .where(member.age.eq(100))
                .orderBy(member.age.desc(),
                        member.username.asc().nullsLast()
                ).fetch();

        Member member1 = fetch.get(0);
        Member member2 = fetch.get(1);
        Member member3 = fetch.get(2);
        assertThat(member1.getUsername()).isEqualTo("member5");
        assertThat(member2.getUsername()).isEqualTo("member6");
        assertThat(member3.getUsername()).isNull();

    }

    @Test
    public void paging1(){
        List<Member> results = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1)  // 0 부터 시작하기 때문에 1이면 한개를 스킵하는것
                .limit(2)
                .fetch();
        assertThat(results.size()).isEqualTo(2);
    }
    @Test
    public void paging2(){
        QueryResults<Member> queryResults = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1)
                .limit(2)
                .fetchResults();
        assertThat(queryResults.getTotal()).isEqualTo(4);
        assertThat(queryResults.getLimit()).isEqualTo(2);
        assertThat(queryResults.getOffset()).isEqualTo(1);
        assertThat(queryResults.getResults().size()).isEqualTo(2);
    }

    @DisplayName("paging2에서 getResults를 사용할때 where조건이 실행되고 ")
    @Test
    public void pagingWithWhereCondition() {
        // Where 조건
        BooleanExpression condition = member.age.gt(20);

        // Count 쿼리
        long totalCount = queryFactory
                .selectFrom(member)
                .where(condition)
                .fetchCount();

        // 결과 가져오기
        QueryResults<Member> queryResults = queryFactory
                .selectFrom(member)
                .where(condition)
                .orderBy(member.username.desc())
                .offset(1)
                .limit(2)
                .fetchResults();

        // 검증
        assertThat(queryResults.getTotal()).isEqualTo(totalCount);
        assertThat(queryResults.getLimit()).isEqualTo(2);
        assertThat(queryResults.getOffset()).isEqualTo(1);
        assertThat(queryResults.getResults().size()).isEqualTo(2);
    }

    @Test
    public void aggregation(){//실무에는 tuple보다 dto로 뽑아오는 방식을 더 선호하여 사용함,
        Tuple tuple = queryFactory
                .select(member.count(),
                        member.age.sum(),
                        member.age.avg(),
                        member.age.max(),
                        member.age.min())
                .from(member)
                .fetchOne();
        assertThat(tuple.get(member.count())).isEqualTo(4);
    }

    @Test
    void group() {
        List<Tuple> fetch = queryFactory
                .select(team.name, member.age.avg())
                .from( member)
                .join(member.team,team)
//                .on(team.name.eq(member.team.name)) // jpa에서는 이러한 연관 관계 매핑을 통해 조인 조건이 자동으로 파악되므로 별도의 on절을 명시할 필요하 없은
                .groupBy(team.name)
                .fetch();
        Tuple tuple = fetch.get(0);
        Double v = tuple.get(member.age.avg());
        System.out.println("i = " + v);

    }

    @Test
    void join(){
        List<Member> fetch = queryFactory
                .selectFrom(member)
                .join(member.team, team)
                .where(team.name.eq("teamA"))
                .fetch();
        assertThat(fetch).extracting("username")
                .containsExactly("member1","member2");

    }

    @DisplayName("연관관계가 없는것중에 조인하는방법을 사용할때 사용. 그러나 카르테시안 조인이 됨 주의하셈")
    @Test
    void theta_join() {
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));

        List<Member> result = queryFactory
                .select(member)
                .from(member, team)
                .where(member.username.eq(team.name))
                .fetch();
        assertThat(result).extracting("username")
                .containsExactly("teamA", "teamB");
    }

    /**
     * 회원가 팀을 조인하면서 , 팀 이름이 teamA인 팀만 조인,회원은 모두 조회
     * JPQL : select m,t from Member m left join m.team t on t.name = 'teamA'
     */
    @Test
    void join_on_filtering(){
        List<Tuple> result = queryFactory
                .select(member, team)
                .from(member)
//                .leftJoin(member.team, team) // 외부조인시에는 on절을 사용해야 적용이되고 아니면 where 조건 사용해도됨
//                .on(team.name.eq("teamA"))
//                .where(member.team.eq(team) ,
//                        team.name.eq("teamA"))
                .leftJoin(member.team,team)
                .where(team.name.eq("teamA"))
                .fetch();

        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
        //select에서 종류가 두가지 이상 나와서 tuple로 가져오게 된다.

    }

    /**
     * 연관관계 없는 엔티티 외부 조인
     * ex) 회원의 이름과 팀의 이름이 같은 대상 '외부 조인'
     */
    @Test
    void join_on_no_relation() {
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));
        em.persist(new Member("teamC"));



        List<Tuple> result = queryFactory
                .select(member, team)
                .from(member)
                .leftJoin(team)
                .on(member.username.eq(team.name)) // 연관관계가 없는 상태에서 where를 사용하면 에러남
                .fetch();
        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
    }





}
