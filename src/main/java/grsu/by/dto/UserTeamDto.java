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
public class UserTeamDto {

    @NotNull
    private Long userId;
    @NotNull
    private Long teamId;
    private Boolean isLeader = false;
}
