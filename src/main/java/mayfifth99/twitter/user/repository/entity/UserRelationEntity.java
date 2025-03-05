package mayfifth99.twitter.user.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@IdClass(UserRelationId.class)
@Entity
@Table(name = "community_user_relation")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRelationEntity {

    @Id
    private Long followingUserId;

    @Id
    private Long followerUserId;




}
