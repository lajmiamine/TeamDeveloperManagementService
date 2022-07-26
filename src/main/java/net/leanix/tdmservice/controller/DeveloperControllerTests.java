package net.leanix.tdmservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import net.leanix.tdmservice.domain.Team;
import net.leanix.tdmservice.exception.ErrorCodes;
import net.leanix.tdmservice.exception.ResourceNotFoundException;
import net.leanix.tdmservice.service.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeamController.class)
class DeveloperControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TeamService teamService;
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();


    @Test
    public void should_return_ALL_team() throws Exception {
        when(teamService.getAll()).thenReturn(List.of(firstTeam(),secondTeam()));
        mockMvc.perform(get("/v1/teams")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(2)))
                        .andReturn();
    }

    @Test
    public void should_return_A_team() throws Exception {
        when(teamService.getById(1L)).thenReturn(firstTeam());
        mockMvc.perform(get("/v1/teams/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(firstTeam().getName())));
    }

    @Test
    public void should_return_Bad_Request_when_fetching_A_team() throws Exception {
        doThrow(new ResourceNotFoundException(ErrorCodes.TEAM_NOT_FOUND)).when(teamService).getById(1L);
        mockMvc.perform(get("/v1/teams/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_create_A_team() throws Exception {
        String team = ow.writeValueAsString(firstTeam());
        when(teamService.create(any())).thenReturn(firstTeam());
        mockMvc.perform(post("/v1/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(team))
                        .andExpect(status().isCreated());
    }

    @Test
    public void should_return_bad_request_when_creating_a_team() throws Exception {
        String team = ow.writeValueAsString(teamWithoutName());
        when(teamService.create(any())).thenReturn(firstTeam());
        mockMvc.perform(post("/v1/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(team))
                        .andExpect(status().isBadRequest());
    }

    @Test
    public void should_update_a_team() throws Exception {
        String team = ow.writeValueAsString(firstTeam());
        doNothing().when(teamService).update(any(),any());
        mockMvc.perform(put("/v1/teams/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(team))
                .andExpect(status().isNoContent());
    }

    @Test
    public void should_delete_a_team() throws Exception {
        doNothing().when(teamService).update(any(),any());
        mockMvc.perform(delete("/v1/teams/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    private Team firstTeam(){
        return Team.builder()
                .id(1L)
                .name("Team 1")
                .description("Team 1 desc")
                .build();
    }

    private Team secondTeam(){
        return Team.builder()
                .id(1L)
                .name("Team 2")
                .description("Team 2 desc")
                .build();
    }

    private Team teamWithoutName(){
        return Team.builder()
                .id(1L)
                .description("Team 1 desc")
                .build();
    }

}
