package mayfifth99.twitter.user.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import mayfifth99.twitter.common.domain.PositiveIntegerCounter;

@Getter
@EqualsAndHashCode(of = "id") // User는 id로 구분한다.
public class User {
    private final Long id;
    private final UserInfo userInfo; // user의 name 유효성 검증을 VO로 위임한다.
    private final PositiveIntegerCounter followingCounter;
    private final PositiveIntegerCounter followerCounter;

    public User(Long id, UserInfo userInfo) {
        this.id = id;
        this.userInfo = userInfo;
        this.followingCounter = new PositiveIntegerCounter();
        this.followerCounter = new PositiveIntegerCounter();
    }

    public void follow(User targetUser){
        if(this.equals(targetUser)){
            throw new IllegalArgumentException("자기 자신을 팔로우할 수 없습니다.");
        }
        followingCounter.increase();
        targetUser.increaseFollowCount();
    }

    public void unfollow(User targetUser){
        followingCounter.decrease();
        targetUser.decreaseFollowCount();
    }

    private void increaseFollowCount(){
        followerCounter.increase();
    }
    private void decreaseFollowCount(){
        followerCounter.decrease();
    }

    public int getFollowingCount(){
        return followingCounter.getCount();
    }

    public int getFollowerCount(){
        return followerCounter.getCount();
    }
}
