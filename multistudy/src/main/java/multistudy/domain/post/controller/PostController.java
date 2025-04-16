package multistudy.domain.post.controller;


import java.util.List;
import lombok.RequiredArgsConstructor;
import multistudy.domain.post.dto.PostRequest;
import multistudy.domain.post.dto.PostResponse;
import multistudy.domain.post.dto.PostResponse.DetailInfo;
import multistudy.domain.post.service.PostService;
import multistudy.global.response.ApiResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/detail")
    public ApiResponse<PostResponse.DetailInfo> detailInfo(
        @RequestParam Long postId
    ) {
        return postService.getDetailInfo(postId);
    }

    @GetMapping("/all")
    public ApiResponse<List<DetailInfo>> detailInfoList(
    ) {
        return postService.getAllDetailInfo();
    }

    @PostMapping("/create")
    public ApiResponse<Boolean> createPost(
        @RequestParam Long userId,
        @RequestBody PostRequest.CreatePost post
    ){
        boolean post1 = postService.createPost(post,userId);
        return ApiResponse.onSuccess(post1);
    }

    @PostMapping("/update")
    public ApiResponse<Boolean> updatePost(
        @RequestBody PostRequest.UpdatePost post
    ){
        boolean b = postService.updatePost(post);
        return ApiResponse.onSuccess(b);
    }

    @DeleteMapping
    public ApiResponse<Boolean> deletePost(
        @RequestParam Long postId
    ){
        boolean b = postService.deletePost(postId);
        return ApiResponse.onSuccess(b);
    }
}
