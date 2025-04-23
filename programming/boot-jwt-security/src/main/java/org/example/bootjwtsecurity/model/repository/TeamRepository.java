package org.example.bootjwtsecurity.model.repository;

import org.example.bootjwtsecurity.model.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Spring
public interface TeamRepository extends JpaRepository<Team, String> {
}