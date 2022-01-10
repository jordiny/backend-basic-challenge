package com.jmontanez.challenge.bcp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthFilter {
    private String username;
    private String password;
}
