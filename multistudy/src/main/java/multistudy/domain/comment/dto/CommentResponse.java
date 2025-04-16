package multistudy.domain.comment.dto;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import multistudy.domain.comment.Comment;

public class CommentResponse {

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommentList {
        List<Comment> comments;
    }


    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommentInfo {

        String username;
        String content;
    }

}
