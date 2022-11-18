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
public class Questions extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Integer limit_time;

    @ManyToOne(targetEntity =  Interviews.class,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Integer interviews_id;
}
