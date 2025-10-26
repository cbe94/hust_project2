package hust.project_2.controller;

import hust.project_2.dto.request.comments.CreateCommentRequest;
import hust.project_2.dto.request.post.CreatePostRequest;
import hust.project_2.dto.response.ApiResponse;
import hust.project_2.dto.response.CommentResponse;
import hust.project_2.dto.response.PostResponse;
import hust.project_2.entity.Comment;
import hust.project_2.entity.Post;
import hust.project_2.service.CommentService;
import hust.project_2.service.LikeService;
import hust.project_2.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/posts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PostController {
    PostService postService;
    CommentService commentService;
    LikeService likeService;

    @GetMapping
    public ApiResponse<List<PostResponse>> getAllPosts() {
        return ApiResponse.<List<PostResponse>>builder()
                .result(postService.getPosts())
                .build();
    }

    @PostMapping
    public ApiResponse<Post> create(@RequestBody CreatePostRequest dto){

        return ApiResponse.<Post>builder()
                .result(postService.create(dto))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<Post> update(@PathVariable String id, @RequestBody CreatePostRequest dto){
        return ApiResponse.<Post>builder()
                .result(postService.update(id, dto))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable String id){
        postService.delete(id);
        return ApiResponse.<Void>builder().build();
    }

    @PostMapping("/{id}/comments")
    public ApiResponse<CommentResponse> addComment(@PathVariable String id, @RequestBody CreateCommentRequest payload){
        return ApiResponse.<CommentResponse>builder()
                .result(commentService.add(id, payload))
                .build();
    }

    @DeleteMapping("/comments/{id}")
    public ApiResponse<Void> deleteComment(@PathVariable String id){
        commentService.delete(id);
        return ApiResponse.<Void>builder().build();
    }

    @PostMapping("/{id}/like")
    public ApiResponse<Void> like(@PathVariable String id){
        likeService.like(id);
        return ApiResponse.<Void>builder().build();
    }

    @PostMapping("/{id}/unlike")
    public ApiResponse<Void> unlike(@PathVariable String id){
        likeService.unlike(id);
        return ApiResponse.<Void>builder().build();
    }

}
