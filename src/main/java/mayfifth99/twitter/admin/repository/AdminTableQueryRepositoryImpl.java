package mayfifth99.twitter.admin.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.admin.ui.dto.posts.GetPostTableRequestDto;
import mayfifth99.twitter.admin.ui.dto.posts.GetPostTableResponseDto;
import mayfifth99.twitter.admin.ui.dto.GetTableListResponse;
import mayfifth99.twitter.admin.ui.dto.users.GetUserTableRequestDto;
import mayfifth99.twitter.admin.ui.dto.users.GetUserTableResponseDto;
import mayfifth99.twitter.admin.ui.query.AdminTableQueryRepository;
import mayfifth99.twitter.auth.repository.entity.QUserAuthEntity;
import mayfifth99.twitter.post.repository.entity.post.QPostEntity;
import mayfifth99.twitter.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminTableQueryRepositoryImpl implements AdminTableQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final QUserAuthEntity userAuthEntity = QUserAuthEntity.userAuthEntity;
    private final QUserEntity userEntity = QUserEntity.userEntity;
    private final QPostEntity postEntity = QPostEntity.postEntity;

    // 50만개의 사용자 데이터가 있을 때, 3초 -> 1초로 성능 개선
    @Override // 이름으로 사용자 목록 조회
    public GetTableListResponse<GetUserTableResponseDto> getUserTableData(
            GetUserTableRequestDto dto) {

        int total = queryFactory
                .select(userEntity.id)
                .from(userEntity)
                .where(likeName(dto.getName()))
                .fetch()
                .size();

        // 커버링 인덱스 적용
        List<Long> ids = queryFactory
                .select(userEntity.id)
                .from(userEntity)
                .where(likeName(dto.getName()))
                .orderBy(userEntity.id.desc())
                .offset(dto.getOffset())
                .limit(dto.getLimit())
                .fetch();

        // 커버링 인덱스를 이용해 찾은 ids 들의 정보만 반환하면 성능 최적화 가능
        List<GetUserTableResponseDto> result = queryFactory
                .select(
                        Projections.fields(
                                GetUserTableResponseDto.class,
                                userEntity.id.as("id"),
                                userEntity.name.as("name"),
                                userAuthEntity.email.as("email"),
                                userAuthEntity.userRole.as("role"),
                                userEntity.regDt.as("createdAt"),
                                userEntity.updDt.as("updatedAt"),
                                userAuthEntity.lastLoginDt.as("lastLoginAt")
                        ))
                                .from(userEntity)
                                .join(userAuthEntity)
                                .on(userEntity.id.eq(userAuthEntity.userId))
                                .where(userEntity.id.in(ids)
                ).orderBy(userEntity.id.desc())
                .fetch();

        return new GetTableListResponse<>(total, result);

        //TODO total 조회 쿼리 성능 개선 필요

        //커버링 인덱스 적용을 위해 해당 쿼리 폐기


//        List<GetUserTableResponseDto> result = queryFactory
//                .select(Projections.fields(
//                        GetUserTableResponseDto.class,
//                        userEntity.id.as("id"),
//                        userEntity.name.as("name"),
//                        userAuthEntity.email.as("email"),
//                        userAuthEntity.userRole.as("role"),
//                        userEntity.regDt.as("createdAt"),
//                        userEntity.updDt.as("updatedAt"),
//                        userAuthEntity.lastLoginDt.as("lastLoginAt")
//                ))
//                .from(userEntity)
//                .join(userAuthEntity)
//                .on(userEntity.id.eq(userAuthEntity.userId))
//                .where(likeName(dto.getName()))
//                .orderBy(userEntity.id.desc())
//                .offset(dto.getOffset())
//                .limit(dto.getLimit())
//                .fetch();
//
//        return new GetTableListResponse<>(total,result);
    }

    @Override
    public GetTableListResponse<GetPostTableResponseDto> getPostTableData(
            GetPostTableRequestDto dto) {

        int total = queryFactory
                .select(postEntity.id)
                .from(postEntity)
                .where(eqPostId(dto.getPostId()))
                .fetch()
                .size();

        List<Long> ids = queryFactory
                .select(postEntity.id)
                .from(postEntity)
                .where(eqPostId(dto.getPostId()))
                .orderBy(postEntity.id.desc())
                .offset(dto.getOffset())
                .limit(dto.getLimit())
                .fetch();


    List<GetPostTableResponseDto> result = queryFactory
                .select(
                        Projections.fields(
                                GetPostTableResponseDto.class,
                                postEntity.id.as("postId"),
                                userEntity.id.as("userId"),
                                userEntity.name.as("userName"),
                                postEntity.content.as("content"),
                                postEntity.regDt.as("createdAt"),
                                postEntity.updDt.as("updatedAt")
                        ))
                .from(postEntity)
                .join(userEntity)
                .on(postEntity.author.id.eq(userEntity.id))
                .where(postEntity.id.in(ids))
                .orderBy(postEntity.id.desc())
                .fetch();

        return new GetTableListResponse<>(total, result);
    }

    private BooleanExpression eqPostId(Long id){
        if(id == null){
            return null;
        }
        return postEntity.id.eq(id);
    }

    private BooleanExpression likeName(String name) {
        if (name == null || name.isBlank()) {
            return null;
        }
        return userEntity.name.like("%" + name + "%");
    }
}
