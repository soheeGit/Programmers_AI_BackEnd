package org.example.bootjwtsecurity.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity // JPA -> ID
@Data // Lombok
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private String manager;
    @Column(nullable = false)
    private String captain;
}