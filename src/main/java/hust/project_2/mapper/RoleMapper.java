package hust.project_2.mapper;

import hust.project_2.dto.request.RoleRequest;
import hust.project_2.dto.response.RoleResponse;
import hust.project_2.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest roleRequest);

    RoleResponse toRoleResponse(Role role);
}
