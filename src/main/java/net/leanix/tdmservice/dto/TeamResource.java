package net.leanix.tdmservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.leanix.tdmservice.domain.Developer;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamResource {

    private Long id;
    @NotEmpty
    private String name;
    private String description;
    private Set<Developer> developers; //Todo
}
