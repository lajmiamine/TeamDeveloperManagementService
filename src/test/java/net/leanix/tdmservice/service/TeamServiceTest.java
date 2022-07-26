package net.leanix.tdmservice.service;

import lombok.val;
import net.leanix.tdmservice.domain.Team;
import net.leanix.tdmservice.exception.ResourceNotFoundException;
import net.leanix.tdmservice.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamServiceTest {

    @InjectMocks
    TeamService teamService;
    @Mock
    TeamRepository teamRepository;
    @Captor
    ArgumentCaptor<Team> teamCaptor;

    @Test
    void should_return_exactly_TWO_teams() {
        given(teamRepository.findAll()).willReturn(List.of(firstTeam(), secondTeam()));
        val teams = teamService.getAll();
        verify(teamRepository).findAll();
        assertThat(teams.size()).isEqualTo(2);
    }

    @Test
    void should_return_A_team() {
        given(teamRepository.findById(anyLong())).willReturn(Optional.of(firstTeam()));
        val team = teamService.getById(1L);
        verify(teamRepository).findById(1L);
        assertThat(team).isNotNull();
    }

    @Test
    void should_throw_exception_when_team_not_found() {
        given(teamRepository.findById(anyLong())).willReturn(Optional.empty());
        assertThatThrownBy(() -> teamService.getById(1L)).isExactlyInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void should_save_and_return_a_team() {
        given(teamRepository.save(any(Team.class))).willReturn(firstTeam());
        teamService.create(firstTeam());
        verify(teamRepository).save(teamCaptor.capture());
        assertThat(teamCaptor.getValue()).isEqualTo(firstTeam());
    }

    @Test
    void should_update_a_team() {
        given(teamRepository.findById(anyLong())).willReturn(Optional.of(firstTeam()));
        teamService.update(1L, firstTeam());
        verify(teamRepository).save(any());
    }

    @Test
    void should_throw_an_exception_when_updating_a_team() {
        given(teamRepository.findById(anyLong())).willReturn(Optional.empty());
        assertThatThrownBy(() -> teamService.update(1L, firstTeam()))
                .isExactlyInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void should_throw_an_exception_when_deleting_a_team() {
        given(teamRepository.findById(anyLong())).willReturn(Optional.empty());
        assertThatThrownBy(() -> teamService.delete(1L))
                .isInstanceOf(RuntimeException.class);
    }

    private Team firstTeam() {
        return Team.builder()
                .id(1L)
                .description("Team 1 Desc")
                .name("Team 1")
                .build();
    }

    private Team secondTeam() {
        return Team.builder()
                .id(2L)
                .description("Team 2 Desc")
                .name("Team 2")
                .build();
    }
}