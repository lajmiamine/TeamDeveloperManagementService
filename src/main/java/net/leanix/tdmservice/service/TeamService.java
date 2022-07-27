package net.leanix.tdmservice.service;

import lombok.RequiredArgsConstructor;
import net.leanix.tdmservice.domain.Team;
import net.leanix.tdmservice.exception.ErrorCodes;
import net.leanix.tdmservice.exception.ResourceNotFoundException;
import net.leanix.tdmservice.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    public Team getById(final long id) {
        return teamRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.TEAM_NOT_FOUND));
    }

    public Team create(final Team team) {
        return teamRepository.save(team);
    }

    public void update(final Long id, final Team team) {
        Team teamToBeUpdate = getById(id);
        if (nonNull(teamToBeUpdate)) {
            if (nonNull(team.getName())) teamToBeUpdate.setName(team.getName());
            if (nonNull(team.getDescription())) teamToBeUpdate.setDescription(team.getDescription());
            if (nonNull(team.getDevelopers())) teamToBeUpdate.setDevelopers(team.getDevelopers());
            teamRepository.save(teamToBeUpdate);
        } else throw new ResourceNotFoundException(ErrorCodes.TEAM_NOT_FOUND);
    }

    public void delete(final Long id) {
        if (nonNull(getById(id))) teamRepository.deleteById(id);
        else throw new ResourceNotFoundException(ErrorCodes.TEAM_NOT_FOUND);
    }
}
