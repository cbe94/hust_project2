package hust.project_2.service;

import hust.project_2.dto.request.post.CreatePostRequest;
import hust.project_2.dto.response.PostResponse;
import hust.project_2.entity.Post;
import hust.project_2.entity.User;
import hust.project_2.mapper.PostMapper;
import hust.project_2.repository.PostRepository;
import hust.project_2.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostService {
    PostRepository postRepository;
    UserRepository userRepository;

    PostMapper postMapper;

    private User currentUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = auth.getName();
        return userRepository.findById(userId).orElseThrow();
    }

    public List<PostResponse> getPosts() {
        User u = currentUser();

        Set<String> relatedIds = new HashSet<>();
        relatedIds.add(u.getId());
        relatedIds.addAll(u.getFriends().stream().map(User::getId).collect(Collectors.toSet()));

        return postRepository.findAllByAuthorIdInOrderByCreatedAtDesc(relatedIds)
                .stream()
                .map(post -> postMapper.toPostResponse(post, u.getId()))
                .toList();
    }

    public Post create(CreatePostRequest request) {
        User u = currentUser();
        Post p = Post.builder()
                .author(u)
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .build();
        return postRepository.save(p);
    }

    public Post update(String id, CreatePostRequest dto) {
        Post p = postRepository.findById(id).orElseThrow();
        if (!p.getAuthor().getId().equals(currentUser().getId())) throw new RuntimeException("Forbidden");
        p.setContent(dto.getContent());
        p.setUpdatedAt(LocalDateTime.now());
        return postRepository.save(p);
    }

    public void delete(String id) {
        Post p = postRepository.findById(id).orElseThrow();
        if (!p.getAuthor().getId().equals(currentUser().getId())) throw new RuntimeException("Forbidden");
        postRepository.delete(p);
    }

}
