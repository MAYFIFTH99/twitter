package mayfifth99.twitter.fake;

import mayfifth99.twitter.post.application.CommentService;
import mayfifth99.twitter.post.application.PostService;
import mayfifth99.twitter.post.application.interfaces.CommentRepository;
import mayfifth99.twitter.post.application.interfaces.LikeRepository;
import mayfifth99.twitter.post.application.interfaces.PostRepository;
import mayfifth99.twitter.post.repository.FakeCommentRepository;
import mayfifth99.twitter.post.repository.FakeLikeRepository;
import mayfifth99.twitter.post.repository.FakePostRepository;
import mayfifth99.twitter.user.application.UserRelationService;
import mayfifth99.twitter.user.application.UserService;
import mayfifth99.twitter.user.application.interfaces.UserRelationRepository;
import mayfifth99.twitter.user.application.interfaces.UserRepository;
import mayfifth99.twitter.user.repository.FakeUserRelationRepository;
import mayfifth99.twitter.user.repository.FakeUserRepository;

public class FakeObjectFactory {
    private FakeObjectFactory() {
    }

    private static final UserRepository userRepository = new FakeUserRepository();
    private static final UserRelationRepository userRelationRepository = new FakeUserRelationRepository();
    private static final PostRepository postRepository = new FakePostRepository();
    private static final CommentRepository commentRepository = new FakeCommentRepository();
    private static final LikeRepository likeRepository = new FakeLikeRepository();

    private static final UserService userService = new UserService(userRepository);
    private static final UserRelationService userRelationService = new UserRelationService(userRelationRepository, userService);
    private static final PostService postService = new PostService(postRepository,userService,likeRepository);
    private static final CommentService commentService = new CommentService(commentRepository, userService, postService, likeRepository);


    public static UserService getUserService() {
        return userService;
    }

    public static UserRelationService getUserRelationService() {
        return userRelationService;
    }

    public static PostService getPostService() {
        return postService;
    }

    public static CommentService getCommentService() {
        return commentService;
    }

}
