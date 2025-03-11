package mayfifth99.twitter.admin.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.admin.ui.dto.users.GetDailyRegisterUserResponseDto;
import mayfifth99.twitter.admin.ui.query.UserStatsQueryRepository;
import mayfifth99.twitter.common.utils.TimeCalculator;
import mayfifth99.twitter.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserStatsQueryRepositoryImpl implements UserStatsQueryRepository {

    private final JPAQueryFactory queryFactory;
    private static final QUserEntity user = QUserEntity.userEntity;

    @Override
    public List<GetDailyRegisterUserResponseDto> getDailyRegisterUserStats(int beforeDays) {
        return queryFactory
                .select(Projections.fields(
                        GetDailyRegisterUserResponseDto.class,
                        user.regDate.as("date"),
                        user.count().as("count")
                ))
                .from(user)
                .where(user.regDate.after(TimeCalculator.getDateDaysAgo(beforeDays)))
                .groupBy(user.regDate)
                .orderBy(user.regDate.asc())
                .fetch();
    }
}
