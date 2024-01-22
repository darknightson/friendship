package dev.wetox.wetoxrestful.friendship;

import dev.wetox.wetoxrestful.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FriendshipTest {

    private User fromUser;
    private User toUser;

    @BeforeEach
    void setUp() {

        fromUser = User.builder()
                .id(1L)
                .nickname("test1")
                .password("1234")
                .build();
        toUser = User.builder()
                .id(2L)
                .nickname("test2")
                .password("1234")
                .build();
    }

    @Test
    void 친구_요청을_생성한다() {

        // given (준비) // when (실행)
        Friendship friendship = Friendship.create(fromUser, toUser);

        // then (단언 assert)
        assertThat(friendship.getFrom().getNickname()).isEqualTo("test1");
        assertThat(friendship.getTo().getNickname()).isEqualTo("test2");
        assertThat(friendship.getStatus()).isEqualTo(FriendshipStatus.REQUEST);
    }

    @Test
    void 친구_요청을_수락한다() {

        // given (준비)
        Friendship friendship = Friendship.create(fromUser, toUser);
        // when (실행)
        friendship.accept();

        // then (단언 assert)
        assertThat(friendship.getStatus()).isEqualTo(FriendshipStatus.ACCEPT);
    }


}