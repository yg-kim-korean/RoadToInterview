package com.server.RoadToInerview.domain.interviews;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewsPostForm {
    private String title;
    private int categorys_id;
}
