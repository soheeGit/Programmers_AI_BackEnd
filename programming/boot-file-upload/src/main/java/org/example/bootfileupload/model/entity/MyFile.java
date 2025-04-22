package org.example.bootfileupload.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Entity
@Data
public class MyFile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String uuid;
    @Column(nullable = false)
    String filename;
    @CreationTimestamp
    LocalDateTime createdAt = ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime();
}