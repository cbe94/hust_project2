package hust.project_2.service;

import hust.project_2.dto.request.comments.CreateCommentRequest;
import hust.project_2.dto.response.CommentResponse;
import hust.project_2.entity.Comment;
import hust.project_2.entity.Post;
import hust.project_2.entity.User;
import hust.project_2.mapper.CommentMapper;
import hust.project_2.repository.CommentRepository;
import hust.project_2.repository.PostRepository;
import hust.project_2.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentService {
    CommentRepository commentRepository;
    PostRepository postRepository;
    UserRepository userRepository;
    CommentMapper commentMapper;

    private User currentUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = auth.getName();
        return userRepository.findById(userId).orElseThrow();
    }

    public CommentResponse add(String postId, CreateCommentRequest payload) {
        Post post = postRepository.findById(postId).orElseThrow();

        Comment c = Comment.builder()
                .post(post)
                .author(currentUser())
                .content(payload.getContent())
                .createdAt(LocalDateTime.now())
                .build();

        commentRepository.save(c);

        return commentMapper.toCommentResponse(c);
    }

    public void delete(String id) {
        Comment c = commentRepository.findById(id).orElseThrow();
        if (!c.getAuthor().getId().equals(currentUser().getId())) throw new RuntimeException("Forbidden");
        commentRepository.delete(c);
    }
}
