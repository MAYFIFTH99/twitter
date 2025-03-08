package mayfifth99.twitter.post.repository.entity.post_queue;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.post.repository.entity.like.LikeTarget;
import mayfifth99.twitter.post.repository.entity.like.QLikeEntity;
import mayfifth99.twitter.post.repository.entity.post.QPostEntity;
import mayfifth99.twitter.post.repository.entity.post.QUserPostQueueEntity;
import mayfifth99.twitter.post.ui.dto.GetPostContentResponseDto;
import mayfifth99.twitter.user.repository.entity.QUserEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile({"!test"})
@RequiredArgsConstructor
public class UserPostQueueQueryRepositoryImpl implements UserPostQueueQueryRepository {

    private final JPAQueryFactory queryFactory;

    private final QUserPostQueueEntity userPostQueue = QUserPostQueueEntity.userPostQueueEntity;
    private final QUserEntity user = QUserEntity.userEntity;
    private final QPostEntity post = QPostEntity.postEntity;
    private final QLikeEntity like = QLikeEntity.likeEntity;


    @Override
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId) {
        //userId의 PostQueue를 가져와서 반환해주는 메서드
        return queryFactory
                .select(Projections.fields(
                        GetPostContentResponseDto.class,
                        post.id.as("id"),
                        post.content.as("content"),
                        user.name.as("userName"),
                        user.imageUrl.as("imageUrl"),
                        post.likeCount.as("likeCount"),
                        post.commentCount.as("commentCount"),
                        post.regDt.eq(post.updDt).as("isNew"),
                        post.regDt.as("regDt"),
                        post.updDt.as("updDt"),
                        like.isNotNull().as("isLikedByMe")
                ))
                .from(userPostQueue)
                .join(post)
                    .on(userPostQueue.postId.eq(post.id))
                .join(user)
                .on(userPostQueue.userId.eq(user.id))
                .leftJoin(like)
                .on(hasLike(userId))
                .where(userPostQueue.userId.eq(userId),
                        hasLastData(lastPostId))
                .orderBy(userPostQueue.postId.desc())
                .limit(20)
                .fetch();

    }

    private BooleanExpression hasLike(Long userId) {
        if (userId == null) {
            return null;
        }

        return post.id
                .eq(like.id.targetId)
                .and(like.id.target.eq(LikeTarget.POST))
                .and(like.id.userId.eq(userId));
    }

    private BooleanExpression hasLastData(Long lastPostId) {
        if(lastPostId == null){
            return null;
        }

        return userPostQueue.postId.lt(lastPostId);
    }

}
