package org.example.jpaexercise.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.jpaexercise.config.JPAConfig;
import org.example.jpaexercise.model.dto.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
public class IndexController {
    final JPAConfig jpaConfig; // 이게 의존성 주입이 되네;;;

    public IndexController(JPAConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @GetMapping
    public String index(Model model) {
        try (EntityManager entityManager = jpaConfig.getEntityManagerFactory().createEntityManager()) {
            entityManager.getTransaction().begin();
            Student student = new Student();
            student.setName(LocalDateTime.now().toString());
            entityManager.persist(student);
            entityManager.getTransaction().commit();
            // 닫아주는 건 따로 할 필요 없음 -> try-with-resource 구문은 아예 끝에 닫아줌
            List<Student> students = entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
            model.addAttribute("students", students);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }
}