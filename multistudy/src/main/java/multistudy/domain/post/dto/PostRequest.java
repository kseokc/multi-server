package multistudy.domain.post.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import multistudy.domain.comment.dto.CommentRequest;

public class PostRequest {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class CreatePost {

        @NotNull
        private String title;
        @NotNull
        private String content;

    }


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdatePost {

        private Long id;
        private String title;
        private String content;

    }

}
