package com.bonfire.domain;

/**
 * 결정사항: 소각은 "물리 삭제"가 아니라 연출(행위)일 뿐.
 * 데이터는 보존하되 상태만 BURNED 로 바꾸는 소프트 삭제.
 * 사용자에게는 BURNED 데이터를 노출하지 않지만, 백엔드 AI는 공감 향상을 위해 참조한다.
 */
public enum TrashStatus {
    ACCUMULATED, // 쓰레기통에 적재 (아직 소각 안 함)
    BURNED       // 소각 연출 완료 (DB에는 보존)
}
