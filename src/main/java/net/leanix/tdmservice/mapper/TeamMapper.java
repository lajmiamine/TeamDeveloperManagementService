package net.leanix.tdmservice.mapper;

import net.leanix.tdmservice.domain.Team;
import net.leanix.tdmservice.dto.TeamResource;

public final class TeamMapper {
    private TeamMapper(){}
    public static Team map(final TeamResource teamResource){
        return Team.builder()
                //.id(teamResource.getId()) TODO should only be set by JPA
                .description(teamResource.getDescription())
                .name(teamResource.getName())
                .developers(teamResource.getDevelopers())
                .build();
    }
    public static TeamResource map(final Team team){
        return TeamResource.builder()
                .id(team.getId())
                .description(team.getDescription())
                .name(team.getName())
                .developers(team.getDevelopers())
                .build();
    }
}
