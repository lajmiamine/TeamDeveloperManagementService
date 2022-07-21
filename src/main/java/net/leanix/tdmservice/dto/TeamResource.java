package net.leanix.tdmservice.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.leanix.tdmservice.domain.Developer;
import org.codehaus.jackson.annotate.JsonIgnore;

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
    @JsonIgnore
    private Set<Developer> developers;
}
