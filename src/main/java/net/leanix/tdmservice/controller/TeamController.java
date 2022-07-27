package net.leanix.tdmservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.leanix.tdmservice.dto.TeamResource;
import net.leanix.tdmservice.mapper.TeamMapper;
import net.leanix.tdmservice.service.TeamService;
import net.leanix.tdmservice.utils.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/teams")
@Slf4j
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<List<TeamResource>> getAll() {
        log.info("Fetching all teams");
        return ok(teamService.getAll().stream().map(TeamMapper::map).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamResource> getById(@PathVariable final Long id) {
        log.info("Fetching a team with id {}", id);
        return ok(TeamMapper.map(teamService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<TeamResource> create(@RequestBody @Valid final TeamResource team) {
        log.info("Creating a team with id incoming payload {}", team);
        val createdTeam = teamService.create(TeamMapper.map(team));
        val uri = ResponseUtil.getURI(createdTeam.getId());
        return created(uri).body(TeamMapper.map(createdTeam));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamResource> update(@PathVariable final Long id, @RequestBody @Valid final TeamResource team) {
        log.info("Updating a team with id {} with an incoming payload {}", id, team);
        teamService.update(id, TeamMapper.map(team));
        return noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        log.info("Updating a team with id {}", id);
        teamService.delete(id);
        return noContent().build();
    }
}
