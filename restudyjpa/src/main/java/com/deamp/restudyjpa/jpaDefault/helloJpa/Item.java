package com.deamp.restudyjpa.jpaDefault.helloJpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Inheritance(strategy = InheritanceType.JOINED)
 * JOINED : 조인 전략
 * SINGLE_TABLE : 단일 테이블 전략
 * TABLE_PER_CLASS : 구현 클래스마다 테이블 전략 > 사용하지말자
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn // Dtype
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;

}
