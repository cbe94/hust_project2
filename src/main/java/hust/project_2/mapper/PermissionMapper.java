package hust.project_2.mapper;

import hust.project_2.dto.request.PermissionRequest;
import hust.project_2.dto.response.PermissionResponse;
import hust.project_2.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest permissionRequest);
    PermissionResponse toPermissionResponse(Permission permission);
}
