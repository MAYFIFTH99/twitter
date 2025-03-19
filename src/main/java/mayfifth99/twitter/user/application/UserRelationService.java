package mayfifth99.twitter.user.application;

import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.user.application.dto.FollowUserRequestDto;
import mayfifth99.twitter.user.application.interfaces.UserRelationRepository;
import mayfifth99.twitter.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserRelationService {

    private final UserRelationRepository userRelationRepository;
    private final UserService userService;

    @Transactional
    public void follow(FollowUserRequestDto dto){
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if(userRelationRepository.isAlreadyFollow(user, targetUser)){
            throw new IllegalArgumentException("이미 팔로우 중입니다.");
        }

        user.follow(targetUser);
        userRelationRepository.save(user, targetUser);
    }

    @Transactional
    public void unfollow(FollowUserRequestDto dto){
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if(!userRelationRepository.isAlreadyFollow(user, targetUser)){
            throw new IllegalArgumentException("팔로우 중이 아닙니다.");
        }

        user.unfollow(targetUser);
        userRelationRepository.delete(user, targetUser);
    }

}
