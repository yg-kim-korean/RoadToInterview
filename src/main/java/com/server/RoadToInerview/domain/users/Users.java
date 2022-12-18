package com.server.RoadToInerview.domain.users;

import com.server.RoadToInerview.domain.BaseEntity;
import com.server.RoadToInerview.domain.Categorys.Categorys;
import com.server.RoadToInerview.domain.Collections;
import com.server.RoadToInerview.domain.interviews.Interviews;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
@Entity
@EntityListeners(value = EntityListeners.class)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Users extends BaseEntity {

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


    @OneToMany(mappedBy = "usersId",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Categorys> categorys = new ArrayList<>();


    @OneToMany(mappedBy = "usersId",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Interviews> interviews = new ArrayList<>();


    @OneToMany(mappedBy = "usersId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Collections> collections = new ArrayList<>();


}
