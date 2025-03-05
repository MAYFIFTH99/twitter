package mayfifth99.twitter.post.application.dto;

import mayfifth99.twitter.post.domain.content.PostPublicationState;

public record UpdatePostRequestDto(Long postId, Long userId, String content, PostPublicationState state) {

}
