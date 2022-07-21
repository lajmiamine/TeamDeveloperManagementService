package net.leanix.tdmservice.service;

import lombok.RequiredArgsConstructor;
import net.leanix.tdmservice.domain.Team;
import net.leanix.tdmservice.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    public Optional<Team> getById(final long id) {
        return teamRepository.findById(id);
    }

    public Team create(final Team team) {
        return teamRepository.save(team);
    }

    public void update(final Long id, final Team team) {
        getById(id).ifPresentOrElse(it -> teamRepository.save(team), RuntimeException::new);
    }

    public void delete(final Long id) {
        getById(id).ifPresentOrElse(it -> teamRepository.deleteById(id), RuntimeException::new);
    }
}
