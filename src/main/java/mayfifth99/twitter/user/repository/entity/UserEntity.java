package mayfifth99.twitter.user.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mayfifth99.twitter.common.domain.PositiveIntegerCounter;
import mayfifth99.twitter.common.repository.entity.TimeBaseEntity;
import mayfifth99.twitter.user.domain.User;
import mayfifth99.twitter.user.domain.UserInfo;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DynamicUpdate
@Table(name = "community_user",
indexes = @Index(name = "idx_community_reg_date", columnList = "daily_register"))
public class UserEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String imageUrl;
    private int followingCount;
    private int followerCount;

    @CreatedDate
    @Column(name = "daily_register", updatable = false)
    private LocalDate regDate;

    public UserEntity(User user){
        this.id = user.getId();
        this.name = user.getUserInfo().getName();
        this.imageUrl = user.getUserInfo().getImageUrl();
        this.followingCount = user.getFollowingCount();
        this.followerCount = user.getFollowerCount();
    }

    public User toUser(){
        return User.builder()
                .id(id)
                .userInfo(new UserInfo(name, imageUrl))
                .followingCounter(new PositiveIntegerCounter())
                .followerCounter(new PositiveIntegerCounter())
                .build();
    }
}
