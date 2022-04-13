package com.kdomo.Myhome.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Role {
    @Id
    @SequenceGenerator(name="seq_role",sequenceName="seq_role" ,allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="seq_role")
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<Member> members;
}
