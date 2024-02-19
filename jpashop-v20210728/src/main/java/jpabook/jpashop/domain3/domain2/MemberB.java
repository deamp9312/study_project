package jpabook.jpashop.domain3.domain2;

import jpabook.jpashop.domain2.TmemberA;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class MemberB {
    @Id
    @GeneratedValue
    private Long id;

    private String username;



}
