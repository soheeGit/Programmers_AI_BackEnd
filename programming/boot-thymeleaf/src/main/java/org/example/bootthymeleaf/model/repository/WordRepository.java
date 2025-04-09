package org.example.bootthymeleaf.model.repository;

import org.example.bootthymeleaf.model.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Word, String> {   // uuid썼기때문에 String으로 해야함
}
