package net.leanix.tdmservice.dto;

import lombok.*;
import net.leanix.tdmservice.domain.Team;
import org.codehaus.jackson.annotate.JsonIgnore;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeveloperResource {

    private Long id;
    private String name;
    private Team team;
}
