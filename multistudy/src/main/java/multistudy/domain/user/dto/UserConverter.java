package multistudy.domain.user.dto;

import multistudy.domain.user.User;

public class UserConverter {

    public static UserResponse.userInfo toDetailInfo(
        User user
    ) {
        return UserResponse.userInfo.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .build();
    }

}
