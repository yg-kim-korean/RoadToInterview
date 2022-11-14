package com.server.RoadToInerview.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @Column(unique = true)
    private String email;

    private String password;

    private String salt;

    private boolean emailauth;

    private boolean manager;

    private String src;

    @Builder.Default
    @OneToMany(mappedBy = "users_id",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Category> categories = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "users_id",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Interviews> interviews = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "users_id",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Collections> collections = new ArrayList<>();

}
