package com.server.RoadToInerview.domain.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.RoadToInerview.domain.BaseEntity;
import com.server.RoadToInerview.domain.users.Users;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@EntityListeners(value = EntityListeners.class)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Categorys extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    private Integer categorys_id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="users_id")
    @JsonIgnore
    private Users users_id;
}
