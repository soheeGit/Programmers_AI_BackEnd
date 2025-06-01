package org.example.bootteststep2.service;

import org.example.bootteststep2.entity.User;
import org.example.bootteststep2.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

// 일반적으로...
// import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DisplayName("UserService 단위 테스트")
@ExtendWith(MockitoExtension.class) // 가짜 객체 -> 의존성 주입
public class UserServiceTest {
    @Mock
    private UserRepository userRepository; // 의존성 주입 당할 대상
    @InjectMocks
    private UserService userService; // 의존성 주입을 할 대상
    private User testUser;

    @BeforeEach
    void setUp() {
        // 각 테스트에서 사용할 공통 테스트 데이터
        testUser = new User("피카츄", "electric_mouse@poke.com", 25);
        // 실제로는 DB에서 생성되는 ID를 임의로 설정
        testUser.setId(1L); // 테스트에서만 가능 (실제로는 DB가 생성)
    }

    @Test
    @DisplayName("Mockito 기본 동작 확인")
    void mockitoBasicTest() {
        // Given - Mock 동작 정의 (BDD - Behavior-Driven Development)
        // TDD -> BDD
        // 테스트 주도 개발, 행동 주도 개발 (given.when.then)
        given(userRepository.existsByEmail("test@example.com")).willReturn(false);
        // "test@example.com" -> false
        given(userRepository.save(any(User.class))).willReturn(testUser);
        // save -> testUser

        // When - 실제 메서드 호출
        User newUser = new User("테스트", "test@example.com", 30);
        User savedUser = userService.createUser(newUser);
        // savedUser ? -> newUser??? -> testUser.
        // 겉으로 보기엔 createUser만 호출이 되었지만...
        // 속에는...? 여러 유효성 검증들이 있어요... (repository)

        // Then - 결과 검증
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getName()).isEqualTo("피카츄"); // Mock이 반환한 데이터

        // Mock 호출 검증
        verify(userRepository).existsByEmail("test@example.com"); // 이거 호출 된 적 있어요?
        verify(userRepository).save(any(User.class));
    }

    // 사용자 생성 테스트
    @Test
    @DisplayName("사용자 생성 - 정상 케이스")
    void createUserTest() {
        // Given
        User newUser = new User("아구몬", "nine@digital.com", 9);
        given(userRepository.existsByEmail("nine@digital.com")).willReturn(false); // 너 mocking으로 조회해보면 없어~
        given(userRepository.save(any(User.class)))
                .willReturn(testUser);

        // When
        User result = userService.createUser(newUser);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("피카츄");
        assertThat(result.getEmail()).isEqualTo("electric_mouse@poke.com");

        // Mock 호출 검증
        verify(userRepository).existsByEmail("nine@digital.com");
        verify(userRepository).save(newUser);
    }

    @Test
    @DisplayName("사용자 생성 - 이메일 중복")
    void createUserTest_EmailAlreadyExist() {
        // Given
        User newUser = new User("아구몬", "nine@digital.com", 9);
        given(userRepository.existsByEmail("nine@digital.com")).willReturn(true); // 어? 너??
//        given(userRepository.save(any(User.class)))
//                .willReturn(testUser);

        // When & Then
        assertThatThrownBy(() -> userService.createUser(newUser))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 존재하는 이메일");

        // Mock 호출 검증
        verify(userRepository).existsByEmail("nine@digital.com"); // 중복확인은 하고...
//        verify(userRepository).save(newUser);
        verify(userRepository, never()).save(any(User.class)); // 저장한 적이 없는지
    }

    @Test
    @DisplayName("사용자 생성 - 빈 이름")
    void createUserTest_EmptyName() {
        // Given
        User newUser = new User("", "nine@digital.com", 9);
        given(userRepository.existsByEmail("nine@digital.com")).willReturn(false);

        // When & Then
        assertThatThrownBy(() -> userService.createUser(newUser))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빈 이름");

        // Mock 호출 검증
        verify(userRepository).existsByEmail("nine@digital.com"); // 중복확인은 하고...
        verify(userRepository, never()).save(any(User.class)); // 저장한 적이 없는지
    }

    @Test
    @DisplayName("사용자 생성 - 나이가 음수")
    void createUserTest_NegativeAge() {
        // Given
        User newUser = new User("아구몬", "nine@digital.com", -9);
        given(userRepository.existsByEmail("nine@digital.com")).willReturn(false);

        // When & Then
        assertThatThrownBy(() -> userService.createUser(newUser))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("나이는 양수여야 함");

        // Mock 호출 검증
        verify(userRepository).existsByEmail("nine@digital.com"); // 중복확인은 하고...
        verify(userRepository, never()).save(any(User.class)); // 저장한 적이 없는지
    }

    @Test
    @DisplayName("ID로 사용자 조회")
    void getUserById() {
        // Given
        given(userRepository.findById(1L)
        ).willReturn(Optional.of(testUser));

        // When
        Optional<User> result = userService.findById(1L);

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("피카츄");
        assertThat(result.get().getEmail()).isEqualTo("electric_mouse@poke.com");

        // Mock 호출 검증
        verify(userRepository).findById(1L);
    }

    @Test
    @DisplayName("ID로 사용자 조회 - 사용자 없음")
    void getUserById_NotFound() {
        // Given
        given(userRepository.findById(999L)
        ).willReturn(Optional.empty());

        // When
        Optional<User> result = userService.findById(999L);

        // Then
        assertThat(result).isEmpty();

        // Mock 호출 검증
        verify(userRepository).findById(999L);
    }
}