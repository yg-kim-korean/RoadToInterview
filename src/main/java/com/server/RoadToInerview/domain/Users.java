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
public class Users extends BaseEntity{

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

    @ToString.Exclude
    @OneToMany(mappedBy = "users_id",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Category> categories = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "users_id",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Interviews> interviews = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "users_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Collections> collections = new ArrayList<>();


}
