package com.server.RoadToInerview.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPutForm {
    private String email;
    private String nickname;
    private String password;
}
