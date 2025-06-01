package org.example.bootteststep2.service;

import lombok.RequiredArgsConstructor;
import org.example.bootteststep2.entity.User;
import org.example.bootteststep2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // lombok 때문에 생성자 주입 생략

    // 사용자 생성
    public User createUser(User user) {
        // 유효성 검증 (이메일, 이름, 나이)
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일");
        }
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("빈 이름");
        }
        if (user.getAge() < 0) {
            throw new IllegalArgumentException("나이는 양수여야 함");
        }
        return userRepository.save(user);
    }

    // ID 사용자 조회
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // 이메일로 사용자 조회
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // 모든 사용자 조회
    public List<User> findAll() {
        return userRepository.findAll();
    }
}