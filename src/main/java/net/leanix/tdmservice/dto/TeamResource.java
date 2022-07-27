package net.leanix.tdmservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.leanix.tdmservice.domain.Developer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamResource {


    private Long id;
    @NotNull
    @NotEmpty
    private String name;
    private String description;
    @JsonIgnore
    private List<Developer> developers;
}
