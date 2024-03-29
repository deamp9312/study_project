package com.deamp.restudyjpa;

import com.deamp.restudyjpa.entity.Hello;
import com.deamp.restudyjpa.entity.QHello;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class RestudyjpaApplicationTests {

	@Autowired
	EntityManager em;

	@DisplayName("querydsl 잘 되는가?")
	@Test
	void contextLoads() {
		Hello hello = new Hello();
		em.persist(hello);
		JPAQueryFactory query = new JPAQueryFactory(em);
		QHello qHello = QHello.hello;

		Hello result = query
				.selectFrom(qHello)
				.fetchOne();

		assertThat(result).isEqualTo(hello);
		assertThat(result.getId()).isEqualTo(hello.getId());
	}

	@Value("${app.name}")
	private String userName;
	@Test
	void ymlValueTest(){
		assertThat(userName).isEqualTo("ChoDongChan");
	}

}

