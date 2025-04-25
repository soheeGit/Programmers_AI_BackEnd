package org.example.bootjwtkakao.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.example.bootjwtkakao.auth.JwtTokenProvider;
import org.example.bootjwtkakao.model.entity.KakaoUser;
import org.example.bootjwtkakao.model.repository.KakaoUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
    private final KakaoUserRepository kakaoUserRepository;

    @SuppressWarnings("unchecked")
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();
        // 카카오 응답 구조 : kakao_account -> , profile -> nickname
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
        String kakaoId = attributes.get("id").toString();
        String nickname = profile.get("nickname").toString();

        String username = "kakao_%s".formatted(kakaoId);

        // DB 저장 혹은 조회
        KakaoUser kakaoUser = kakaoUserRepository.findByUsername(username)
                .orElseGet(() -> {
                    KakaoUser newUser = new KakaoUser();
                    newUser.setUsername(username);
                    newUser.setName(nickname);
                    newUser.setRole("KAKAO"); // 빼먹은거
                    return kakaoUserRepository.save(newUser);
                });
        log.info(kakaoUser.toString());
        return oAuth2User;
    }

    @Service
    @RequiredArgsConstructor
    public static class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
        private final JwtTokenProvider jwtTokenProvider;

        @Value("${front-end.redirect}")
        private String frontEndRedirect;

        @Override
        public void onAuthenticationSuccess(HttpServletRequest req,
                                            HttpServletResponse res,
                                            Authentication authentication) throws IOException, ServletException {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            String id = oAuth2User.getAttributes().get("id").toString();
            String username = "kakao_%s".formatted(id);
            String token = jwtTokenProvider.generateToken(new UsernamePasswordAuthenticationToken(username, ""), List.of("KAKAO"));
//            ObjectMapper objectMapper = new ObjectMapper();
//            Map<String, String> result = Map.of("token", token);
//            res.setContentType("application/json;charset=UTF-8");
//            res.getWriter().write(objectMapper.writeValueAsString(result));
            // value -> redirect url
            String redirectUrl = UriComponentsBuilder
                    .fromUriString(frontEndRedirect)
                    .queryParam("token", token)
                    .build().toUriString();
            res.sendRedirect(redirectUrl);
        }
    }
}