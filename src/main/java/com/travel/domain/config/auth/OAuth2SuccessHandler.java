package com.travel.domain.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.domain.user.entity.User;
import com.travel.domain.user.repository.UserRepository;
import com.travel.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final TokenService tokenService;
    private final UserRequestMapper userRequestMapper;
    private final ObjectMapper objectMapper;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        UserDto userDto = userRequestMapper.toDto(oAuth2User);
//        userRepository.getById(userDto.getEmail());
        int newUser = 1;
        System.out.println(userDto.getEmail());
        if(userService.findByEmail(userDto.getEmail()) == null){
            System.out.println("saving user");
            userService.save(userDto);
            newUser = 0;
        }
        User user = userRepository.findByEmail(userDto.getEmail());
        Token token = tokenService.generateToken(userDto.getEmail(), "USER");
        String redirect = "/" + token.getAccessToken() + "/" + newUser + "/" + URLEncoder.encode(user.getUserName());

        response.sendRedirect("http://tracious.shop/callback" + redirect);
    }


//    @ApiOperation(value = "아카이브 id로 가져오기 API")
//    @GetMapping("/archives/{id}")
//    public ResponseEntity<ArchiveDetailResponseDto> findById(@PathVariable Long id){
//        return ResponseEntity.ok(archivesService.findById(id));
//    }

    @GetMapping("/oauth2/{accessToken}/{refreshToken}")
    private ResponseEntity <Void> returnTokens(@PathVariable String accessToken, @PathVariable String refreshToken){
        return ResponseEntity.ok().build();
    }
}
