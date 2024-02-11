package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberJpaRepository repository;

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

        assertThat(findAllMember).isEqualTo(2);

        //카운트 검증
        long memberCount = repository.count();
        assertThat(memberCount).isEqualTo(2);

        repository.deleteAll();

        long nowCount = repository.count();
        assertThat(nowCount).isEqualTo(0);
    }
}