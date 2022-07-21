package net.leanix.tdmservice.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.leanix.tdmservice.domain.Team;
import org.codehaus.jackson.annotate.JsonIgnore;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeveloperResource {

    private Long id;
    private String name;
    private Team team;
}
