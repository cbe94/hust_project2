package hust.project_2.repository;

import hust.project_2.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
    List<Post> findAllByAuthorIdInOrderByCreatedAtDesc(Set<String> relatedUserIds);
}
