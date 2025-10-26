package hust.project_2.controller;


import hust.project_2.dto.response.ApiResponse;
import hust.project_2.service.FriendService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/friends")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class FriendController {
    FriendService friendService;

    @PostMapping("/{id}")
    public ApiResponse<Void> addFriend(@PathVariable String id){
        friendService.addFriend(id);
        return ApiResponse.<Void>builder().build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> removeFriend(@PathVariable String id){
        friendService.removeFriend(id);
        return ApiResponse.<Void>builder().build();
    }

    @PostMapping("/{id}/block")
    public ApiResponse<Void> block(@PathVariable String id){
        friendService.block(id);
        return ApiResponse.<Void>builder().build();
    }

    @PostMapping("/{id}/unblock")
    public ApiResponse<Void> unblock(@PathVariable String id){
        friendService.unblock(id);
        return ApiResponse.<Void>builder().build();
    }
}
