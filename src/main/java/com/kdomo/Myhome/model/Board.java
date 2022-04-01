package com.kdomo.Myhome.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class Board {
    @Id
    @SequenceGenerator(name="seq_board",sequenceName="seq_board" ,allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_board")
    private Long id;

    @NotNull
    @Size(min=2, max=30,message = "제목은 2자이상 30자 이하입니다.")
    private String title;

    private String content;

}
