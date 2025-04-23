package org.example.bootjwtsecurity.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.example.bootjwtsecurity.model.dto.TeamRequestDTO;
import org.example.bootjwtsecurity.model.entity.Team;
import org.example.bootjwtsecurity.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/baseball/teams")
@RequiredArgsConstructor
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = "JWT Bearer token"
)
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    @Operation(
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<List<Team>> findAllTeams() {
        List<Team> teamList = teamService.findAllTeams();
        return ResponseEntity.ok(teamList);
    }

    @PostMapping
    @Operation(
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<Team> createTeam(@RequestBody TeamRequestDTO dto) {
        Team team = teamService.saveTeam(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(team);
    }
}