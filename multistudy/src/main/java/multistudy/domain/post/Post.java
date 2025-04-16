package multistudy.domain.post;

import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import multistudy.domain.comment.Comment;
import multistudy.domain.common.BaseEntity;
import multistudy.domain.user.User;

@Entity
@NoArgsConstructor
@Getter
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    private User user;

    private String title;

    private String content;

    private Integer likes;

    @OneToMany(fetch = LAZY, cascade = REMOVE)
    @JoinColumn(name = "comment_id")
    private List<Comment> comment;


    @Builder
    public Post(User user, String title, String content, Integer likes) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.likes = likes;
    }

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
