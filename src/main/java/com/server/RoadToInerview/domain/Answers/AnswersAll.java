package com.server.RoadToInerview.domain.Answers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswersAll {
    private List<Answers> mobum;
    private List<Answers> answers;
}
