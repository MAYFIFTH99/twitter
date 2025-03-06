package mayfifth99.twitter.user.ui.controller;

import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.common.ui.Response;
import mayfifth99.twitter.user.application.UserService;
import mayfifth99.twitter.user.application.dto.CreateUserRequestDto;
import mayfifth99.twitter.user.application.dto.GetUserResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController  implements UserControllerSpec{

    private final UserService userService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public Response<Long> createUser(@RequestBody CreateUserRequestDto dto){
        return Response.ok(userService.createUser(dto).getId());
    }

    @GetMapping("/{userId}")
    public Response<GetUserResponseDto> getUserProfile(@PathVariable Long userId){
        return Response.ok(userService.getUserProfile(userId));
    }

}
