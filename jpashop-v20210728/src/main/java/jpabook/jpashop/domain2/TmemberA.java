package jpabook.jpashop.domain2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class TmemberA {

    @Id @GeneratedValue
    @Column(name = "teamA_id")
    private Long id;
    private String username;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamB_id")
    private TmemberB tmemberB;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamC_id")
    private TmemberC tmemberC;

}
