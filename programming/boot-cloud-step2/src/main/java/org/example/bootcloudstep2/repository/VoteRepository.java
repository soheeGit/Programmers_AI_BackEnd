package org.example.bootcloudstep2.repository;

import org.example.bootcloudstep2.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}