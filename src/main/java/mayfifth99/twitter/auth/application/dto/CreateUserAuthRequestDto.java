package mayfifth99.twitter.auth.application.dto;

import mayfifth99.twitter.auth.domain.UserRole;

public record CreateUserAuthRequestDto(String email, String password, UserRole role, String name, String imageUrl) {

}
