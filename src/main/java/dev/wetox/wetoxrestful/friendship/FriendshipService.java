package dev.wetox.wetoxrestful.friendship;

import dev.wetox.wetoxrestful.user.User;
import dev.wetox.wetoxrestful.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FriendshipService {

    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;

    public Friendship findById(Long id) {
        return friendshipRepository.findById(id).orElseThrow();
    }

    /*
    친구 관계 요청
     */
    @Transactional
    public Friendship create(Long toUserId, Long fromUserId) {
        User toUser = userRepository.findById(toUserId).orElseThrow();
        User fromUser = userRepository.findById(fromUserId).orElseThrow();
        return friendshipRepository.save(Friendship.create(toUser, fromUser));
    }

    /*
    친구관계 수락
     */
    @Transactional
    public Friendship accept(Long id) {
        Friendship friendship = friendshipRepository.findById(id).orElseThrow();
        friendship.accept();
        return friendship;
    }
}
