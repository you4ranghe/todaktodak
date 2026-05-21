package com.bonfire.repository;

import com.bonfire.domain.Category;
import com.bonfire.domain.Trash;
import com.bonfire.domain.TrashStatus;
import com.bonfire.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrashRepository extends JpaRepository<Trash, Long> {

    // 쓰레기통 조회: 본인의 아직 소각되지 않은 적재분만
    List<Trash> findByUserAndStatusOrderByCreatedAtDesc(User user, TrashStatus status);

    long countByUserAndStatus(User user, TrashStatus status);

    // AI 공감 향상용 컨텍스트: 같은 카테고리 최근 글 (소각 여부 무관, 본인 것)
    List<Trash> findTop5ByUserAndCategoryOrderByCreatedAtDesc(User user, Category category);
}
