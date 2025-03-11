package mayfifth99.twitter.admin.ui.dto.users;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mayfifth99.twitter.auth.domain.UserRole;
import mayfifth99.twitter.common.utils.TimeCalculator;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class GetUserTableResponseDto {

    @Getter
    private Long id;
    @Getter
    private String email;
    @Getter
    private String name;
    @Getter
    private UserRole role;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoginAt;

    public String getCreatedAt(){
        return TimeCalculator.getFormattedDate(createdAt);
    }

    public String getUpdatedAt(){
        return TimeCalculator.getFormattedDate(updatedAt);
    }

    public String getLastLoginAt(){
        return TimeCalculator.getFormattedDate(lastLoginAt);
    }

}
