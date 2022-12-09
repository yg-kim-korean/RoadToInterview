package com.server.RoadToInerview.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersTokens {
    private Users users;
    private String accessToken;
    private String refreshToken;
    private Boolean verified;
}
