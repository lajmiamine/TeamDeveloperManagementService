package net.leanix.tdmservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import net.leanix.tdmservice.domain.Developer;
import net.leanix.tdmservice.service.DeveloperService;
import net.leanix.tdmservice.utils.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/developers")
public class DeveloperController {

    private final DeveloperService developerService;

    @GetMapping
    public ResponseEntity<List<Developer>> getAll(){
        return ok(developerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Developer> getById(@PathVariable final Long id){
        return developerService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Developer> create(@RequestBody final Developer developer){
        val createdDeveloper = developerService.create(developer);
        val uri = ResponseUtil.getURI(createdDeveloper.getId());
        return created(uri).body(createdDeveloper);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id, @RequestBody @Valid final Developer developer) {
        developerService.update(id, developer);
        return noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        developerService.delete(id);
        return noContent().build();
    }
}
