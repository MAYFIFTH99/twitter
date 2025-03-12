package mayfifth99.twitter.user.application;

import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.user.application.dto.CreateUserRequestDto;
import mayfifth99.twitter.user.application.dto.GetUserResponseDto;
import mayfifth99.twitter.user.application.interfaces.UserRepository;
import mayfifth99.twitter.user.domain.User;
import mayfifth99.twitter.user.domain.UserInfo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(CreateUserRequestDto dto) {
        UserInfo userInfo = new UserInfo(dto.name(), dto.profileImageUrl());
        User user = new User(userInfo);
        return userRepository.save(user);
    }

    public User getUser(Long userId){
        return userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
    }

    public GetUserResponseDto getUserProfile(Long userId){
        User user = getUser(userId);
        return new GetUserResponseDto(user);
    }

}
