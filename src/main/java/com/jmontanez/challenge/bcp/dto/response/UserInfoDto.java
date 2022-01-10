package com.jmontanez.challenge.bcp.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserInfoDto {
    private String id;
    private String username;
    private String fullname;
    private String email;
    private String token;
}
