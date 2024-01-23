package dev.wetox.wetoxrestful.friendship;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/friendship")
public class FriendshipController {

    private final FriendshipService friendshipService;

    /*
    사용자의 친구 리스트 조회
    PathVariable: 사용자의 id 값은 차후 Principal 객체에서 가져 오거나, JWT 토큰에서 가져올 예정
     */
    @Operation(summary = "사용자의 친구 리스트 조회 상호 ACCEPT 관계",
            description = "사용자의 친구 리스트를 조회합니다. 사용자의 id 값을 넣으세요 이 부분은 차후 인증정보에서 사용해야 합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<List<FriendshipResponse>> findFriendshipById(@PathVariable Long id) {
        return ResponseEntity.ok(friendshipService.findMutualAcceptedFriendships(id));
    }

    /*
    사용자가 수신한 친구 신청 리스트 조회
     */
    @Operation(summary = "사용자가 수신한 친구 신청 리스트 조회",
            description = "사용자가 수신한 친구 신청 리스트를 조회합니다. 사용자의 id 값을 넣으세요 이 부분은 차후 인증정보에서 사용해야 합니다.")
    @GetMapping("/request/{id}")
    public ResponseEntity<List<FriendshipResponse>> findFriendshipRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(friendshipService.findByToIdAndStatus(id, FriendshipStatus.REQUEST));
    }

    @Operation(summary = "친구관계 신청",
            description = "친구관계를 신청합니다. toId: 친구 신청을 받는 사용자의 id, fromId: 친구 신청을 하는 사용자의 id")
    @PostMapping("/request/{toId}/{fromId}")
    public ResponseEntity<String> createFriendship(@PathVariable Long toId, @PathVariable Long fromId) {
        friendshipService.create(toId, fromId);
        return ResponseEntity.ok("친구관계 신청 완료");
    }

    @Operation(summary = "친구관계 수락",
            description = "친구관계를 수락합니다. toId: 친구 신청을 받는 사용자의 id, fromId: 친구 신청을 하는 사용자의 id")
    @PutMapping("/request/{toId}/{fromId}")
    public ResponseEntity<String> acceptFriendship(@PathVariable Long toId, @PathVariable Long fromId) {
        friendshipService.accept(toId, fromId);
        return ResponseEntity.ok("친구관계 승인 완료");
    }
}
