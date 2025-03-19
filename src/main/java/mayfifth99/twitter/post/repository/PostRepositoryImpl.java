package mayfifth99.twitter.post.repository;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.post.application.interfaces.PostRepository;
import mayfifth99.twitter.post.domain.Post;
import mayfifth99.twitter.post.repository.entity.post.PostEntity;
import mayfifth99.twitter.post.repository.entity.post_queue.UserPostQueueCommandRepository;
import mayfifth99.twitter.post.repository.jpa.JpaPostRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository jpaPostRepository;
    private final UserPostQueueCommandRepository userPostQueueCommandRepository;

    /**
     * merge가 무조건 발생해 SELECT 쿼리가 한 번 더 나가는 문제를 해결하기 위해,
     * JPQL을 이용해 PostEntity를 업데이트하는 메서드를 추가했다.
     */
    @Override
    public Post save(Post post) {
        PostEntity postEntity = new PostEntity(post);
        if(postEntity.getId() != null){
            jpaPostRepository.updatePostEntity(postEntity);
            return postEntity.toPost();
        }
        Post savedPost = jpaPostRepository.save(postEntity).toPost();
        userPostQueueCommandRepository.publishPost(postEntity);
        return savedPost;
    }

    @Override
    public Optional<Post> findById(Long postId) {
        return jpaPostRepository.findById(postId).map(PostEntity::toPost);
    }
}
