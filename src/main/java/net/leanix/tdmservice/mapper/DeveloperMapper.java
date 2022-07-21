package net.leanix.tdmservice.mapper;

import net.leanix.tdmservice.domain.Developer;
import net.leanix.tdmservice.dto.DeveloperResource;

public class DeveloperMapper {
    public static DeveloperResource map(Developer developer) {
        return DeveloperResource.builder()
                .id(developer.getId())
                .name(developer.getName())
                .team(developer.getTeam())
                .build();
    }
    public static Developer map(DeveloperResource developerResource) {
        return Developer.builder()
                .id(developerResource.getId())
                .name(developerResource.getName())
                .team(developerResource.getTeam())
                .build();
    }
}
