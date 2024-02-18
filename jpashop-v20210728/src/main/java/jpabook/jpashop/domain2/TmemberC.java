package jpabook.jpashop.domain2;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class TmemberC {

    @Id
    @GeneratedValue
    @Column(name = "teamC_id")
    private Long id;

    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "tmemberC")
    private List<TmemberA> tmemberA = new ArrayList<>();


}
