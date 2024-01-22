package dev.wetox.wetoxrestful.friendship;

import dev.wetox.wetoxrestful.user.User;
import dev.wetox.wetoxrestful.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("local")
class FriendshipServiceTest {

    @Autowired
    private FriendshipService friendshipService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User fromUser = User.builder()
                .nickname("test1")
                .password("1234")
                .build();
        User toUser = User.builder()
                .nickname("test2")
                .password("1234")
                .build();
        User user1 = User.builder()
                .nickname("test1")
                .password("1234")
                .build();
        User user2 = User.builder()
                .nickname("test2")
                .password("1234")
                .build();
        userRepository.save(fromUser);
        userRepository.save(toUser);
        userRepository.save(user1);
        userRepository.save(user2);
    }

    @Test
    void 친구_요청을_한다() {

        // given (준비)
        Long fromUserId = 1L;
        Long toUserId = 2L;

        // when (실행)
        Friendship friendship = friendshipService.create(fromUserId, toUserId);

        // then (단언 assert)
        assertThat(friendship.getFrom().getId()).isEqualTo(fromUserId);
        assertThat(friendship.getTo().getId()).isEqualTo(toUserId);
        assertThat(friendship.getStatus()).isEqualTo(FriendshipStatus.REQUEST);
    }


}