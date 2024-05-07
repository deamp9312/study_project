package jpabook.jpashop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString
public class Code {

    @GeneratedValue
    @Id
    private int id;
    private String code;

}
