package net.leanix.tdmservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.leanix.tdmservice.domain.Team;
import net.leanix.tdmservice.mapper.TeamMapper;
import net.leanix.tdmservice.service.TeamService;
import net.leanix.tdmservice.utils.ResponseUtil;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/teams")
@Slf4j //TODO think about spring AOP
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<List<Team>> getAll(){
        return ok(teamService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getById(@PathVariable final Long id){
        return teamService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Team> create(@RequestBody @Valid final Team team) {
        val createdTeam = teamService.create(team);
        val uri = ResponseUtil.getURI(createdTeam.getId());
        return created(uri).body(createdTeam);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> update(@PathVariable final Long id, @RequestBody @Valid final Team team) {
        teamService.update(id, team);
        return noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        teamService.delete(id);
        return noContent().build();
    }
}
