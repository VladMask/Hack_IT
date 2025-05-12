package grsu.by.dto.prizeDto;

import grsu.by.dto.TeamHackathonDto;
import grsu.by.dto.hackathonDto.HackathonShortDto;
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
public class PrizeFullDto {

    private String name;
    private Integer quantity;
    private HackathonShortDto hackathon;
    private TeamHackathonDto teamHackathon;
}

