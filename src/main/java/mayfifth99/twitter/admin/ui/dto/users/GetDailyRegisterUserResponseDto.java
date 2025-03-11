package mayfifth99.twitter.admin.ui.dto.users;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetDailyRegisterUserResponseDto {


    private LocalDate date;
    private Long count;
}
