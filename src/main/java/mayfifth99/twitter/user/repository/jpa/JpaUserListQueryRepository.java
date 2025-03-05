package mayfifth99.twitter.user.repository.jpa;

import java.util.List;
import mayfifth99.twitter.user.application.dto.GetUserListResponseDto;
import mayfifth99.twitter.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * JPQL을 사용한 사용자 목록 조회를 위한 Repository
 */
public interface JpaUserListQueryRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT new mayfifth99.twitter.user.application.dto.GetUserListResponseDto(u.name, u.imageUrl) "
            + "FROM UserRelationEntity ur "
            + "JOIN UserEntity u "
            + "ON ur.followerUserId = u.id "
            + "WHERE ur.followingUserId =:userId")
    List<GetUserListResponseDto> getFollowingUserList(Long userId);

    @Query("SELECT new mayfifth99.twitter.user.application.dto.GetUserListResponseDto(u.name, u.imageUrl) "
            + "FROM UserRelationEntity ur "
            + "JOIN UserEntity u "
            + "ON ur.followingUserId = u.id "
            + "WHERE ur.followerUserId =:userId")
    List<GetUserListResponseDto> getFollowerUserList(Long userId);
}
