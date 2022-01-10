package com.jmontanez.challenge.bcp.service.implementations;

import com.jmontanez.challenge.bcp.common.security.JwtTokenProvider;
import com.jmontanez.challenge.bcp.dto.request.AuthFilter;
import com.jmontanez.challenge.bcp.dto.response.UserInfoDto;
import com.jmontanez.challenge.bcp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public UserInfoDto authenticate(AuthFilter filter) {
        //TODO: Validate user
        UserInfoDto userInfoDto = UserInfoDto.builder()
                .id("e85b-32d3-a456-56415461")
                .email("jordinym@gmail.com")
                .fullname("Jordiny Montañez Flores")
                .username(filter.getUsername())
                .build();

        String token = jwtTokenProvider.generateAccessToken(userInfoDto);
        userInfoDto.setToken(token);
        return userInfoDto;
    }
}
