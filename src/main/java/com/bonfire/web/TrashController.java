package com.bonfire.web;

import com.bonfire.domain.Trash;
import com.bonfire.domain.User;
import com.bonfire.service.TrashService;
import com.bonfire.service.UserService;
import com.bonfire.web.dto.ThrowAwayRequest;
import com.bonfire.web.dto.TrashResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trash")
public class TrashController {

    private final TrashService trashService;
    private final UserService userService;

    public TrashController(TrashService trashService, UserService userService) {
        this.trashService = trashService;
        this.userService = userService;
    }

    /** 감정 투척 + AI 위로 (적재) */
    @PostMapping
    public ResponseEntity<TrashResponse> throwAway(@Valid @RequestBody ThrowAwayRequest req,
                                                   @AuthenticationPrincipal UserDetails principal) {
        User user = userService.getByUsername(principal.getUsername());
        Trash saved = trashService.throwAway(user, req.content(), req.category());
        return ResponseEntity.status(HttpStatus.CREATED).body(TrashResponse.from(saved));
    }

    /** 쓰레기통: 본인의 미소각 적재분 + 통계 */
    @GetMapping("/can")
    public Map<String, Object> can(@AuthenticationPrincipal UserDetails principal) {
        User user = userService.getByUsername(principal.getUsername());
        List<TrashResponse> items = trashService.accumulated(user).stream()
                .map(TrashResponse::from)
                .toList();
        return Map.of(
                "count", items.size(),
                "items", items
        );
    }

    /** 소각(연출) → 소프트 삭제 */
    @PatchMapping("/{id}/burn")
    public ResponseEntity<Void> burn(@PathVariable Long id,
                                     @AuthenticationPrincipal UserDetails principal) {
        User user = userService.getByUsername(principal.getUsername());
        trashService.burn(user, id);
        return ResponseEntity.noContent().build();
    }
}
