package jpabook.jpashop.domain2;

import jpabook.jpashop.domain.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TmemberB {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "tmemberB")
    private List<TmemberA> tmemberA = new ArrayList<>();

    private int age;
    private String Bis;

}
