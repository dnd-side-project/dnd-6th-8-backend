package com.travel.domain.config.auth;

import com.travel.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
public class JwtAuthFilter extends GenericFilterBean {
    private final TokenService tokenService;
    private final UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String bearerToken = ((HttpServletRequest)request).getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7);
            if(tokenService.verifyToken(token)){
                String email = tokenService.getUid(token);

                UserDto userDto = userService.findByEmail(email);

                Authentication auth = getAuthentication(userDto.getEmail());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        }

        chain.doFilter(request, response);
    }

    public Authentication getAuthentication(String userEmail) {
        return new UsernamePasswordAuthenticationToken(userEmail, "",
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}