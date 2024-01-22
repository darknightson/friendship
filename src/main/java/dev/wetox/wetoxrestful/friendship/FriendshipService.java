package dev.wetox.wetoxrestful.friendship;

import dev.wetox.wetoxrestful.user.User;
import dev.wetox.wetoxrestful.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    사용자의 친구 리스트 조회
     */
    public List<FriendshipResponse> findMutualAcceptedFriendships(Long id) {
        return FriendshipResponse.from(friendshipRepository.findMutualAcceptedFriendships(id));
    }

    /*
    사용자가 수신한 친구 신청 리스트 조회
     */
    public List<FriendshipResponse> findByToIdAndStatus(Long toId, FriendshipStatus status) {
        return FriendshipResponse.from(friendshipRepository.findByToIdAndStatus(toId, status));
    }

    /*
    친구 관계 요청
     */
    @Transactional
    public Friendship create(Long fromUserId, Long toUserId) {
        User fromUser = userRepository.findById(fromUserId).orElseThrow();
        User toUser = userRepository.findById(toUserId).orElseThrow();
        return friendshipRepository.save(Friendship.create(fromUser, toUser));
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
