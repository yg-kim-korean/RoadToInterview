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
public class Category extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    private Integer category_id;
    @ToString.Exclude
    @ManyToOne(targetEntity =  Users.class,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Integer users_id;
}
