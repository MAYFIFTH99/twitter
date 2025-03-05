package mayfifth99.twitter.post.application.dto;

public record CreateCommentRequestDto(Long postId, Long userId, String content) {

}
