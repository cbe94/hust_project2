package hust.project_2.mapper;

import hust.project_2.dto.request.UserCreationRequest;
import hust.project_2.dto.request.UserUpdateRequest;
import hust.project_2.dto.response.UserResponse;
import hust.project_2.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
