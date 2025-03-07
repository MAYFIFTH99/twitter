package mayfifth99.twitter.user.application.dto;

import mayfifth99.twitter.user.domain.User;

public record GetUserResponseDto
        (Long id,
         String name,
         String profileImageUrl,
         int followingCount,
         int followerCount)
{

    public GetUserResponseDto(User user){
        this(user.getId(), user.getUserInfo().getName(), user.getUserInfo().getImageUrl(), user.getFollowingCount(), user.getFollowerCount());
    }

}
