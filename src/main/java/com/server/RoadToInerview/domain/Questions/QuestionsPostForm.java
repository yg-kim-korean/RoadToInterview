package com.server.RoadToInerview.domain.Questions;

import com.server.RoadToInerview.domain.interviews.Interviews;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionsPostForm {
    private String title;
    private String description;
    private Integer limit_time;
    private Interviews interviews;
}
