package jpabook.jpashop.domain3.domain2;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class MemberA {

    @Id @GeneratedValue
    private Long id;
    private String username;


}

