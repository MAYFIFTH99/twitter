package mayfifth99.twitter.post.application.dto;

public record UpdateCommentRequestDto(Long commentId, Long userId, String content) {

}
