package mayfifth99.twitter.user.repository.jpa;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.user.application.dto.GetUserListResponseDto;
import mayfifth99.twitter.user.repository.entity.QUserEntity;
import mayfifth99.twitter.user.repository.entity.QUserRelationEntity;
import org.springframework.stereotype.Repository;

/**
 * 복잡한 사용자 목록 조회를 위한 Repository
 * By Querydsl
 */
@Repository
@RequiredArgsConstructor
public class JpaUserListPagingQueryRepository {

    private final JPAQueryFactory queryFactory;
    private static final QUserEntity user = QUserEntity.userEntity;
    private static final QUserRelationEntity relation = QUserRelationEntity.userRelationEntity;

    public List<GetUserListResponseDto> getFollowerUserPagingList(Long userId, Long lastFollowId){
        return queryFactory
                .select(Projections.fields(GetUserListResponseDto.class,
                        user.name, user.imageUrl))
                .from(relation)
                .join(user).on(relation.followingUserId.eq(user.id))
                .where(relation.followerUserId.eq(userId),
                        hasLastData(lastFollowId))
                .orderBy(user.id.desc())
                .limit(20)
                .fetch();
    }

    private BooleanExpression hasLastData(Long lastFollowId){
        if(lastFollowId == null){
            return null;
        }
        return user.id.lt(lastFollowId);
    }


}
