package mayfifth99.twitter.post.application.dto;

import mayfifth99.twitter.post.domain.content.PostPublicationState;

public record CreatePostRequestDto(Long userId, String content, PostPublicationState state) {

}
