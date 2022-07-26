package net.leanix.tdmservice.service;

import lombok.val;
import net.leanix.tdmservice.domain.Developer;
import net.leanix.tdmservice.domain.Team;
import net.leanix.tdmservice.exception.ResourceNotFoundException;
import net.leanix.tdmservice.repository.DeveloperRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeveloperServiceTest {

    @InjectMocks
    DeveloperService developerService;
    @Mock
    DeveloperRepository developerRepository;
    @Captor
    ArgumentCaptor<Developer> developerCaptor;

    @Test
    void should_return_TWO_developers(){
        given(developerRepository.findAll()).willReturn(List.of(firstDeveloper(),secondDeveloper()));
        val developers = developerService.getAll();
        verify(developerRepository).findAll();
        assertThat(developers.size()).isEqualTo(2);
    }

    @Test
    void should_return_A_developers(){
        given(developerRepository.findById(anyLong())).willReturn(Optional.of(firstDeveloper()));
        val developer = developerService.getById(1L);
        verify(developerRepository).findById(1L);
        assertThat(developer).isNotNull();
    }

    @Test
    void should_throw_exception_when_developer_not_found(){
        given(developerRepository.findById(anyLong())).willReturn(Optional.empty());
        assertThatThrownBy(()-> developerService.getById(1L)).isExactlyInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void should_save_and_return_a_developer() {
        given(developerRepository.save(any(Developer.class))).willReturn(firstDeveloper());
        developerService.create(firstDeveloper());
        verify(developerRepository).save(developerCaptor.capture());
        assertThat(developerCaptor.getValue()).isEqualTo(firstDeveloper());
    }

    @Test
    void should_update_a_developer() {
        given(developerRepository.findById(anyLong())).willReturn(Optional.of(firstDeveloper()));
        developerService.update(1L, firstDeveloper());
        verify(developerRepository).save(any());
    }

    @Test
    void should_throw_an_exception_when_updating_a_developer() {
        given(developerRepository.findById(anyLong())).willReturn(Optional.empty());
        assertThatThrownBy(() -> developerService.update(1L, firstDeveloper()))
                .isExactlyInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void should_throw_an_exception_when_deleting_a_team() {
        given(developerRepository.findById(anyLong())).willReturn(Optional.empty());
        assertThatThrownBy(() -> developerService.delete(1L))
                .isInstanceOf(RuntimeException.class);
    }


    private Developer firstDeveloper() {
        val team = Team.builder()
                .id(1L)
                .description("Team 1 Desc")
                .name("Team 1")
                .build();
        return Developer.builder()
                .id(1L)
                .name("Mohamed Lajmi")
                .team(team)
                .build();
    }

    private Developer secondDeveloper() {
        val team = Team.builder()
                .id(1L)
                .description("Team 2 Desc")
                .name("Team 2")
                .build();
        return Developer.builder()
                .id(1L)
                .name("Amin Lajmi")
                .team(team)
                .build();
    }

}
