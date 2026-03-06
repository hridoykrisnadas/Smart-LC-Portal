package com.example.smartlcprotal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(updatable = false)
    private int createdBy;
    @Column(updatable = false)
    private LocalDateTime createdAt;
    private int updateBy;
    private LocalDateTime updateAt;
    private Boolean isActive;

    @PrePersist
    public void setPreInsertData() {
        this.createdAt = LocalDateTime.now();
        this.isActive = true;
    }

    @PreUpdate
    public void setPostUpdateData() {
        this.updateAt = LocalDateTime.now();
    }
}