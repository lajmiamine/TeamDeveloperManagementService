package net.leanix.tdmservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.leanix.tdmservice.dto.TeamResource;
import net.leanix.tdmservice.mapper.TeamMapper;
import net.leanix.tdmservice.service.TeamService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/teams")
@Slf4j //TODO think about spring AOP
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<List<TeamResource>> getAll(){
        val teams = teamService.getAll()
                .stream()
                .map(TeamMapper::map)
                .toList();
        return ok(teams);
    }

    /*@GetMapping("/paginated")
    public ResponseEntity<Page<Team>> getAllPaginated(final Pageable pageable){
        return ok(teamService.getAllPaginated(pageable));
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<TeamResource> getById(@PathVariable final Long id){
        return teamService.getById(id)
                .map(TeamMapper::map)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TeamResource> create(@RequestBody @Valid final TeamResource teamResource) throws Exception {
        if (teamResource.getId() != null) teamResource.setId(null);
        val createdTeam = teamService.create(TeamMapper.map(teamResource));
        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTeam.getId())
                .toUri();
        return created(uri).body(TeamMapper.map(createdTeam));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamResource> update(@PathVariable final Long id, @RequestBody @Valid final TeamResource teamResource) throws ChangeSetPersister.NotFoundException {
        if(teamService.getById(id).isEmpty()){
            throw new ChangeSetPersister.NotFoundException();
        }
        val teamData = TeamMapper.map(teamResource);
        return ok(TeamMapper.map(teamService.update(id, teamData)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable final Long id) throws ChangeSetPersister.NotFoundException {
        teamService.delete(id);
        return ok().body("Team with id "+id+" was successfully deleted");
    }
}
