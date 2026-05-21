package com.bonfire.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trash")
public class Trash {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // 사용자가 작성한 날것의 감정

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Category category; // 사용자가 직접 선택

    @Lob
    @Column(columnDefinition = "TEXT")
    private String aiConsolation; // Claude 위로 (Phase 2 연동, 현재는 mock)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TrashStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime burnedAt;

    protected Trash() {
    }

    public Trash(User user, String content, Category category) {
        this.user = user;
        this.content = content;
        this.category = category;
        this.status = TrashStatus.ACCUMULATED;
    }

    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    /** 소각 연출 완료 → 소프트 삭제(상태 전환). 물리 삭제하지 않는다. */
    public void burn() {
        this.status = TrashStatus.BURNED;
        this.burnedAt = LocalDateTime.now();
    }

    public void attachConsolation(String aiConsolation) {
        this.aiConsolation = aiConsolation;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public Category getCategory() {
        return category;
    }

    public String getAiConsolation() {
        return aiConsolation;
    }

    public TrashStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getBurnedAt() {
        return burnedAt;
    }
}
