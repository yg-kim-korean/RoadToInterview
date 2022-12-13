package com.server.RoadToInerview.domain.users;

import com.server.RoadToInerview.domain.users.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResultForm {
    private String accessToken;
    private Users users;
}

