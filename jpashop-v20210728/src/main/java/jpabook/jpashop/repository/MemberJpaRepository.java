package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberJpaRepository extends JpaRepository<Member,Long> {

    List<Member> findByNameAndAgeGreaterThan(String name, int age);


    @Query("select m from Member m " +
            "where m.name = :username " +
            "and m.age =:age")
    List<Member> findMember(@Param("username") String username, @Param("age") int age);


}
