package grsu.by.dto.notificationDto;

import grsu.by.dto.hackathonDto.HackathonShortDto;
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
public class NotificationFullDto {

    private String title;
    private String content;
    private UserBaseDto user;
    private HackathonShortDto hackathon;
}

