package com.server.RoadToInerview.domain.Questions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.RoadToInerview.domain.Answers.Answers;
import com.server.RoadToInerview.domain.BaseEntity;
import com.server.RoadToInerview.domain.interviews.Interviews;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@EntityListeners(value = EntityListeners.class)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true,exclude = {"interviews_id"})
public class Questions extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Integer limit_time;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "interviews_id")
    private Interviews interviewsId;

    @OneToMany(mappedBy = "questionsId",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Answers> answers = new ArrayList<>();

}
