package net.leanix.tdmservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import net.leanix.tdmservice.domain.Developer;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"developers"})
public class TeamResource {

    private Long id;
    @NotEmpty
    private String name;
    private String description;
    private Set<Developer> developers;
}
