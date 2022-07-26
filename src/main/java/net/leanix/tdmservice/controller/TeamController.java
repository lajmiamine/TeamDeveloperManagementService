package net.leanix.tdmservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.leanix.tdmservice.domain.Team;
import net.leanix.tdmservice.service.TeamService;
import net.leanix.tdmservice.utils.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/teams")
@Slf4j
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<List<Team>> getAll() {
        log.info("Fetching all teams");
        return ok(teamService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getById(@PathVariable final Long id) {
        log.info("Fetching a team with id {}", id);
        return ok(teamService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Team> create(@RequestBody @Valid final Team team) {
        log.info("Creating a team with id incoming payload {}", team);
        val createdTeam = teamService.create(team);
        val uri = ResponseUtil.getURI(createdTeam.getId());
        return created(uri).body(createdTeam);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> update(@PathVariable final Long id, @RequestBody @Valid final Team team) {
        log.info("Updating a team with id {} with an incoming payload {}", id, team);
        teamService.update(id, team);
        return noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        log.info("Updating a team with id {}", id);
        teamService.delete(id);
        return noContent().build();
    }
}
