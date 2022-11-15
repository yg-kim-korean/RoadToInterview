package com.server.RoadToInerview.domain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Category extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    private Integer category_id;

    @ManyToOne(targetEntity =  Users.class,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Integer users_id;
}
