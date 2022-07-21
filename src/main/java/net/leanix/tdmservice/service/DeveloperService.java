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

    public void update(Long id, Developer developer) {
        developerRepository.findById(id).ifPresentOrElse(it ->developerRepository.save(developer), RuntimeException::new);
    }

    public void delete(Long id) {
        developerRepository.findById(id).ifPresentOrElse(it ->developerRepository.deleteById(id), RuntimeException::new);
    }
}
