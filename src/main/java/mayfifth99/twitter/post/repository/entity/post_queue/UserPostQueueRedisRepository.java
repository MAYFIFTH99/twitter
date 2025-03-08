package mayfifth99.twitter.post.repository.entity.post_queue;

import java.util.List;
import mayfifth99.twitter.post.repository.entity.post.PostEntity;
import mayfifth99.twitter.post.ui.dto.GetPostContentResponseDto;

public interface UserPostQueueRedisRepository {
    void publishPostToFollowerUsers(PostEntity postEntity, List<Long> followersId); // 나를 팔로우하는 사람들에게 게시글을 전달
    void publishPostListToFollowingUsers(List<PostEntity> postEntityList, Long userId); // 내가 팔로우하는 사람의 게시글을 전달
    void deletePostToUnfollowUser(Long userId, Long targetId); // 언팔로우한 사람의 게시글 삭제
    List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId);



}
