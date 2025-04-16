package multistudy.domain.post.dto;

import static java.util.stream.Collectors.toList;

import java.util.List;
import multistudy.domain.post.Post;
import multistudy.domain.post.dto.PostResponse.DetailInfo;
import multistudy.domain.user.User;

public abstract class PostConverter {

    public static Post toEntity(PostRequest.CreatePost postRequest, User user) {

        return Post.builder()
            .title(postRequest.getTitle())
            .content(postRequest.getContent())
            .likes(0)
            .user(user)
            .build();
    }

    public static PostResponse.DetailInfo detailInfo(Post post) {
        return DetailInfo.builder()
            .id(post.getId())
            .title(post.getTitle())
            .content(post.getContent())
            .build();
    }

    public static List<PostResponse.DetailInfo> allPostInfo(
        List<Post> posts
    ) {
        return posts.stream()
            .map(PostConverter::detailInfo)
            .toList();
    }

}
