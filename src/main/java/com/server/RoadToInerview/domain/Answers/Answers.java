package com.server.RoadToInerview.domain.Answers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.RoadToInerview.domain.BaseEntity;
import com.server.RoadToInerview.domain.Questions.Questions;
import com.server.RoadToInerview.domain.users.Users;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@EntityListeners(value = EntityListeners.class)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true,exclude = {"users_id","questions_id"})
public class Answers extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String answer;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="users_id")
    @JsonIgnore
    private Users usersId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="questions_id")
    @JsonIgnore
    private Questions questionsId;

}
