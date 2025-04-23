package org.example.bootjwtsecurity.service;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.bootjwtsecurity.auth.JwtTokenProvider;
import org.example.bootjwtsecurity.model.dto.AuthTokenDTO;
import org.example.bootjwtsecurity.model.dto.UserRequestDTO;
import org.example.bootjwtsecurity.model.entity.Account;
import org.example.bootjwtsecurity.model.repository.AccountRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtTokenProvider jwtTokenProvider;

    public void register(UserRequestDTO dto) throws BadRequestException {
        if (dto.username().isEmpty() || dto.password().isEmpty()) {
            throw new BadRequestException("잘못된 입력!");
        }
        Account account = new Account();
        account.setUsername(dto.username());
        account.setPassword(passwordEncoder.encode(dto.password())); // BCrypt
        // UUID.
        accountRepository.save(account);
    }

    public AuthTokenDTO login(UserRequestDTO dto) throws BadRequestException {
        if (dto.username().isEmpty() || dto.password().isEmpty()) {
            throw new BadRequestException("잘못된 입력!");
        }
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.username(), dto.password())
        );
        String token = jwtTokenProvider.generateToken(auth);
        return new AuthTokenDTO(token);
    }
}