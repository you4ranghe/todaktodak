package com.bonfire.service;

import com.bonfire.domain.Trash;

import java.util.List;

/**
 * AI 위로 생성 추상화.
 * Phase 1: Mock 구현. Phase 2: Claude API 구현체로 교체(같은 카테고리 과거 글을 컨텍스트로 주입).
 */
public interface AiConsolationService {

    /**
     * @param content    이번에 작성한 감정 텍스트
     * @param category   사용자가 선택한 카테고리
     * @param recentSameCategory 같은 카테고리의 과거 글 (공감 향상용 컨텍스트, 직접 인용 금지)
     * @return 따뜻한 위로 메시지
     */
    String consolation(String content, com.bonfire.domain.Category category, List<Trash> recentSameCategory);
}
