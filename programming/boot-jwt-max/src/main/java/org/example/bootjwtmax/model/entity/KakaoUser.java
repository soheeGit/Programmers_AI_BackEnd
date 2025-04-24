package org.example.bootjwtmax.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class KakaoUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false, unique = true)
    private String username;
//    @Column(nullable = false)
//    private String email;
    @Column(nullable = false)
    private String name;
}