package com.server.RoadToInerview.domain.Answers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswersPostForm {
    private Long questions_id;
    private String answer;
}
