package grsu.by.dto;

import jakarta.validation.constraints.NotBlank;
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
public class PrizeDto {

    @NotBlank
    private String name;

    @NotNull
    private Integer quantity;

    @NotNull
    private Long hackathonId;

    private Long teamHackathonId;
}

