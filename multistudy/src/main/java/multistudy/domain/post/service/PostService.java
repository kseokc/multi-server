package multistudy.domain.post.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import multistudy.domain.comment.dto.CommentConverter;
import multistudy.domain.post.Post;
import multistudy.domain.post.dto.PostConverter;
import multistudy.domain.post.dto.PostRequest;
import multistudy.domain.post.dto.PostResponse;
import multistudy.domain.post.dto.PostResponse.DetailInfo;
import multistudy.domain.post.repository.PostRepository;
import multistudy.domain.user.User;
import multistudy.domain.user.repository.UserRepository;
import multistudy.global.response.ApiResponse;
import multistudy.global.response.exception.GeneralException;
import multistudy.global.response.status.ErrorStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public ApiResponse<PostResponse.DetailInfo> getDetailInfo(Long postId) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new GeneralException(ErrorStatus.BAD_REQUEST));

        DetailInfo detailInfo = PostConverter.detailInfo(post);

        return ApiResponse.onSuccess(detailInfo);
    }

    public ApiResponse<List<PostResponse.DetailInfo>> getAllDetailInfo() {
        List<Post> allPost = postRepository.findAll();

        List<DetailInfo> detailInfos = PostConverter.allPostInfo(allPost);

        return ApiResponse.onSuccess(detailInfos);
    }

    @Transactional
    public boolean createPost(PostRequest.CreatePost post, Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        Post createPost = PostConverter.toEntity(post, user);
        postRepository.save(createPost);
        return true;
    }

    @Transactional
    public boolean updatePost(PostRequest.UpdatePost post) {
        Post updatePost = postRepository.findById(post.getId())
            .orElseThrow(() -> new GeneralException(ErrorStatus.POST_NOT_FOUND));

        updatePost.updatePost(post.getTitle(), post.getContent());
        return true;
    }

    @Transactional
    public boolean deletePost(Long postId) {
        postRepository.deleteById(postId);
        return true;
    }

}
