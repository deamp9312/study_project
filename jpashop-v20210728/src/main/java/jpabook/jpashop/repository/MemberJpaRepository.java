package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.dto.MemberDto;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Entity;
import java.util.Collection;
import java.util.List;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    List<Member> findByNameAndAgeGreaterThan(String name, int age);


    @Query("select m from Member m " +
            "where m.name = :username " +
            "and m.age =:age")
    List<Member> findMember(@Param("username") String username, @Param("age") int age);

    /**
     * 값이나 dto를 조회할때 사용법
     */

    @Query("select m.name from Member m")
    List<String> findUsernameList();

//    @Query("select m from Member m join m.team t")
//    List<MemberDto> findMemberDto();

    /**
     * 파라미터 바인딩
     */
    @Query("select m from Member m where m.name in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);

//    @Query("update Member m set m.age = m.age +1 where m.age>= :age")
//    int bulkAgePlus(@Param("age") int age);

    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);


    //N+1 문제 해결법
    @Query("select m from Member m left join fetch m.orders")
    List<Member> findMemberFetchJoin();
    //위와 비슷한 방법인데 어노테이션 사용하여 해결하는 방법
    @Override
    @EntityGraph(attributePaths = {"orders"})
    List<Member> findAll();

}
