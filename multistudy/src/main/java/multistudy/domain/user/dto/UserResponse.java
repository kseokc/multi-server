package multistudy.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

public class UserResponse {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class userInfo {

        public String username;
        public String password;
    }

}
