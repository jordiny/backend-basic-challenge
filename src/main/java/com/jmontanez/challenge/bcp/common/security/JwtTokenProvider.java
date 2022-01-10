package com.jmontanez.challenge.bcp.common.security;

import com.jmontanez.challenge.bcp.common.Constants;
import com.jmontanez.challenge.bcp.dto.response.UserInfoDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Component
public class JwtTokenProvider {

    @Value("${security.jwt.id}")
    private String jwtId;

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length}")
    private long expireLength;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String generateAccessToken(UserInfoDto userInfoDto) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        return Jwts
                .builder()
                .setId(jwtId)
                .setSubject(format("%s,%s,%s,%s", userInfoDto.getId(), userInfoDto.getUsername(),userInfoDto.getFullname(),userInfoDto.getEmail()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireLength))
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
    public Claims validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(Constants.JWT_VALIDATION_FILTER_HEADER).replace(Constants.JWT_VALIDATION_FILTER_PREFIX, "");
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken).getBody();
    }
}
