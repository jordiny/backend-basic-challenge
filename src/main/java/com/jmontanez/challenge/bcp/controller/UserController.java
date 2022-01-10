package com.jmontanez.challenge.bcp.controller;

import com.jmontanez.challenge.bcp.dto.request.AuthFilter;
import com.jmontanez.challenge.bcp.dto.response.UserInfoDto;
import com.jmontanez.challenge.bcp.dto.response.common.Response;
import com.jmontanez.challenge.bcp.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api/users")
@Tag(name = "Users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/authenticate")
    public Response<UserInfoDto> login(@RequestBody AuthFilter filter){
        Response<UserInfoDto> response=Response.ok();
        return response.setData(userService.authenticate(filter));
    }
}
