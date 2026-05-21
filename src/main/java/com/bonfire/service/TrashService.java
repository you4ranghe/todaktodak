package com.bonfire.service;

import com.bonfire.domain.Category;
import com.bonfire.domain.Trash;
import com.bonfire.domain.TrashStatus;
import com.bonfire.domain.User;
import com.bonfire.repository.TrashRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrashService {

    private final TrashRepository trashRepository;
    private final AiConsolationService aiConsolationService;

    public TrashService(TrashRepository trashRepository, AiConsolationService aiConsolationService) {
        this.trashRepository = trashRepository;
        this.aiConsolationService = aiConsolationService;
    }

    /** 감정 투척 → AI 위로 생성 → 적재(ACCUMULATED) 상태로 저장 */
    @Transactional
    public Trash throwAway(User user, String content, Category category) {
        List<Trash> history = trashRepository
                .findTop5ByUserAndCategoryOrderByCreatedAtDesc(user, category);

        Trash trash = new Trash(user, content, category);
        String consolation = aiConsolationService.consolation(content, category, history);
        trash.attachConsolation(consolation);

        return trashRepository.save(trash);
    }

    /** 소각(연출) → 소프트 삭제. 본인 것이 아니면 거부. */
    @Transactional
    public void burn(User user, Long trashId) {
        Trash trash = trashRepository.findById(trashId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 항목입니다."));
        if (!trash.getUser().getId().equals(user.getId())) {
            throw new SecurityException("본인의 항목만 소각할 수 있습니다.");
        }
        trash.burn();
    }

    /** 쓰레기통: 본인의 아직 소각되지 않은 적재분 */
    @Transactional(readOnly = true)
    public List<Trash> accumulated(User user) {
        return trashRepository.findByUserAndStatusOrderByCreatedAtDesc(user, TrashStatus.ACCUMULATED);
    }

    @Transactional(readOnly = true)
    public long accumulatedCount(User user) {
        return trashRepository.countByUserAndStatus(user, TrashStatus.ACCUMULATED);
    }
}
