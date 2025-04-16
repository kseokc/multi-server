package multistudy.domain.comment.dto;

import multistudy.domain.comment.Comment;

public abstract class CommentConverter {

    public static Comment toEntity(CommentRequest.CommentInfo commentInfo) {
        return Comment.builder()
//            .user(commentInfo.getUserId())
            .content(commentInfo.getContent())
            .build();
    }

}
