package org.example.bootjwtsecurity.controller;

import org.apache.coyote.BadRequestException;
import org.example.bootjwtsecurity.model.dto.AuthTokenDTO;
import org.example.bootjwtsecurity.model.dto.UserRequestDTO;
import org.example.bootjwtsecurity.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AccountService accountService;

    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserRequestDTO dto) throws BadRequestException {
        accountService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthTokenDTO> login(@RequestBody UserRequestDTO dto) throws BadRequestException {
        AuthTokenDTO tokenDTO = accountService.login(dto);
        return ResponseEntity.ok(tokenDTO);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Void> handleBadRequestException(BadRequestException e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}