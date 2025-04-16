package multistudy.domain.post.repository;

import java.util.Optional;
import multistudy.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
