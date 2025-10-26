package hust.project_2.repository;

import hust.project_2.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, String> {
    Optional<PostLike> findByPostIdAndUserId(String postId, String userId);
    long countByPostId(String postId);
}
