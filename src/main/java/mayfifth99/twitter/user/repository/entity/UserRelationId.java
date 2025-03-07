package mayfifth99.twitter.user.repository.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserRelationId implements Serializable {

    private Long followingUserId;
    private Long followerUserId;
}
