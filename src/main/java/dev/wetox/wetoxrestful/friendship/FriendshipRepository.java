package dev.wetox.wetoxrestful.friendship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    @Query("SELECT f " +
            " FROM Friendship f " +
            "WHERE f.status = 'ACCEPT' " +
            "  AND f.from.id = :userId " +
            "  AND f.to.id IN (" +
            "                   SELECT f2.from.id " +
            "                     FROM Friendship f2 " +
            "                    WHERE f2.to.id = :userId " +
            "                      AND f2.status = 'ACCEPT'" +
            "                   )"
    )
    List<Friendship> findMutualAcceptedFriendships(Long userId);

    List<Friendship> findByToIdAndStatus(Long toId, FriendshipStatus status);

}
