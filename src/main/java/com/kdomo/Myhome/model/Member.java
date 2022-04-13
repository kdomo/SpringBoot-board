package com.kdomo.Myhome.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Member {
    @Id
    @SequenceGenerator(name="seq_member",sequenceName="seq_member" ,allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="seq_member")
    private Long id;

    private String username;
    private String password;
    private String enabled;

    @ManyToMany
    @JoinTable(
            name="member_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

}
