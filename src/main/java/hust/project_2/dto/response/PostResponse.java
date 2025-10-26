package hust.project_2.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponse {
    String id;
    String content;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    UserResponse author;
    Set<CommentResponse> comments;
    Integer likeCount;
    Boolean likedByCurrentUser;
}