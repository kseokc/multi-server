package multistudy.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import multistudy.domain.comment.service.CommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

//    @PostMapping("/create")

}
