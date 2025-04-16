package multistudy.domain.comment.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import multistudy.domain.comment.Comment;
import multistudy.domain.comment.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;

//    public List<Comment> finAllByPostId(Long postId) {
//        return
//    }

}
