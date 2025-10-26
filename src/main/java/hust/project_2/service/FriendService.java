package hust.project_2.service;

import hust.project_2.entity.User;
import hust.project_2.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FriendService {
    UserRepository userRepository;

    private User currentUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = auth.getName();
        return userRepository.findById(userId).orElseThrow();
    }

    public void addFriend(String userId) {
        User cur = currentUser();
        User other = userRepository.findById(userId).orElseThrow();
        cur.getFriends().add(other);
        userRepository.save(cur);
    }

    public void removeFriend(String userId) {
        User cur = currentUser();
        User other = userRepository.findById(userId).orElseThrow();
        cur.getFriends().remove(other);
        userRepository.save(cur);
    }

    public void block(String userId) {
        User cur = currentUser();
        User other = userRepository.findById(userId).orElseThrow();
        cur.getBlocked().add(other);
        userRepository.save(cur);
    }

    public void unblock(String userId) {
        User cur = currentUser();
        User other = userRepository.findById(userId).orElseThrow();
        cur.getBlocked().remove(other);
        userRepository.save(cur);
    }
}
