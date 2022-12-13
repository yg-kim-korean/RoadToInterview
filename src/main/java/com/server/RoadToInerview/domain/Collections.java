package com.server.RoadToInerview.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.RoadToInerview.domain.interviews.Interviews;
import com.server.RoadToInerview.domain.users.Users;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
@EntityListeners(value = EntityListeners.class)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Collections extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="users_id")
    @JsonIgnore
    private Users users_id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="interviews_id")
    @JsonIgnore
    private Interviews interviews_id;

}
