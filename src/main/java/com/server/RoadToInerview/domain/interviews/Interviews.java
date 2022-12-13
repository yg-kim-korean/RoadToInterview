package com.server.RoadToInerview.domain.interviews;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.RoadToInerview.domain.BaseEntity;
import com.server.RoadToInerview.domain.Category.Categorys;
import com.server.RoadToInerview.domain.Collections;
import com.server.RoadToInerview.domain.Questions;
import com.server.RoadToInerview.domain.users.Users;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
@EntityListeners(value = EntityListeners.class)
@ToString(callSuper = true)
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
    private Users users_id;


    @OneToMany(mappedBy = "interviews_id",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Collections> collections = new ArrayList<>();


    @OneToMany(mappedBy = "interviews_id",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Questions> questions = new ArrayList<>();

    @ManyToMany(targetEntity =  Categorys.class,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "cate_inter")
    @JsonIgnore
    private List<Categorys> categorys = new ArrayList<>();
}
