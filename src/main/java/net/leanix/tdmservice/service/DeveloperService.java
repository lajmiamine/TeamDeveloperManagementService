package net.leanix.tdmservice.service;

import lombok.RequiredArgsConstructor;
import net.leanix.tdmservice.domain.Developer;
import net.leanix.tdmservice.exception.ErrorCodes;
import net.leanix.tdmservice.exception.ResourceNotFoundException;
import net.leanix.tdmservice.repository.DeveloperRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    public List<Developer> getAll() {
        return developerRepository.findAll();
    }

    public Developer getById(final Long id) {
        return developerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.DEVELOPER_NOT_FOUND));
    }

    public Developer create(Developer map) {
        return developerRepository.save(map);
    }

    public void update(Long id, Developer developer) {
        if (nonNull(getById(id))) developerRepository.save(developer);
    }

    public void delete(Long id) {
        if (nonNull(getById(id))) developerRepository.deleteById(id);
    }
}
