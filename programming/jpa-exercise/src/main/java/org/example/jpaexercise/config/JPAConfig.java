package org.example.jpaexercise.config;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JPAConfig {

    private final EntityManagerFactory entityManagerFactory;

    public JPAConfig() {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        Map<String, String> props = new HashMap<>();
        props.put("javax.persistence.jdbc.url", dotenv.get("DB_URL"));
        props.put("javax.persistence.jdbc.user", dotenv.get("DB_USER"));
        props.put("javax.persistence.jdbc.password", dotenv.get("DB_PASSWORD"));
        props.put("javax.persistence.jdbc.driver", dotenv.get("DB_DRIVER"));
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("MyPersistenceUnit", props);
        } catch (Exception e) {
            // 예외 처리: EntityManagerFactory 생성 실패 시 로그 기록 또는 예외 던지기
            System.err.println("EntityManagerFactory 생성 실패: " + e.getMessage());
            throw new RuntimeException("EntityManagerFactory 생성 실패", e);
        }
    }

    public EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            throw new IllegalStateException("EntityManagerFactory가 초기화되지 않았습니다.");
        }
        return entityManagerFactory;
    }
}