package com.server.RoadToInerview.domain.interviews;

import com.server.RoadToInerview.domain.Questions.Questions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewsPostForm {
    private String title;
    private String description;
    private List<Integer> categorysList;
    private List<Questions> questions;
}
