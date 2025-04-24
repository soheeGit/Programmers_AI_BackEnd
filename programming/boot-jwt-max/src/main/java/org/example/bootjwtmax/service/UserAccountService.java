package org.example.bootjwtmax.service;

import org.apache.coyote.BadRequestException;
import org.example.bootjwtmax.model.dto.TokenResponseDTO;
import org.example.bootjwtmax.model.dto.UserAccountRequestDTO;
import org.example.bootjwtmax.model.entity.UserAccount;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserAccountService {
    void join(UserAccountRequestDTO dto) throws BadRequestException;

    TokenResponseDTO login(UserAccountRequestDTO dto) throws BadRequestException, UsernameNotFoundException;

    void joinAdmin(UserAccountRequestDTO dto) throws BadRequestException;
}