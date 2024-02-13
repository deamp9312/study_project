package jpabook.jpashop.domain2;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TmemberC {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "tmemberC")
    private List<TmemberA> tmemberA = new ArrayList<>();

    private int age;
    private String Cis;

}
