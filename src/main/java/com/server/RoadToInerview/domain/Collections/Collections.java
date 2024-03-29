package com.server.RoadToInerview.domain.Collections;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.RoadToInerview.domain.BaseEntity;
import com.server.RoadToInerview.domain.interviews.Interviews;
import com.server.RoadToInerview.domain.users.Users;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@EntityListeners(value = EntityListeners.class)
@ToString(callSuper = true,exclude = {"users_id","interviews_id"})
@EqualsAndHashCode(callSuper = true)
public class Collections extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="users_id")
    @JsonIgnore
    private Users usersId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="interviews_id")
    @JsonIgnore
    private Interviews interviewsId;

}
