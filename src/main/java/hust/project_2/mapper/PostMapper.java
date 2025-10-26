package hust.project_2.mapper;


import hust.project_2.dto.response.PostResponse;
import hust.project_2.entity.Post;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CommentMapper.class})
public interface PostMapper {

    @Mapping(target = "author", source = "post.author")
    @Mapping(target = "comments", source = "post.comments")
    @Mapping(target = "likeCount", expression = "java(post.getLikes() != null ? post.getLikes().size() : 0)")
    @Mapping(target = "likedByCurrentUser", expression = "java(post.getLikes() != null && post.getLikes().stream().anyMatch(u -> u.getId().equals(currentUserId)))")
    PostResponse toPostResponse(Post post, @Context String currentUserId);

    // --- Helper method for likedByCurrentUser ---
    default boolean isLikedByCurrentUser(Post post, String currentUserId) {
        if (post.getLikes() == null || currentUserId == null) return false;
        return post.getLikes().stream().anyMatch(u -> u.getId().equals(currentUserId));
    }
}