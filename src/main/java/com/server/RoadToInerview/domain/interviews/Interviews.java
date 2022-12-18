package com.server.RoadToInerview.domain.interviews;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.RoadToInerview.domain.BaseEntity;
import com.server.RoadToInerview.domain.Categorys.Categorys;
import com.server.RoadToInerview.domain.Collections;
import com.server.RoadToInerview.domain.Questions.Questions;
import com.server.RoadToInerview.domain.users.Users;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
@EntityListeners(value = EntityListeners.class)
@ToString(callSuper = true,exclude = {"users_id","categorys"})
@EqualsAndHashCode(callSuper = true)
public class Interviews extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id")
    @JsonIgnore
    @Column(name = "users_id")
    private Users usersId;


    @OneToMany(mappedBy = "interviewsId",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Collections> collections = new ArrayList<>();


    @OneToMany(mappedBy = "interviewsId",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Questions> questions = new ArrayList<>();

    @ManyToMany(targetEntity =  Categorys.class,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "cate_inter")
    @JsonIgnore
    private List<Categorys> categorys = new ArrayList<>();
}
