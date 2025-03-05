package mayfifth99.twitter.post.application;

import mayfifth99.twitter.fake.FakeObjectFactory;
import mayfifth99.twitter.post.application.dto.CreatePostRequestDto;
import mayfifth99.twitter.post.application.dto.LikeRequestDto;
import mayfifth99.twitter.post.domain.Post;
import mayfifth99.twitter.post.domain.content.PostPublicationState;
import mayfifth99.twitter.user.application.UserService;
import mayfifth99.twitter.user.application.dto.CreateUserRequestDto;
import mayfifth99.twitter.user.domain.User;

public class PostApplicationTestTemplate {
    protected final PostService postService = FakeObjectFactory.getPostService();
    protected final UserService userService = FakeObjectFactory.getUserService();

    protected final User user = userService.createUser(new CreateUserRequestDto("user1", ""));
    protected final User otherUser = userService.createUser(new CreateUserRequestDto("user2", ""));

    protected final CreatePostRequestDto postDto = new CreatePostRequestDto(user.getId(),
            "this is test",
            PostPublicationState.PUBLIC);

    protected final Post post = postService.createPost(postDto);

    protected final LikeRequestDto postLikeDto = new LikeRequestDto(post.getId(), otherUser.getId());

}
