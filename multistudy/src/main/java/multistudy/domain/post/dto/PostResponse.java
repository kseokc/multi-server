package multistudy.domain.post.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import multistudy.domain.comment.dto.CommentResponse.CommentList;

public class PostResponse {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetailInfo {

        @NotNull
        public Long id;

        public String title;

        public String content;

    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostInfo {

        public Long id;

        public String title;

        public String content;

        public Integer likes;

        public List<String> comments;
    }

}
