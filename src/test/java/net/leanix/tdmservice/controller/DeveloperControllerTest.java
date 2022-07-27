package net.leanix.tdmservice.controller;

import net.leanix.tdmservice.domain.Developer;
import net.leanix.tdmservice.domain.Team;
import net.leanix.tdmservice.exception.ErrorCodes;
import net.leanix.tdmservice.exception.ResourceNotFoundException;
import net.leanix.tdmservice.service.DeveloperService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeveloperController.class)
class DeveloperControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DeveloperService developerService;

    @Test
    void should_return_TWO_developers() throws Exception {
        when(developerService.getAll()).thenReturn(List.of(firstDeveloper(), secondDeveloper()));
        mockMvc.perform(get("/v1/developers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andReturn();
    }

    @Test
    public void should_return_A_developer() throws Exception {
        when(developerService.getById(1L)).thenReturn(firstDeveloper());
        mockMvc.perform(get("/v1/developers/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(firstDeveloper().getName())));
    }

    @Test
    public void should_return_Bad_Request_when_fetching_A_developer() throws Exception {
        doThrow(new ResourceNotFoundException(ErrorCodes.DEVELOPER_NOT_FOUND)).when(developerService).getById(1L);
        mockMvc.perform(get("/v1/developers/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_delete_a_developer() throws Exception {
        doNothing().when(developerService).delete(any());
        mockMvc.perform(delete("/v1/developers/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    private Developer firstDeveloper() {
        return Developer.builder()
                .id(1L)
                .name("Developer 1")
                .team(firstTeam())
                .build();
    }

    private Developer secondDeveloper() {
        return Developer.builder()
                .id(1L)
                .name("Developer 2")
                .team(firstTeam())
                .build();
    }

    private Team firstTeam() {
        return Team.builder()
                .id(1L)
                .name("Team 1")
                .description("Team 1 desc")
                .build();
    }
}