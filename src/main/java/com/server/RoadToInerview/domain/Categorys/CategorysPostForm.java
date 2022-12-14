package com.server.RoadToInerview.domain.Categorys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategorysPostForm {
    private int categorys_id;
    private String category;
}
