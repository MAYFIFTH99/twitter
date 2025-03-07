package mayfifth99.twitter.post.repository.jpa;

import mayfifth99.twitter.post.repository.entity.like.LikeEntity;
import mayfifth99.twitter.post.repository.entity.like.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, LikeId> {

}
