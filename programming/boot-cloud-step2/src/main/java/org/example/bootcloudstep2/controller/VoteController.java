package org.example.bootcloudstep2.controller;

import lombok.RequiredArgsConstructor;
import org.example.bootcloudstep2.dto.VoteDTO;
import org.example.bootcloudstep2.entity.Vote;
import org.example.bootcloudstep2.repository.VoteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class VoteController {
    private final VoteRepository voteRepository;

    @GetMapping
    public ResponseEntity<List<Vote>> list() {
        return ResponseEntity.ok(voteRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Vote> save(@RequestBody VoteDTO voteDTO) {
        Vote vote = new Vote();
        vote.setName(voteDTO.name());
        vote.setCreatedAt(ZonedDateTime.now(ZoneOffset.UTC));
        vote = voteRepository.save(vote);
        return ResponseEntity.status(201).body(vote);
    }
}