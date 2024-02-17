package jpabook.jpashop.fastcampus.repository;

import jpabook.jpashop.fastcampus.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Date;


@Commit
@Transactional
@SpringBootTest
class fastcamTest {

    @Autowired
    EntityManagerFactory emf;

    @Autowired
    EntityManager em;

    @Test
    void fastcamTest(){
//        EntityManager em = emf.createEntityManager();
        User user = User.builder()
                .id("aaa")
                .password("1234")
                .name("lee")
                .email("aaa@aaa.com")
                .inDate(new Date())
                .upDate(new Date())
                .build();

        System.out.println("user = " + user);

        em.persist(user);

    }

}