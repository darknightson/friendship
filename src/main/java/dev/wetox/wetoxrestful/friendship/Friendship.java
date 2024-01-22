package dev.wetox.wetoxrestful.friendship;

import dev.wetox.wetoxrestful.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Friendship extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friendship_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "from_id")
    private User from;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "to_id")
    private User to;

    @Enumerated(EnumType.STRING)
    private FriendshipStatus status;

    @Builder
    private Friendship(Long id, User from, User to, FriendshipStatus status) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.status = status;
    }

    // 친구 요청 생성
    public static Friendship create(User from, User to) {
        return Friendship.builder()
                .from(from)
                .to(to)
                .status(FriendshipStatus.REQUEST) // 최초 생성에는 친구 요청 상태이다.
                .build();
    }

    // 친구요청 수락
    public void accept() {
        this.status = FriendshipStatus.ACCEPT; // 친구 요청 수락
    }
}
