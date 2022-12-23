package com.server.RoadToInerview.domain.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOauthLoginForm {
    private String email;
    private String nickname;
    private String src;
    private Long emailauth;
}
