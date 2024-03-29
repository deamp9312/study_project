package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {
    @Autowired
    MemberJpaRepository repository;

    //동일한 명칭 한번에 변경 (shift + fn+ f6 )
    @Test
    void basicCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        repository.save(member1);
        repository.save(member2);

        //단건 조회
        Member findMember = repository.findById(member1.getId()).get();
        assertThat(findMember.getName()).isEqualTo("member1");
        //리스트 조회
        List<Member> findAllMember = repository.findAll();

        for (Member member : findAllMember) {
            System.out.println("member = " + member);
        }

        assertThat(findAllMember.size()).isEqualTo(2);

        //카운트 검증
        long memberCount = repository.count();
        assertThat(memberCount).isEqualTo(2);

        repository.deleteAll();

        long nowCount = repository.count();
        assertThat(nowCount).isEqualTo(0);
    }


    @Test
    void findUserName(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        repository.save(m1);
        repository.save(m2);
        List<Member> findMember = repository.findByNameAndAgeGreaterThan("AAA", 15);
        assertThat(findMember.size()).isEqualTo(1);
    }

    @Test
    void findUsernameList(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        repository.save(m1);
        repository.save(m2);
        List<String> usernameList = repository.findUsernameList();
        for (String s : usernameList) {
            System.out.println("s = " + s);
        }
    }

    @Test
    void findByNames(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        repository.save(m1);
        repository.save(m2);
        List<Member> byNames = repository.findByNames(Arrays.asList("AAA", "BBB"));
        for (Member byName : byNames) {
            System.out.println("byName = " + byName);
        }

    }

    @Test
    void bulkAgePlus(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        repository.save(m1);
        repository.save(m2);
        int resultint = repository.bulkAgePlus(10);
        System.out.println("resultint = " + resultint);

        List<Member> all = repository.findAll();
        for (Member member : all) {
            System.out.println("member = " + member);
        }


    }


    @Test
    void findMemberFetchJoin() {
        List<Member> all = repository.findMemberFetchJoin();
        for (Member member : all) {
            System.out.println("member = " + member);
            System.out.println("member order= " + member.getOrders().get(0).getTotalPrice());
        }
    }

    @Test
    void findAllGraph(){
        List<Member> all = repository.findAll();
        for (Member member : all) {
            System.out.println("member = " + member.getOrders().get(0).getOrderDate());
        }
    }
}