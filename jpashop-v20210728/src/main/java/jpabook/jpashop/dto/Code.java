package jpabook.jpashop.dto;

import jpabook.jpashop.config.CommonCodeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString
@EntityListeners(CommonCodeService.class) // CommonCodeService의 이벤트 리스너 등록
public class Code {

    @GeneratedValue
    @Id
    private int id;
    private String code;

}
