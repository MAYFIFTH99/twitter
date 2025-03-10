package mayfifth99.twitter.admin.ui.query;

import java.util.List;
import mayfifth99.twitter.admin.ui.dto.GetDailyRegisterUserResponseDto;

public interface UserStatsQueryRepository {

    List<GetDailyRegisterUserResponseDto> getDailyRegisterUserStats(int beforeDays);

}
