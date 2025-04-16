package multistudy.domain.user.controller;


import lombok.RequiredArgsConstructor;
import multistudy.domain.user.dto.UserResponse;
import multistudy.domain.user.service.UserService;
import multistudy.global.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/info")
    public ApiResponse<UserResponse.userInfo> info(
        @RequestParam Long id
    ) {
        return userService.detailInfo(id);
    }
}
