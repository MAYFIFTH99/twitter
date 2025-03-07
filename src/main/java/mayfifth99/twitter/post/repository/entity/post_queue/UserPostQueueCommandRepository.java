package mayfifth99.twitter.post.repository.entity.post_queue;

import mayfifth99.twitter.post.repository.entity.post.PostEntity;
// 서비스 계층에 노출되면 안되기 때문에 Repository 레이어에 패키지 위치를 지정
public interface UserPostQueueCommandRepository {


    // 글을 작성했을 때 나를 팔로우하는 유저에게 글을 넣어주는 메서드
    void publishPost(PostEntity postEntity);

    // 내가 팔로우했을 때 그 사람의 글을 내 피드에 넣어주는 메서드
    void saveFollowPost(Long userId, Long targetId);

    // 언팔로우했을 때 그 사람의 글을 내 피드에서 삭제하는 메서드
    void deleteUnfollowPost(Long userId, Long targetId);
}
