package net.leanix.tdmservice.service;

import net.leanix.tdmservice.repository.TeamRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TeamServiceTest {

    @InjectMocks
    TeamService teamService;
    @Mock
    TeamRepository teamRepository;

    @Test
    void should_return_exactly_TWO_teams() {
        // Given
        // When
        // Then
    }
}