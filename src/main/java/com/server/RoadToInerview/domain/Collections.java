package com.server.RoadToInerview.domain;

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
    @ToString.Exclude
    @ManyToOne(targetEntity =  Users.class,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Integer users_id;
    @ToString.Exclude
    @ManyToOne(targetEntity =  Interviews.class,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Integer interviews_id;

}
