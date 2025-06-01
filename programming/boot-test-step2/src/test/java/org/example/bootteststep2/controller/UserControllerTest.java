package org.example.bootteststep2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.bootteststep2.entity.User;
import org.example.bootteststep2.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
// MockMVC
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("UserController 웹 계층 테스트")
@WebMvcTest(UserController.class) // 웹 계층을 불러옴
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService; // Service가 이번엔 Mock임

    @Autowired
    private ObjectMapper objectMapper;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User("모기", "mogi@paris.com", 1);
        testUser.setId(1L);
    }

    // GET
    @Test
    @DisplayName("사용자 조회 API")
    void getUserById() throws Exception {
        // Given
        given(userService.findById(1L)).willReturn(Optional.of(testUser));
        // When & Then
        mockMvc.perform(get("/{id}", 1L))
                // 응답 코드
                .andExpect(status().isOk()) // 200
                // 미디어 타입
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // 응답 결과
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("모기"))
                .andExpect(jsonPath("$.age").value(1))
                .andExpect(jsonPath("$.email").value("mogi@paris.com"));

        verify(userService).findById(1L);
    }

    // POST
}