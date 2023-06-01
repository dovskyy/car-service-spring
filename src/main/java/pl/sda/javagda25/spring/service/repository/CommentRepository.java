package pl.sda.javagda25.spring.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.javagda25.spring.service.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    boolean existsByContent(String content);
}
