package multistudy.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CommentRequest {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class CommentInfo {

        public String content;
        public Long userId;
    }

}
