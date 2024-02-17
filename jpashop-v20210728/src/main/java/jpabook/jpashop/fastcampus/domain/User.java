package jpabook.jpashop.fastcampus.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "my_user")
public class User {

    @Id
    private String id;

    private String password;

    private String name;

    private String email;

    private Date inDate;
    private Date upDate;

}
