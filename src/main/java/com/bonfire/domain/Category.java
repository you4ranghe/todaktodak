package com.bonfire.domain;

/**
 * 감정 쓰레기 카테고리.
 * 결정사항: AI 자동분류가 아니라 "사용자가 직접 선택" → DB 조회 단순화 + AI 컨텍스트 추출 용이.
 */
public enum Category {
    COMPANY("회사"),
    DAILY("일상"),
    ANXIETY("불안"),
    ANGER("분노"),
    RELATIONSHIP("관계"),
    ETC("기타");

    private final String label;

    Category(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
