//package com.travel.domain.config.auth;
//
//import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class OAuth2AuthorizationRequestRedirectFilter extends OncePerRequestFilter {
//    public static final String DEFAULT_AUTHORIZATION_REQUEST_BASE_URI = "/oauth2/authorization";
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                    FilterChain filterChain)
//            throws ServletException, IOException {
//
//        try {
//            OAuth2AuthorizationRequest authorizationRequest = this.authorizationRequestResolver.resolve(request);
//            if (authorizationRequest != null) {
//                this.sendRedirectForAuthorization(request, response, authorizationRequest);
//                return;
//            }
//        } catch (Exception failed) {
//            this.unsuccessfulRedirectForAuthorization(request, response, failed);
//            return;
//        }
//    }
//}
