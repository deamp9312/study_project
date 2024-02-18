package jpabook.jpashop.fastcampus.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@TableGenerator(name = "board_seq_generator",
        table = "SEQUENCE_TABLE",
        pkColumnName = "SEQ_NAME",
        pkColumnValue = "BOARD_SEQ",
        valueColumnName = "SEQ_VALUE",
        allocationSize = 100)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq_generator")
    @SequenceGenerator(name = "board_seq_generator", sequenceName = "my_sequence", allocationSize = 1)
    private Long bno; //게시물 번호

    private String title;
    private String writer;
    private String content;

    private Long viewCnt;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date inDate;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date upDate;


}
