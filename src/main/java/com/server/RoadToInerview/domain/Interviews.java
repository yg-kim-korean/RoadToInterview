package com.server.RoadToInerview.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
@Entity
@EntityListeners(value = EntityListeners.class)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Interviews extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;


    @ManyToOne(targetEntity =  Users.class,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private int users_id;

    @ToString.Exclude
    @OneToMany(mappedBy = "interviews_id",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Collections> collections = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "interviews_id",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Questions> questions = new ArrayList<>();

    @ToString.Exclude
    @ManyToMany(targetEntity =  Category.class,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "cate_inter")
    private List<Category> categories = new ArrayList<>();
}
