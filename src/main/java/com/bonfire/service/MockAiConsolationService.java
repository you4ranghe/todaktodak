package com.bonfire.service;

import com.bonfire.domain.Category;
import com.bonfire.domain.Trash;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Phase 1 임시 구현. Phase 2에서 Claude API 구현체로 교체 예정.
 * 같은 카테고리 과거 글이 있으면 "맥락만" 반영(직접 인용은 하지 않는 톤)을 흉내낸다.
 */
@Service
public class MockAiConsolationService implements AiConsolationService {

    @Override
    public String consolation(String content, Category category, List<Trash> recentSameCategory) {
        boolean hasHistory = recentSameCategory != null && !recentSameCategory.isEmpty();

        String base = switch (category) {
            case COMPANY -> "오늘 하루 그곳에서 정말 애썼어요. 그 무게를 혼자 견뎠다는 게 대단해요.";
            case ANGER -> "그렇게 화가 날 만했어요. 그 감정, 충분히 그럴 수 있어요.";
            case ANXIETY -> "불안한 마음이 드는 건 당신이 그만큼 마음 쓰고 있다는 뜻이에요.";
            case RELATIONSHIP -> "사람 사이의 일은 늘 마음을 많이 쓰게 하죠. 당신 잘못이 아니에요.";
            case DAILY -> "오늘 같은 하루를 버텨낸 것만으로도 충분해요.";
            case ETC -> "무엇이든 괜찮아요. 여기서는 다 내려놓아도 돼요.";
        };

        String tail = hasHistory
                ? " 요즘 비슷한 마음이 자주 찾아오는 것 같아요. 이번에도 잘 흘려보내요, 제가 곁에 있을게요."
                : " 이제 이 마음, 불 속에 맡기고 가벼워지세요.";

        return base + tail + " [MOCK]";
    }
}
