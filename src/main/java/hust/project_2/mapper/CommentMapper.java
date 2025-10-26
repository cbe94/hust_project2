package hust.project_2.mapper;

import hust.project_2.dto.response.CommentResponse;
import hust.project_2.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface CommentMapper {
    CommentResponse toCommentResponse(Comment comment);
}
