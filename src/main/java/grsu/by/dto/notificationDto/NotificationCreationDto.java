package grsu.by.dto.notificationDto;

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
public class NotificationCreationDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private Long userId;

    @NotNull
    private Long hackathonId;
}

