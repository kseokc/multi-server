package multistudy.domain.user.service;


import lombok.RequiredArgsConstructor;
import multistudy.domain.user.User;
import multistudy.domain.user.dto.UserConverter;
import multistudy.domain.user.dto.UserResponse;
import multistudy.domain.user.dto.UserResponse.userInfo;
import multistudy.domain.user.repository.UserRepository;
import multistudy.global.response.ApiResponse;
import multistudy.global.response.exception.GeneralException;
import multistudy.global.response.status.ErrorStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
    }

    public ApiResponse<UserResponse.userInfo> detailInfo(Long id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new GeneralException(ErrorStatus.BAD_REQUEST));

        userInfo detailInfo = UserConverter.toDetailInfo(user);

        return ApiResponse.onSuccess(detailInfo);
    }
}
