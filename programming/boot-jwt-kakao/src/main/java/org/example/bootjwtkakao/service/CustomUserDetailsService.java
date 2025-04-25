package org.example.bootjwtkakao.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.example.bootjwtkakao.model.entity.KakaoUser;
import org.example.bootjwtkakao.model.repository.KakaoUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log
public class CustomUserDetailsService implements UserDetailsService {
    private final KakaoUserRepository kakaoUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        KakaoUser account = kakaoUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("유저가 없습니다. %s".formatted(username)));
        log.info("roles : %s".formatted(account.getRole()));
        return User.builder()
                .username(account.getUsername())
                .password("")
                .roles(account.getRole())
                .build();
    }
}