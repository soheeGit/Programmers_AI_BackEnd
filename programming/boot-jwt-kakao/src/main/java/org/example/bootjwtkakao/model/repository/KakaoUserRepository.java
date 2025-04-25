package org.example.bootjwtkakao.model.repository;

import org.example.bootjwtkakao.model.entity.KakaoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KakaoUserRepository extends JpaRepository<KakaoUser, Long> {
    Optional<KakaoUser> findByUsername(String username);
}