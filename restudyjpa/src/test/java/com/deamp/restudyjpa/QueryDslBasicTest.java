package com.deamp.restudyjpa;

import com.deamp.restudyjpa.dto.MemberDto;
import com.deamp.restudyjpa.dto.QMemberDto;
import com.deamp.restudyjpa.dto.UserDto;
import com.deamp.restudyjpa.entity.Member;
import com.deamp.restudyjpa.entity.QMember;
import com.deamp.restudyjpa.entity.Team;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.NonUniqueResultException;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.deamp.restudyjpa.entity.QMember.member;
import static com.deamp.restudyjpa.entity.QTeam.team;
import static com.querydsl.jpa.JPAExpressions.select;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
//@Commit
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

        try {
            //findMember1
            String query = "select m from Member m where m.username = :username";
            Member findMember = em.createQuery(query, Member.class)
                    .setParameter("username", "member1").getSingleResult();

            assertThat(findMember.getUsername()).isEqualTo("member1");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void startQuerydsl(){

        try {
            Member findMember = queryFactory
                    .select(member)
                    .from(member)
                    .where(member.username.eq("member1"))
                    .fetchOne();
            assertThat(findMember.getUsername()).isEqualTo("member1");
        } catch (NonUniqueResultException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void searchAndParam(){
        try {
            Member findMember = queryFactory
                    .selectFrom(member)
                    .where(
                            member.username.eq("member1"),
                            member.age.eq(10)//,를 이용한 방식은 중간에 null이 존재하면 무시함.
                    )
                    .fetchOne();
            assertThat(findMember.getUsername()).isEqualTo("member1");
        } catch (NonUniqueResultException e) {
            throw new RuntimeException(e);
        }
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

    @PersistenceUnit
    EntityManagerFactory emf;

    @Test
    void fetchJoinNo() {
        try {
            em.flush();
            em.clear();

            Member findMember = queryFactory
                    .selectFrom(member)
                    .where(member.username.eq("member1"))
                    .fetchOne();

            boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
            assertThat(loaded).as("패치 조인 미적용").isFalse();
        } catch (NonUniqueResultException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void fetchJoinUse() {
        try {
            em.flush();
            em.clear();

            Member findMember = queryFactory
                    .selectFrom(member)
                    .join(member.team,team).fetchJoin()
                    .where(member.username.eq("member1"))
                    .fetchOne();

            boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
            assertThat(loaded).as("패치 조인 미적용").isTrue();
        } catch (NonUniqueResultException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * JPAExpressions
     * 나이가 가장 많은 회원 조회
     */

    @Test
    void subQuery(){
        try {
            QMember memberSub = new QMember("memberSub");

            Member findMember = queryFactory
                    .selectFrom(member)
                    .where(member.age.eq(
                            select(memberSub.age.max())
                                    .from(memberSub)
                    )).fetchOne();
            assertThat(findMember.getAge()).isEqualTo(40);
        } catch (NonUniqueResultException e) {
            throw new RuntimeException(e);
        }

    }

    @DisplayName("in쿼리 사용예시와 검증할때 사용하는법")
    @Test
    void subQueryIn(){
        QMember memberSub = new QMember("memberSub");

        List<Member> findMember = queryFactory
                .selectFrom(member)
                .where(member.age.in(
                        select(memberSub.age)
                                .from(memberSub)
                )).fetch();
        assertThat(findMember).extracting("age")
                .containsExactly(10,20, 30, 40);

    }

    @Test
    void basicCase() {
        List<String> findMembers = queryFactory
                .select(member.age
                        .when(10).then("열살")
                        .when(20).then("스무살")
                        .otherwise("기타"))
                .from(member)
                .fetch();
        for (String findMember : findMembers) {
            System.out.println("findMember = " + findMember);
        }
    }

    @Test
    void complexCase() {
        List<String> fetch = queryFactory
                .select(new CaseBuilder()
                        .when(member.age.between(0, 20)).then("스무살미만")
                        .otherwise("기타"))
                .from(member)
                .fetch();
        for (String s : fetch) {
            System.out.println("s = " + s);
        }

    }

    @Test
    void concat(){
        //username_age
        List<String> fetch = queryFactory
                .select(member.username.concat("_").concat(member.age.stringValue()))
                .from(member)
                .fetch();
        for (String s : fetch) {
            System.out.println("s = " + s);
        }
    }

    @Test
    void simpleProjection() {
        List<String> result = queryFactory
                .select(member.username)
                .from(member)
                .fetch();
        for (String s : result) {
            System.out.println("s = " + s);
        }
    }

    @Test
    void tupleProjection() {
        List<Tuple> result = queryFactory
                .select(member.username, member.age)
                .from(member)
                .fetch();
        for (Tuple tuple : result) {
            String userName = tuple.get(member.username);
            Integer userAge = tuple.get(member.age);
            System.out.println("userAge = " + userAge);
            System.out.println("userName = " + userName);

        }
    }

    @Test
    void findDtoByJPQL(){
        String qlString = "select new com.deamp.restudyjpa.dto.MemberDto(m.username,m.age) " +
                "from Member m";
        List<MemberDto> resultList = em.createQuery(qlString, MemberDto.class).getResultList();
        for (MemberDto memberDto : resultList) {
            System.out.println("memberDto = " + memberDto);
        }

    }

    @Test
    void findDtoBySeeter(){
        //기본 생성자가 있어야됨.
        //프로퍼티 접근방법 중 setter 방법

        List<MemberDto> result = queryFactory
                .select(Projections.bean(MemberDto.class,
                        member.username,
                        member.age))
                .from(member)
                .fetch();
        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }

    }

    @Test
    void findDtoByField(){
        //위방식과 다르게 getter,Setter 말고 필드에 바로 값이 채워짐.

        List<MemberDto> result = queryFactory
                .select(Projections.fields(MemberDto.class,
                        member.username,
                        member.age))
                .from(member)
                .fetch();
        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }

    }

    @Test
    void findDtoByConstructor(){
        //생성자의 매개변수type의 순서와 일치하는것을 불러옴
        List<MemberDto> result = queryFactory
                .select(Projections.constructor(MemberDto.class,
                        member.username,
                        member.age))
                .from(member)
                .fetch();
        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }

    }


    @Test
    void findUserDto(){
        //생성자의 매개변수type의 순서와 일치하는것을 불러옴
        List<UserDto> result = queryFactory
                .select(Projections.fields(UserDto.class,
                        member.username.as("name"), //엔티티의 변수명과 다를때 엘리어스를 이용하여 명칭을 맞쳐줘야됨
                        member.age))
                .from(member)
                .fetch();
        for (UserDto memberDto : result) {
            System.out.println("UserDto = " + memberDto);
        }

    }


    @Test
    void findUserDtoSubQuery(){
        QMember memeberSub = new QMember("memeberSub");
        //생성자의 매개변수type의 순서와 일치하는것을 불러옴
        List<UserDto> result = queryFactory
                .select(Projections.fields(UserDto.class,
                        ExpressionUtils.as(member.username,"name"),
                        ExpressionUtils.as(JPAExpressions //서브쿼리의경우 ex유틸 사용해서 감싸서 사용해야됨
                                .select(memeberSub.age.max())
                                .from(memeberSub),"age")//subquery 결과를 dto의 age에 맞쳐서 넣어줌
                ))
                .from(member)
                .fetch();
        for (UserDto memberDto : result) {
            System.out.println("UserDto = " + memberDto);
        }

    }
//command + p > 가변변수값 뭐가 들어가는지 알수있는 단축키
    @Test
    void findDtoByQueryProjection() {//위에는 런타임에 사용자가 직접 호출시점에 에러가 발생되어 확인이 가능하지만
        //아래의 Qdto를 사용하는 방식은 컴파일시점에 알수있음
        List<MemberDto> result = queryFactory
                .select(new QMemberDto(member.username, member.age))
                .from(member)
                .fetch();
        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @DisplayName("동적쿼리 사용하는법")
    @Test
    void dynamicQuery_BooleanBuilder() {
        String userNmaeParam = "member1";
        Integer ageParam = 10;

        List<Member> result = searchMember1(userNmaeParam, ageParam);

        for (Member member1 : result) {
            System.out.println("member1 = " + member1);
        }

    }

    private List<Member> searchMember1(String userNmaeParam, Integer ageParam) {
        BooleanBuilder builder = new BooleanBuilder();

        if (userNmaeParam != null) {
            builder.and(member.username.eq(userNmaeParam));
        }
        if (ageParam != null) {
            builder.and(member.age.eq(ageParam));
        }

        return  queryFactory
                .selectFrom(member)
                .where(builder)
                .fetch();

    }

    @DisplayName("동적쿼리 이게 더 깔끔함.")
    @Test
    void dynamicQuery_WhereParam() {
        String userNmaeParam = "member1";
        Integer ageParam = 10;

        List<Member> result = searchMember2(userNmaeParam, ageParam);

        for (Member member1 : result) {
            System.out.println("member1 = " + member1);
        }
    }

    private List<Member> searchMember2(String userNmaeParam, Integer ageParam) {
        return queryFactory
                .selectFrom(member)
//                .where(usernameEq(userNmaeParam),ageEq(ageParam))
                .where(allEq(userNmaeParam,ageParam))
                .fetch();
    }

    private BooleanExpression usernameEq(String userNmaeParam) {
        return userNmaeParam != null ?  member.username.eq(userNmaeParam):null;
    }

    private BooleanExpression ageEq(Integer ageParam) {
        return ageParam != null ?  member.age.eq(ageParam):null;
    }

    private BooleanExpression allEq(String userName, Integer age) {
        return usernameEq(userName).and(ageEq(age));
    }

    //벌크연산 ~

    @Test
    void bulkUpdate() {
        /**
         * 영속성 컨텍스트는 그대로인데 db의 값만 바뀜
         * 근데 영속성 컨텍스트에 존재하는값을 db에서 가져오게 되면
         * db보다 우선권한이 더 높은 영속성컨텍스트에서 값을 가져오게되고
         * update이전의 값을 가져오게 되어 문제가 발생할수있음.
         */
        long count = queryFactory
                .update(member)
                .set(member.username, "비회원")
                .where(member.age.lt(28))
                .execute();
        System.out.println("count = " + count);

        //
        em.flush();
        em.clear();
        //벌크 연산이 수행되면 걍 이거 수행하면 됨
    }

    @Test
    void bulkAdd(){
        long execute = queryFactory
                .update(member)
                .set(member.age, member.age.add(1))
                .execute();
        System.out.println("execute = " + execute);

    }

    @Test
    void bulkDelte(){
        long execute = queryFactory
                .delete(member)
                .where(member.age.gt(18))
                .execute();
        System.out.println("execute = " + execute);
    }
    //~ 벌크연산

    //sql function 호출하는방법 ~
    @Test
    void sqlFunction(){
        List<String> result = queryFactory
                .select(
                        Expressions.stringTemplate(
                                "function('replace',{0},{1},{2})",
                                member.username, "member", "M"))
                .from(member)
                .fetch();
        for (String s : result) {
            System.out.println("s = " + s);
        }
    }

    @Test
    void sqlFunction2(){
        List<String> fetch = queryFactory
                .select(member.username)
                .from(member)
//                .where(member.username.eq(
//                        Expressions.stringTemplate(
//                                "function('lower',{0})"
//                                , member.username
//                        )))
                .where(member.username.eq(member.username.lower()))
                .fetch();
        for (String s : fetch) {
            System.out.println("s = " + s);
        }
    }

}
