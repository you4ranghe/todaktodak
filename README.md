# 🔥 불멍 감정 소각장 (Bonfire TrashCan)

현대인의 파편화된 스트레스를 안전하게 '버리고 태우는' 비공개 힐링 공간.
기록 보관이 아니라 **소각(파괴) 연출**을 통한 심리적 해방감을 제공한다.

## 진행 현황

| Phase | 내용 | 상태 |
|-------|------|------|
| 0 | 프로젝트 셋업 (Spring Boot 3.4 + JPA + H2 File + Security) | ✅ 완료 |
| 1 | 로그인/회원가입 + 엔티티/API + POV 진입 화면 | ✅ 완료 |
| 2 | Claude API 연동 (위로 생성 + 과거 컨텍스트 주입) | ⬜ 예정 (현재 Mock) |
| 3 | POV 영상 합성 연출 (fireplace.mp4 + rain + 불티 파티클) | ⬜ 예정 (현재 CSS 플레이스홀더) |
| 4 | 통계 / 반응형 / 엣지케이스 | ⬜ 예정 |

## 핵심 설계 결정
- **로그인 필수**: 사용자 데이터 귀속 + AI가 과거 글을 참조해 공감 향상.
- **소각 = 소프트 삭제**: `status`를 `BURNED`로 전환할 뿐 **물리 삭제하지 않는다.**
  사용자에게는 노출하지 않지만 백엔드 AI는 같은 카테고리 과거 글을 참조한다.
- **카테고리는 사용자가 직접 선택** (AI 자동분류 X) → DB 조회 단순화.
- **개인정보**: 사용자의 날것 감정(`content`)은 절대 로깅하지 않는다.

## 실행 방법

> ⚠️ 시스템 기본 JDK가 11이라 그대로 빌드하면 실패한다.
> Gradle/앱 모두 **JDK 21**로 구동해야 한다. (`C:\Users\ibank\.jdks\jdk-21.0.11+10`)

```bat
run.bat
```
또는 수동으로:
```powershell
$env:JAVA_HOME = "C:\Users\ibank\.jdks\jdk-21.0.11+10"
.\gradlew.bat bootRun
```

- 앱: http://localhost:8080  (로그인 → 소각장)
- H2 콘솔: http://localhost:8080/h2-console  (JDBC URL: `jdbc:h2:file:./data/bonfire`, user `sa`)

## 환경 메모
- 설치 JDK: 8 / 11 / 25 (17·21 부재). Gradle 8.10은 JDK 25에서 빌드스크립트 파싱 불가(class v69),
  Spring Boot 플러그인은 JDK 17+ 필요 → **JDK 21을 별도 설치**해 사용.
- Gradle Wrapper: 8.10 / Java Toolchain: 21.

## API 요약
| Method | Endpoint | 설명 |
|--------|----------|------|
| POST | `/api/trash` | 감정 투척 + AI 위로 (ACCUMULATED 적재) |
| GET | `/api/trash/can` | 본인의 미소각 적재분 + count |
| PATCH | `/api/trash/{id}/burn` | 소각 연출 완료 → 소프트 삭제 |
