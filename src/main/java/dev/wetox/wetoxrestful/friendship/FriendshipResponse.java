package dev.wetox.wetoxrestful.friendship;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@Builder
public class FriendshipResponse {

    private Long friendshipId;
    private Long toUserId;
    private String toUserNickname;
    private LocalDateTime requestedDate;

    public static FriendshipResponse from(Friendship friendship) {
        return FriendshipResponse.builder()
                .friendshipId(friendship.getId())
                .toUserId(friendship.getTo().getId())
                .toUserNickname(friendship.getTo().getNickname())
                .requestedDate(friendship.getCreatedAt())
                .build();
    }

    public static List<FriendshipResponse> from(List<Friendship> friendships) {
        return friendships.stream()
                .map(FriendshipResponse::from)
                .collect(toList());
    }
}
