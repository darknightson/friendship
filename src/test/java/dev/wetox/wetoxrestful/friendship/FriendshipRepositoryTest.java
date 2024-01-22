package dev.wetox.wetoxrestful.friendship;

import dev.wetox.wetoxrestful.user.User;
import dev.wetox.wetoxrestful.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class FriendshipRepositoryTest {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Test
    void 테스트() {

        // given (준비)


        // when (실행)


        // then (단언 assert)

        List<Friendship> mutualAcceptedFriendships = friendshipRepository.findMutualAcceptedFriendships(5l);
        System.out.println("mutualAcceptedFriendships.size() = " + mutualAcceptedFriendships.size());
        mutualAcceptedFriendships.forEach(friendship -> {
            System.out.println("friendship.getFrom().getNickname() = " + friendship.getTo().getId());

        });
    }

    @Test
    void 테스트1() {

        List<Friendship> result = friendshipRepository.findByToIdAndStatus(3l, FriendshipStatus.REQUEST);
        result.forEach(friendship -> {
            System.out.println("friendship.getFrom().getNickname() = " + friendship.getFrom().getId());

        });
    }

}