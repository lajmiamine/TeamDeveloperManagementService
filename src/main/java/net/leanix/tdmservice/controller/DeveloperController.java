package net.leanix.tdmservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.leanix.tdmservice.domain.Developer;
import net.leanix.tdmservice.service.DeveloperService;
import net.leanix.tdmservice.utils.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/developers")
@Slf4j
public class DeveloperController {

    private final DeveloperService developerService;

    @GetMapping
    public ResponseEntity<List<Developer>> getAll() {
        log.info("Fetching all developers");
        return ok(developerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Developer> getById(@PathVariable final Long id) {
        log.info("Fetching a developer with id {}", id);
        return ok(developerService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Developer> create(@RequestBody @Valid final Developer developer) {
        log.info("Creating a developer with id incoming payload {}", developer);
        val createdDeveloper = developerService.create(developer);
        val uri = ResponseUtil.getURI(createdDeveloper.getId());
        return created(uri).body(createdDeveloper);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id, @RequestBody @Valid final Developer developer) {
        log.info("Updating a developer with id {} with an incoming payload {}", id, developer);
        developerService.update(id, developer);
        return noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        log.info("Updating a developer with id {}", id);
        developerService.delete(id);
        return noContent().build();
    }
}
