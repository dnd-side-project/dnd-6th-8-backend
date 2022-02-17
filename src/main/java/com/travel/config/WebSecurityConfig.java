package com.travel.config;

import com.travel.domain.config.auth.test.CustomOAuth2UserService;
import com.travel.domain.config.auth.test.JwtAuthFilter;
import com.travel.domain.config.auth.test.OAuth2SuccessHandler;
import com.travel.domain.config.auth.test.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService oAuth2UserService;
    private final OAuth2SuccessHandler successHandler;
    private final TokenService tokenService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/oauth2/**",
                        "/v2/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui/**",
                        "/auth/**",
                        "/token/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login().loginPage("/token/expired")
                .successHandler(successHandler)
                .userInfoEndpoint().userService(oAuth2UserService);

        http.addFilterBefore(new JwtAuthFilter(tokenService), UsernamePasswordAuthenticationFilter.class);
    }

}


//import com.travel.domain.config.auth.token.OAuth2SuccessHandler;
//import lombok.AllArgsConstructor;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@EnableWebSecurity
//@AllArgsConstructor
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    private final CustomOAuth2UserService oAuth2UserService;
//    private final OAuth2SuccessHandler successHandler;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/oauth2/**",
//                        "/v2/api-docs",
//                        "/swagger-resources/**",
//                        "/swagger-ui/**",
//                        "/auth/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .oauth2Login()
//                .successHandler(successHandler)
//                .userInfoEndpoint().userService(oAuth2UserService);;
//    }
//}
