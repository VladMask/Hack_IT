package grsu.by.dto;

import grsu.by.dto.userDto.UserBaseDto;
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
    private UserBaseDto user;
    private Boolean isLeader = false;
}
