package hust.project_2.service;

import hust.project_2.entity.Post;
import hust.project_2.entity.PostLike;
import hust.project_2.entity.User;
import hust.project_2.repository.PostLikeRepository;
import hust.project_2.repository.PostRepository;
import hust.project_2.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LikeService {
    PostLikeRepository likeRepository;
    PostRepository postRepository;
    UserRepository userRepository;

    private User currentUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = auth.getName();
        return userRepository.findById(userId).orElseThrow();
    }

    public void like(String postId) {
        Post p = postRepository.findById(postId).orElseThrow();
        var cur = currentUser();
        if (likeRepository.findByPostIdAndUserId(postId, cur.getId()).isPresent()) return;
        PostLike pl = PostLike.builder().post(p).user(cur).build();
        likeRepository.save(pl);
    }

    public void unlike(String postId) {
        var cur = currentUser();
        likeRepository.findByPostIdAndUserId(postId, cur.getId()).ifPresent(likeRepository::delete);
    }
}
