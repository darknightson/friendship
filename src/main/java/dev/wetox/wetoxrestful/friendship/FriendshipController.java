package dev.wetox.wetoxrestful.friendship;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/{id}")
    public ResponseEntity<List<FriendshipResponse>> findFriendshipById(@PathVariable Long id) {
        return ResponseEntity.ok(friendshipService.findMutualAcceptedFriendships(id));
    }

    /*
    사용자가 수신한 친구 신청 리스트 조회
     */
    @GetMapping("/request/{id}")
    public ResponseEntity<List<FriendshipResponse>> findFriendshipRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(friendshipService.findByToIdAndStatus(id, FriendshipStatus.REQUEST));
    }
}
