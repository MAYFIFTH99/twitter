package mayfifth99.twitter.user.application.dto;

/**
 * JPQL을 사용한 사용자 목록 조회를 위한 Response DTO
 * userId가 follow,follower 하고 있는 사용자의 기본 정보 조회
 */
public record GetUserListResponseDto(String name, String imageUrl) {

}
