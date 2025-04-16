package org.example.bootblogplus.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data // Lombok
@Entity // JPA
public class Article {
    // JPA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MYSQL
            Long id;
    @Column(nullable = false) // NotNull - NullSafety
    String title;
    @Column(nullable = false, length = 2000) // byte 2^8
    String content;
    @CreatedDate // Listener.
    // createdAt. default? TIL.
    LocalDateTime createTime = LocalDateTime.now(); // JPA
    //    @Column(nullable = false, length = 2000)
    @Column(nullable = false) // uuid + 확장자만...
            String imageUrl;
}