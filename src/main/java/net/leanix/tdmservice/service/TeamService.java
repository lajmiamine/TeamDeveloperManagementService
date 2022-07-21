package net.leanix.tdmservice.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import net.leanix.tdmservice.domain.Team;
import net.leanix.tdmservice.repository.TeamRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Team> getAllPaginated(final Pageable pageable) {
        return teamRepository.findAll(pageable);
    }

    public Team create(final Team team) { return teamRepository.save(team); }

    public Team update(final Long id, final Team teamData) {
        val team = teamRepository.findById(id).get();
        if(teamData.getName() != null)  team.setName(teamData.getName());
        if(teamData.getDescription() != null)  team.setDescription(teamData.getDescription());
        if(teamData.getDevelopers() != null)  team.setDevelopers(teamData.getDevelopers());
        return teamRepository.save(team);
    }

    public void delete(Long id) throws ChangeSetPersister.NotFoundException {
        if(teamRepository.findById(id).isEmpty()){
            throw new ChangeSetPersister.NotFoundException();
        }
        teamRepository.deleteById(id);
    }
}
