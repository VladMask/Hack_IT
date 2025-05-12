package grsu.by.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamHackathonDto {
    @NotNull
    private Long teamId;
    @NotNull
    private Long hackathonId;

    private Boolean isWinner = false;

    private Integer place;
}



