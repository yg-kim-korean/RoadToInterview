package com.server.RoadToInerview.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
@Entity
public class Interviews extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;


    @ManyToOne(targetEntity =  Users.class,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private int users_id;

    @Builder.Default
    @OneToMany(mappedBy = "interviews_id",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Collections> collections = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "interviews_id",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Questions> questions = new ArrayList<>();

    @Builder.Default
    @ManyToMany(targetEntity =  Category.class,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "cate_inter")
    private List<Category> categories = new ArrayList<>();
}