package net.leanix.tdmservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import net.leanix.tdmservice.dto.DeveloperResource;
import net.leanix.tdmservice.mapper.DeveloperMapper;
import net.leanix.tdmservice.service.DeveloperService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/developers")
public class DeveloperController {

    private final DeveloperService developerService;

    @GetMapping
    public ResponseEntity<List<DeveloperResource>> getAll(){
        val developers = developerService.getAll()
                .stream()
                .map(DeveloperMapper::map)
                .toList();
        return ok(developers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeveloperResource> getById(@PathVariable final Long id){
        return developerService.getById(id)
                .map(DeveloperMapper::map)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DeveloperResource> create(@RequestBody final DeveloperResource developerResource){
        //if (developerResource.getId() != null) developerResource.setId(null); TODO is this managed by JPA?
        val createdDeveloper = developerService.create(DeveloperMapper.map(developerResource));
        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdDeveloper.getId())
                .toUri();
        return created(uri).body(DeveloperMapper.map(createdDeveloper));
    }



    @PutMapping("/{id}")
    public ResponseEntity<DeveloperResource> update(@PathVariable final Long id, @RequestBody @Valid final DeveloperResource developerResource) throws ChangeSetPersister.NotFoundException {
        if(developerService.getById(id).isEmpty()){
            throw new ChangeSetPersister.NotFoundException();
        }
        val developerData = DeveloperMapper.map(developerResource);
        return ok(DeveloperMapper.map(developerService.update(id, developerData)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable final Long id) throws ChangeSetPersister.NotFoundException {
        developerService.delete(id);
        return ok().body("Developer with id "+id+" was successfully deleted");
    }
}
