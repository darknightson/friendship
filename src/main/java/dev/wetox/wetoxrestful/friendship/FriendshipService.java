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
    public void create(Long toUserId, Long fromUserId) {

        // 조회 전 친구관계가 존재하는지 확인
        friendshipRepository.findByToIdAndFromId(toUserId, fromUserId).ifPresent(friendship -> {
            throw new IllegalStateException("이미 친구관계가 존재합니다.");
        });
        User toUser = userRepository.findById(toUserId).orElseThrow();
        User fromUser = userRepository.findById(fromUserId).orElseThrow();
        friendshipRepository.save(Friendship.create(toUser, fromUser));
    }
    /*
    친구관계 수락
     */
    @Transactional
    public void accept(Long toId, Long fromId) {
        Friendship friendship = friendshipRepository.findByToIdAndFromId(toId, fromId).orElseThrow();
        friendship.accept();
    }
}
