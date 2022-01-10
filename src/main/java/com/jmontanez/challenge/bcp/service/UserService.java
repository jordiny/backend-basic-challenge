package com.jmontanez.challenge.bcp.service;

import com.jmontanez.challenge.bcp.dto.request.AuthFilter;
import com.jmontanez.challenge.bcp.dto.response.UserInfoDto;

public interface UserService {
    UserInfoDto authenticate(AuthFilter filter);
}
