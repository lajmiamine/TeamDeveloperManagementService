package net.leanix.tdmservice.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import net.leanix.tdmservice.domain.Developer;
import net.leanix.tdmservice.repository.DeveloperRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    public List<Developer> getAll() {
        return developerRepository.findAll();
    }

    public Optional<Developer> getById(final Long id) {return developerRepository.findById(id);}

    public Developer create(Developer map) { return developerRepository.save(map);}

    public Developer update(Long id, Developer developerData) {
        val developer = developerRepository.findById(id).get();
        if(developerData.getName() != null)  developer.setName(developerData.getName());
        if(developerData.getTeam() != null)  developer.setTeam(developerData.getTeam());
        return developerRepository.save(developer);
    }

    public void delete(Long id) throws ChangeSetPersister.NotFoundException {
        if(developerRepository.findById(id).isEmpty()){
            throw new ChangeSetPersister.NotFoundException();
        }
        developerRepository.deleteById(id);
    }
}
