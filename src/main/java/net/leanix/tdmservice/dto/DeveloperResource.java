package net.leanix.tdmservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.leanix.tdmservice.domain.Team;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeveloperResource {

    private Long id;
    private String name;
    private Team team;
}
