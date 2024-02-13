package jpabook.jpashop.domain2;

import javax.persistence.*;

@Entity
public class TmemberA {

    @Id @GeneratedValue
    private Long id;

    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private TmemberB tmemberB;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private TmemberC tmemberC;

    private int age;
    private String Ais;


}
