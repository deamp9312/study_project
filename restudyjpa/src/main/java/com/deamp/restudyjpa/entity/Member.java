package com.deamp.restudyjpa.entity;

import com.deamp.restudyjpa.jpaDefault.Order;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
//@SequenceGenerator(
//        name = "member_seq_generator",
//        sequenceName = "member_seq",
//        initialValue = 1 , allocationSize = 50) //allocationSize 는 db에서 한번에 50개를 가져온뒤에 메모리상에서 차례대로 사용한뒤에 해당 번호를 다 사용하면 다시 50개 가져오는 방식
public class Member {
    /**
     * 유니크 제약조건 설정방법 ( 엔티티 위에다가 선언해서 사용)
     * @Table(uniqueConstraints
     * = {@UniqueConstraint(name="UniqueTestName",columnNames
     * = {"username","age"})})
     *
     * @Column
     * Name : 필드와 매핑할 테이블의 컬럼 이름
     * insertable,updatable : 생성 , 수정을 제한할수 있음 기본값은 True
     * nullable : null값의 허용 여부를 설정한다 false > not null 제약붙음
     * unique : 유니크 제약조건 설정 > 이름이 랜덤생성되기때문에 효율성은 x
     *
     * @Enumerated
     * ORDINAL : enum 순서를 db에 저장 ( 순서가 바뀌거나 추가되면 문제발생)
     * STRING : enum 이름을 db에 저장 (이거 사용한다고 생각하면 됨)
     *
     * @Temporal(TemporalType.TIMESTAMP)
     * 그냥 LocalDate , LocalDateTime 로 변수선언해서 사용하면 됨
     */
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE,generator = "member_seq")
    @Column(name = "member_id")
    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;
    private String username;
    private int age;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createDate;
    private LocalDateTime createDate;

    @Lob //clob blob ?
    private String description;

    @Transient //db에 반영하지 않고 케시에서만 사용하려고 할때 사용하는법
    private int temp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();


    public Member(String username) {
        this(username, 0);
    }
    public Member(String username, int age) {
        this(username, age, null);
    }
    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }
    public void changeTeam(Team team) {
        this.team = team;
        //연관관계 편의 메서드를 이용해서 사용하기
        team.getMembers().add(this);
    }

}
