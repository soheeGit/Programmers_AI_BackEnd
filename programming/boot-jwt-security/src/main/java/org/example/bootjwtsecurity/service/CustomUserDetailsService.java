package org.example.bootjwtsecurity.service;

import lombok.RequiredArgsConstructor;
import org.example.bootjwtsecurity.model.entity.Account;
import org.example.bootjwtsecurity.model.repository.AccountRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("유저를 찾을 수 없습니다. : %s".formatted(username))
        );
        return User.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .build();
    }
}